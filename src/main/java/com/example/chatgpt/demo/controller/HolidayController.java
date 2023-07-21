package com.example.chatgpt.demo.controller;

import com.example.chatgpt.demo.entity.Holiday;
import com.example.chatgpt.demo.service.HolidayService;
import com.example.chatgpt.demo.utils.CustomResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/holiday")
public class HolidayController {

    //define logger
    private static final Logger logger = LoggerFactory.getLogger(HolidayController.class);

    // define service
    @Autowired
    private HolidayService service;

    // save holidays with the given holidays set
    @RequestMapping(value = "/saveHolidays", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public CustomResponse saveHolidays(@RequestBody Set<Holiday> holidays) {
        // if holidays is empty, return
        if (CollectionUtils.isEmpty(holidays)) {
            return new CustomResponse(400, "Holidays is empty", null);
        }

        logger.info("Saving holidays...");
        // call service to save holidays
        return service.saveHolidays(holidays);
    }

    // update holidays with the given holidays set
    @RequestMapping(value = "/updateHolidays", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public CustomResponse updateHolidays(@RequestBody Set<Holiday> holidays) {
        // if holidays is empty, return
        if (CollectionUtils.isEmpty(holidays)) {
            return new CustomResponse(400, "Holidays is empty", null);
        }

        logger.info("Updating holidays...");
        // call service to update holidays
        return service.updateHolidays(holidays);
    }

    // remove holidays with the given holidays set
    @RequestMapping(value = "/removeHolidays", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public CustomResponse removeHolidays(@RequestBody Set<Holiday> holidays) {
        // if holidays is empty, return
        if (CollectionUtils.isEmpty(holidays)) {
            return new CustomResponse(400, "Holidays is empty", null);
        }

        logger.info("Removing holidays...");
        // call service to remove holidays
        return service.deleteHolidays(holidays);
    }

    // get next holiday by country code
    @RequestMapping(value = "/getNextHolidayByCountryCode", method = RequestMethod.GET, produces = "application/json")
    public Holiday getNextHolidayByCountryCode(@RequestParam String countryCode) {
        logger.info("Getting next holiday by country code...");
        // call service to get next holiday by country code
        return service.getNextHolidayByCountryCode(countryCode);
    }

    // get next year holiday by country code
    @RequestMapping(value = "/getNextYearHolidayByCountryCode", method = RequestMethod.GET, produces = "application/json")
    public Set<Holiday> getNextYearHolidayByCountryCode(@RequestParam String countryCode) {
        logger.info("Getting next year holiday by country code...");
        // call service to get next year holiday by country code
        return service.getNextYearHolidayByCountryCode(countryCode);
    }

    // check if the given date is holiday
    @RequestMapping(value = "/isHoliday", method = RequestMethod.GET, produces = "application/json")
    public CustomResponse isHoliday(@RequestParam String date) {
        logger.info("Checking if the given date is holiday...");
        // call service to check if the given date is holiday
        return service.isHoliday(date);
    }

}
