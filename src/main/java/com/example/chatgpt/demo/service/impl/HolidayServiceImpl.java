package com.example.chatgpt.demo.service.impl;

import com.example.chatgpt.demo.dao.HolidayDao;
import com.example.chatgpt.demo.entity.Holiday;
import com.example.chatgpt.demo.service.HolidayService;
import com.example.chatgpt.demo.utils.CustomResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Service
public class HolidayServiceImpl implements HolidayService {

    // define logger
    private static final Logger logger = LoggerFactory.getLogger(HolidayServiceImpl.class);

    // define dao
    private HolidayDao dao;

    // save holidays with the given holidays set
    public CustomResponse saveHolidays(Set<Holiday> holidays) {
        // if holidays is empty, return
        if (CollectionUtils.isEmpty(holidays)) {
            return new CustomResponse(400, "Holidays is empty", null);
        }

        logger.info("Saving holidays...");
        // call dao to save holidays
        return dao.saveHoliday(holidays);
    }

    // update holidays with the given holidays set
    public CustomResponse updateHolidays(Set<Holiday> holidays) {
        // if holidays is empty, return
        if (CollectionUtils.isEmpty(holidays)) {
            return new CustomResponse(400, "Holidays is empty", null);
        }

        logger.info("Updating holidays...");
        // call dao to update holidays
        return dao.saveHoliday(holidays);
    }

    // delete holidays with the given holidays set
    public CustomResponse deleteHolidays(Set<Holiday> holidays) {
        // if holidays is empty, return
        if (CollectionUtils.isEmpty(holidays)) {
            return new CustomResponse(400, "Holidays is empty", null);
        }

        logger.info("Deleting holidays...");
        // call dao to delete holidays
        return dao.removeHoliday(holidays);
    }

    // get next holiday by country code
    public Holiday getNextHolidayByCountryCode(String countryCode) {
        // if country code is empty, return
        if (countryCode == null || countryCode.isEmpty()) {
            return null;
        }

        logger.info("Getting next holiday by country code...");
        // call dao to get next holiday by country code
        return dao.getNextHolidayByCountryCode(countryCode);
    }

    // get next year holiday by country code
    public Set<Holiday> getNextYearHolidayByCountryCode(String countryCode) {
        // if country code is empty, return
        if (countryCode == null || countryCode.isEmpty()) {
            return null;
        }

        logger.info("Getting next year holiday by country code...");
        // call dao to get next year holiday by country code
        return dao.getNextYearHolidayByCountryCode(countryCode);
    }

    // check if the given date is holiday
    public CustomResponse isHoliday(String date) {
        // if date is empty, return
        if (date == null || date.isEmpty()) {
            return new CustomResponse(400, "Date is empty", null);
        }

        logger.info("Checking if the given date is holiday...");
        // call dao to check if the given date is holiday
        return dao.isHoliday(date);
    }

}
