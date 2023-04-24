package com.example.myfirstapplication.model;

public class Hour {
    private String Sunday;
    private String Monday;
    private String Tuesday;
    private String Wednesday;
    private String Thursday;
    private String Friday;
    private String Saturday;

    public Hour() {
    }

    public Hour(String sunday, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday) {
        this.Sunday = sunday;
        this.Monday = monday;
        this.Tuesday = tuesday;
        this.Wednesday = wednesday;
        this.Thursday = thursday;
        this.Friday = friday;
        this.Saturday = saturday;
    }

    public String getSunday() {
        return Sunday;
    }

    public void setSunday(String sunday) {
        this.Sunday = sunday;
    }

    public String getMonday() {
        return Monday;
    }

    public void setMonday(String monday) {
        this.Monday = monday;
    }

    public String getTuesday() {
        return Tuesday;
    }

    public void setTuesday(String tuesday) {
        this.Tuesday = tuesday;
    }

    public String getWednesday() {
        return Wednesday;
    }

    public void setWednesday(String wednesday) {
        this.Wednesday = wednesday;
    }

    public String getThursday() {
        return Thursday;
    }

    public void setThursday(String thursday) {
        this.Thursday = thursday;
    }

    public String getFriday() {
        return Friday;
    }

    public void setFriday(String friday) {
        this.Friday = friday;
    }

    public String getSaturday() {
        return Saturday;
    }

    public void setSaturday(String saturday) {
        this.Saturday = saturday;
    }
}
