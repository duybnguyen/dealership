package com.pluralsight;

import java.io.*;

public class DealershipFileManager {

    public Dealership getDealership() {
        File file = new File("inventory.csv");
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();

            if (line == null) {
                System.out.println("Exiting: Unable to create Dealership object, file is empty");
                return null;
            }
            String[] header = line.split("\\|");
            if (header.length < 3) {
                System.out.println("Exiting: Malformed header line");
                return null;
            }

            Dealership dealership = new Dealership(header[0], header[1], header[2]);

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length != 8) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }
                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                double price = Double.parseDouble(parts[7]);
                int odometer = Integer.parseInt(parts[6]);


                Vehicle vehicle = new Vehicle(vin, year, price, odometer, parts[5], parts[4], parts[3], parts[2]);
                dealership.addVehicle(vehicle);
            }
            return dealership;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveDealership(Dealership dealership) {
        File file = new File("inventory.csv");
        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone() + "\n");

            for (Vehicle v: dealership.getAllVehicles()) {
                fileWriter.write(v.getVin() + "|" + v.getYear() + "|" + v.getMake() + "|" + v.getModel() + "|" + v.getVehicleType() + "|" + v.getColor() + "|" +v.getOdometer() + "|" + v.getPrice() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
