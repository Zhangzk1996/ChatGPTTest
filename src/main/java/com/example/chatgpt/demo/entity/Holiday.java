package com.example.chatgpt.demo.entity;

import com.example.chatgpt.demo.utils.DateFormatUtils;

import java.util.Date;

public class Holiday {

    // country code
    private String countryCode;

    // country description
    private String countryDescription;

    // holiday name
    private String holidayName;

    // holiday date
    private Date holidayDate;

    // constructor for Holiday
    public Holiday() {
    }

    // constructor for Holiday
    // @param countryCode
    // @param countryDescription
    // @param holidayName
    // @param holidayDate
    public Holiday(String countryCode, String countryDescription, String holidayName, Date holidayDate) {
        this.countryCode = countryCode;
        this.countryDescription = countryDescription;
        this.holidayName = holidayName;
        this.holidayDate = holidayDate;
    }

    // getter for all the fields
    // @return countryCode
    public String getCountryCode() {
        return countryCode;
    }

    // setter for all the fields
    // @param countryCode
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    // getter for all the fields
    // @return countryDescription
    public String getCountryDescription() {
        return countryDescription;
    }

    // setter for all the fields
    // @param countryDescription
    public void setCountryDescription(String countryDescription) {
        this.countryDescription = countryDescription;
    }

    // getter for all the fields
    // @return holidayName
    public String getHolidayName() {
        return holidayName;
    }

    // setter for all the fields
    // @param holidayName
    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    // getter for all the fields
    // @return holidayDate
    public Date getHolidayDate() {
        return holidayDate;
    }

    // setter for all the fields
    // @param holidayDate
    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    // toString method
    // @return countryCode
    // @return countryDescription
    // @return holidayName
    // @return holidayDate
    @Override
    public String toString() {
        return "Holiday [countryCode=" + countryCode + ", countryDescription=" + countryDescription + ", holidayDate="
                + holidayDate + ", holidayName=" + holidayName + "]";
    }

    // hashCode method
    // @return result
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + countryCode.hashCode();
        result = prime * result + DateFormatUtils.formatDateToString(holidayDate).hashCode();
        return result;
    }

    // equals method
    // @param obj
    // @return true
    // @return false
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Holiday))
            return false;
        Holiday other = (Holiday) obj;
        if (!countryCode.equals(other.countryCode))
            return false;
        if (!DateFormatUtils.formatDateToString(holidayDate).equals(DateFormatUtils.formatDateToString(other.holidayDate)))
            return false;
        return true;
    }

}
