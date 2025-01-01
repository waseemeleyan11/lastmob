package com.example.myapplication;

public class Reminder {
    private String title;
    private String date;
    private String frequency;

    public Reminder(String title, String date, String frequency) {
        this.title = title;
        this.date = date;
        this.frequency = frequency;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getFrequency() {
        return frequency;
    }
}

