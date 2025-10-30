package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        inventory = new ArrayList<>();
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> filteredVehicles = new ArrayList<>();
        for (Vehicle v: getAllVehicles()) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
                filteredVehicles.add(v);
            }
        }
        return filteredVehicles;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> filteredVehicles = new ArrayList<>();
        for (Vehicle v: getAllVehicles()) {
            if (v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)) {
                filteredVehicles.add(v);
            }
        }
        return filteredVehicles;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> filteredVehicles = new ArrayList<>();
        for (Vehicle v : getAllVehicles()) {
            if (v.getYear() >= min && v.getYear() <= max) {
                filteredVehicles.add(v);
            }
        }
        return filteredVehicles;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> filteredVehicles = new ArrayList<>();
        for (Vehicle v : getAllVehicles()) {
            if (v.getColor().equalsIgnoreCase(color)) {
                filteredVehicles.add(v);
            }
        }
        return filteredVehicles;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> filteredVehicles = new ArrayList<>();
        for (Vehicle v : getAllVehicles()) {
            if (v.getOdometer() >= min && v.getOdometer() <= max) {
                filteredVehicles.add(v);
            }
        }
        return filteredVehicles;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> filteredVehicles = new ArrayList<>();
        for (Vehicle v : getAllVehicles()) {
            if (v.getVehicleType().equalsIgnoreCase(vehicleType)) {
                filteredVehicles.add(v);
            }
        }
        return filteredVehicles;
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public boolean removeVehicle(int vin) {
        for (Vehicle v : getAllVehicles()) {
            if (v.getVin() == vin) {
                inventory.remove(v);
                return true;
            }
        }
        return false;
    }
}
