package com.as.users.model;


public class DateTimeFormatData {
    private String pattern;
    private String dateTimeType;

    public DateTimeFormatData() {
    }

    public DateTimeFormatData(String pattern, String dateTimeType) {
        this.pattern = pattern;
        this.dateTimeType = dateTimeType;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getDateTimeType() {
        return dateTimeType;
    }

    public void setDateTimeType(String dateTimeType) {
        this.dateTimeType = dateTimeType;
    }
}
