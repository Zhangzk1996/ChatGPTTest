package com.example.chatgpt.demo.dao.impl;

import com.example.chatgpt.demo.dao.HolidayDao;
import com.example.chatgpt.demo.entity.Holiday;
import com.example.chatgpt.demo.utils.CustomResponse;
import com.example.chatgpt.demo.utils.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class HolidayDaoImpl implements HolidayDao {

    private static Logger logger = LoggerFactory.getLogger(HolidayDaoImpl.class);
    private static String filePath = "src/main/resources/holidays.csv";

    private static Set<Holiday> allHolidays = new CopyOnWriteArraySet<>();

    // post construct
    @PostConstruct
    public void init() {
        refreshAllHolidays();
    }

    // refresh all holidays by reading data from csv file schedule every 5 minutes
    @Scheduled(fixedRate = 300000)
    public void refreshAllHolidays() {
        allHolidays = getAllHolidays();
    }

    // read data from csv file
    // return Set of holidays
    @Override
    public Set<Holiday> getAllHolidays() {
        Set<Holiday> holidays = new HashSet<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Holiday holiday = new Holiday();
                holiday.setCountryCode(values[0]);
                holiday.setCountryDescription(values[1]);
                holiday.setHolidayDate(DateFormatUtils.formatStringToDate(values[2]));
                holiday.setHolidayName(values[3]);
                holidays.add(holiday);
            }
        } catch (Exception e) {
            logger.error("Error in reading file: " + filePath);
            e.printStackTrace();
        }
        return holidays;
    }

    // return next holiday by country code based on current system date
    @Override
    public Holiday getNextHolidayByCountryCode(String countryCode) {
        Set<Holiday> holidays = allHolidays;
        Date currentDate = new Date();
        Holiday nextHoliday = null;
        for (Holiday holiday : holidays) {
            if (holiday.getCountryCode().equals(countryCode)) {
                if (nextHoliday == null) {
                    nextHoliday = holiday;
                } else {
                    if (holiday.getHolidayDate().compareTo(nextHoliday.getHolidayDate()) < 0 && currentDate.compareTo(nextHoliday.getHolidayDate()) < 0) {
                        nextHoliday = holiday;
                    }
                }
            }
        }
        return nextHoliday;
    }

    // return next year's holiday information for given country code and current system date
    @Override
    public Set<Holiday> getNextYearHolidayByCountryCode(String countryCode) {
        Set<Holiday> holidays = allHolidays;
        Date currentDate = new Date();
        Set<Holiday> nextYearHolidays = new HashSet<>();
        for (Holiday holiday : holidays) {
            if (holiday.getCountryCode().equals(countryCode)) {
                if (holiday.getHolidayDate().getYear() > currentDate.getYear() && holiday.getHolidayDate().getYear() < currentDate.getYear() + 2) {
                    nextYearHolidays.add(holiday);
                }
            }
        }
        return nextYearHolidays;
    }

    // check if given date is holiday
    @Override
    public CustomResponse isHoliday(String date) {
        Set<String> matchedCountry = new HashSet<>();
        Set<String> missedCountry = new HashSet<>();
        try {
            Date inputDate = DateFormatUtils.formatStringToDate(date);
            for (Holiday holiday : allHolidays) {
                if (inputDate != null && inputDate.compareTo(holiday.getHolidayDate()) == 0) {
                    matchedCountry.add(holiday.getCountryCode());
                } else {
                    missedCountry.add(holiday.getCountryCode());
                }
            }
        } catch (ParseException e) {
            return new CustomResponse(400, "Invalid date format, correct format is: yyyy-MM-dd", null);
        }

        String message = "the date: " + date + " is holiday in: " + matchedCountry + ", not holiday in: " + missedCountry;
        return new CustomResponse(200, message, null);
    }

    // save data from allHolidays to csv file
    public void saveAllHolidayToFile() {
        // get system newlines
        String newLine = System.getProperty("line.separator");

        // transfer data from allHolidays to string
        StringBuffer sb = new StringBuffer();
        for (Holiday holiday : allHolidays) {
            sb.append(holiday.getCountryCode() + "," + holiday.getCountryDescription() + "," + DateFormatUtils.formatDateToString(holiday.getHolidayDate()) + "," + holiday.getHolidayName());
            sb.append(newLine);
        }

        // write data to csv file
        try {
            FileWriter fw = new FileWriter(filePath, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sb.toString());
            bw.close();
        } catch (Exception e) {
            System.out.println("Error in writing file: " + filePath);
            e.printStackTrace();
        }
    }

    // batch save all holidays information to csv file
    @Override
    public CustomResponse saveHoliday(Set<Holiday> holidays) {
        logger.info("allHolidays size: " + allHolidays.size());
        // remove duplicate holidays
        allHolidays.removeAll(holidays);
        logger.info("allHolidays size: " + allHolidays.size());

        // add holidays to allHolidays
        allHolidays.addAll(holidays);
        logger.info("allHolidays size: " + allHolidays.size());

        // add all holidays to csv file
        saveAllHolidayToFile();

        return new CustomResponse(200, "Success", null);
    }

    // remove holiday information from csv file
    @Override
    public CustomResponse removeHoliday(Set<Holiday> holidays) {
        // remove holiday information from allHolidays
        allHolidays.removeAll(holidays);

        // save allHolidays to csv file
        saveAllHolidayToFile();

        return new CustomResponse(200, "Success", null);
    }

}
