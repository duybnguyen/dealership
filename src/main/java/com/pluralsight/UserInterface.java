package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;

    public void display() {
        init();
        if (this.dealership == null) {
            System.out.println("Exiting: Failed to initialize dealership object");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 2) {
            displayMenu();

            if (!scanner.hasNextInt()) {
                System.out.println("Please only enter 1-2.");
                scanner.nextLine();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> processGetAllVehiclesRequest(scanner);
                case 2 -> System.out.println("\nThank you for shopping with us!");
                default -> System.out.println("Invalid choice");
            }
        }
        scanner.close();
    }

    public void processGetAllVehiclesRequest(Scanner scanner) {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
        int choice = -1;
        while (choice != 9) {
            displaySearchChoices();

            if (!scanner.hasNextInt()) {
                System.out.println("Please only enter 1-9");
                scanner.nextLine();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("\nEnter minimum price: ");
                    double min = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter maximum price: ");
                    double max = Double.parseDouble(scanner.nextLine());
                    processGetByPriceRequestRequest(min, max);
                }
                case 2 -> {
                    System.out.print("\nEnter make: ");
                    String make = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    processGetByMakeModelRequest(make, model);
                }
                case 3 -> {
                    System.out.print("\nEnter minimum year: ");
                    int min = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter maximum year: ");
                    int max = Integer.parseInt(scanner.nextLine());
                    processGetByYearRequest(min, max);
                }
                case 4 -> {
                    System.out.print("\nEnter color: ");
                    String color = scanner.nextLine();
                    processGetByColorRequest(color);
                }
                case 5 -> {
                    System.out.print("\nEnter minimum mileage: ");
                    int min = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter maximum mileage: ");
                    int max = Integer.parseInt(scanner.nextLine());
                    processGetByMileageRequest(min, max);
                }
                case 6 -> {
                    System.out.print("\nEnter vehicle Type: ")  ;
                    String type = scanner.nextLine();
                    processGetByVehicleTypeRequest(type);
                }
                case 7 -> {
                    System.out.print("Enter VIN: ");
                    int vin = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter year: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter make: ");
                    String make = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter vehicle type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter color: ");
                    String color = scanner.nextLine();
                    System.out.print("Enter odometer reading: ");
                    int odometer = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter price: ");
                    double price = Double.parseDouble(scanner.nextLine());

                    Vehicle newVehicle = new Vehicle(vin, year, price, odometer, color, type, model, make);
                    processAddVehicleRequest(newVehicle);
                }
                case 8 -> {
                    System.out.print("Enter VIN of the vehicle to remove: ");
                    int vin = Integer.parseInt(scanner.nextLine());
                    processRemoveVehicleRequest(vin);
                }
                case 9 -> display();
                default -> System.out.println("Invalid choice");
            }
        }
    }

    public void processGetByPriceRequestRequest(double min, double max) {
        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    public void processGetByMakeModelRequest(String make, String model) {
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    public void processGetByYearRequest(int min, int max) {
        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    public void processGetByColorRequest(String color) {
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest(int min, int max) {
        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    public void processGetByVehicleTypeRequest(String type) {
        displayVehicles(dealership.getVehiclesByType(type));
    }

    public void processAddVehicleRequest(Vehicle vehicle) {
        dealership.addVehicle(vehicle);
        new DealershipFileManager().saveDealership(this.dealership);
        System.out.println("\nSuccessfully added vehicle!");
    }

    public void processRemoveVehicleRequest(int vin) {
        boolean success = dealership.removeVehicle(vin);
        if (success) {
            new DealershipFileManager().saveDealership(this.dealership);
            System.out.println("\nSuccessfully removed vehicle!");
        } else {
            System.out.println("\n Could not find vehicle with that VIN");
        }
    }

    public void displayVehicles(List<Vehicle> vehicles) {
        System.out.println("\n========================== Inventory ===========================");
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found");
            System.out.println("=================================================================");
            return;
        }
        for (Vehicle v: vehicles) {
            System.out.println(v);
        }
        System.out.println("=================================================================");
    }

    private void init() {
        DealershipFileManager dfm = new DealershipFileManager();
        this.dealership = dfm.getDealership();
    }

    public void displayMenu() {
        System.out.println("\nWelcome to the Car Dealership!");
        System.out.println("1. View all vehicles");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
    }

    public void displaySearchChoices() {
        System.out.println("\n1.Search by price");
        System.out.println("2.Search by make and model");
        System.out.println("3.Search by year");
        System.out.println("4.Search by color");
        System.out.println("5.Search by mileage");
        System.out.println("6.Search by vehicle type");
        System.out.println("7.Add vehicle");
        System.out.println("8.Remove vehicle");
        System.out.println("9.Go back");
        System.out.print("Enter your choice: ");
    }
}
