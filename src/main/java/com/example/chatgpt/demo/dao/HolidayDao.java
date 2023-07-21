package com.example.chatgpt.demo.dao;

import com.example.chatgpt.demo.entity.Holiday;
import com.example.chatgpt.demo.utils.CustomResponse;

import java.util.Set;

public interface HolidayDao {

    Set<Holiday> getAllHolidays();

    Holiday getNextHolidayByCountryCode(String countryCode);

    Set<Holiday> getNextYearHolidayByCountryCode(String countryCode);

    CustomResponse isHoliday(String date);

    CustomResponse saveHoliday(Set<Holiday> holidays);

    CustomResponse removeHoliday(Set<Holiday> holidays);
}
