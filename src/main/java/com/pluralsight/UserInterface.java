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
                System.out.println("Please only enter 1, or 2.");
                scanner.nextLine();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> processGetAllVehiclesRequest();
                case 2 -> System.out.println("Thank you for shopping with us!");
                default -> System.out.println("Invalid choice");
            }
        }
        scanner.close();
    }

    public void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    public void displayVehicles(List<Vehicle> vehicles) {
        System.out.println("\n========================== Inventory ==========================");
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found");
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
        System.out.println("\n Welcome to the Car Dealership!");
        System.out.println("1. View all vehicles");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
    }
}
