package com.example.myapplication;

public class MaintenanceItem {
    private String name;
    private String dueDate;

    public MaintenanceItem(String name, String dueDate) {
        this.name = name;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public String getDueDate() {
        return dueDate;
    }
}

