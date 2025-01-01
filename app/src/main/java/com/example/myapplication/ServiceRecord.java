package com.example.myapplication;

public class ServiceRecord {
    private String serviceName;
    private String date;
    private double cost;

    public ServiceRecord(String serviceName, String date, double cost) {
        this.serviceName = serviceName;
        this.date = date;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getDate() {
        return date;
    }

    public double getCost() {
        return cost;
    }
}

