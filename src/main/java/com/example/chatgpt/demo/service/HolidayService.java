package com.example.chatgpt.demo.service;

import com.example.chatgpt.demo.entity.Holiday;
import com.example.chatgpt.demo.utils.CustomResponse;


import java.util.Set;

public interface HolidayService {
    
    // declare methods here

    // save holidays with the given holidays set
    CustomResponse saveHolidays(Set<Holiday> holidays);

    // update holidays with the given holidays set
    CustomResponse updateHolidays(Set<Holiday> holidays);

    // delete holidays with the given holidays set
    CustomResponse deleteHolidays(Set<Holiday> holidays);

    // get next holiday by country code
    Holiday getNextHolidayByCountryCode(String countryCode);

    // get next year holiday by country code
    Set<Holiday> getNextYearHolidayByCountryCode(String countryCode);

    // check if the given date is holiday
    CustomResponse isHoliday(String date);

}
