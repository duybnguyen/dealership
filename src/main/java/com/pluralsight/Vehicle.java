package com.pluralsight;

public class Vehicle {
    int vin;
    int year;
    String make;
    String model;
    String vehicleType;
    String color;
    int odometer;
    double price;

    public Vehicle(int vin, int year, double price, int odometer, String color, String vehicleType, String model, String make) {
        this.vin = vin;
        this.year = year;
        this.price = price;
        this.odometer = odometer;
        this.color = color;
        this.vehicleType = vehicleType;
        this.model = model;
        this.make = make;
    }
}
