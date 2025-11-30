package com.mph.MiniProject;

import com.mph.MiniProject.Model.Property;
import com.mph.MiniProject.Model.Vehicle;
import com.mph.MiniProject.Service.*;
import com.mph.MiniProject.Storage.Database;
import com.mph.MiniProject.Util.InputUtil;

public class App {

    static PropertyService propertyService = new PropertyServiceImpl();
    static VehicleService vehicleService = new VehicleServiceImpl();
    static LoginService loginService = new LoginServiceImpl();

    public static void main(String[] args) {


        boolean authenticated = false;
        while (!authenticated) {
            String user = InputUtil.getString("Enter Username: ");
            String pass = InputUtil.getString("Enter Password: ");

            if (loginService.login(user, pass)) {
                System.out.println("Login Successful! Welcome.\n");
                authenticated = true;
            } else {
                System.out.println("Invalid Credentials! Try Again.\n");
            }
        }

        boolean run = true;
        while (run) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Property Menu");
            System.out.println("2. Vehicle Menu");
            System.out.println("3. Display All Taxes");
            System.out.println("4. Exit");

            int choice = InputUtil.getInt("Enter your choice: ");
            switch (choice) {
                case 1: propertyMenu(); break;
                case 2: vehicleMenu(); break;
                case 3: displayAllTaxes(); break;
                case 4:
                    System.out.println("Exiting System...");
                    run = false;
                    break;
                default: System.out.println("Invalid Choice! Enter a number between 1-4.");
            }
        }
    }

    private static void propertyMenu() {
        boolean propertyRun = true;
        while (propertyRun) {
            System.out.println("\n--- PROPERTY MENU ---");
            System.out.println("1. Add Property");
            System.out.println("2. Calculate Property Tax & Display");
            System.out.println("3. Display Properties");
            System.out.println("4. Back to Main Menu");

            int choice = InputUtil.getInt("Enter your choice: ");
            switch (choice) {
                case 1: addPropertyUI(); break;
                case 2: calculateAndDisplayProperties(); break;
                case 3: displayProperties(false); break;
                case 4: propertyRun = false; break;
                default: System.out.println("Invalid Choice! Enter a number between 1-4.");
            }
        }
    }


    private static void vehicleMenu() {
        boolean vehicleRun = true;
        while (vehicleRun) {
            System.out.println("\n--- VEHICLE MENU ---");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Calculate Vehicle Tax & Display");
            System.out.println("3. Display Vehicles");
            System.out.println("4. Back to Main Menu");

            int choice = InputUtil.getInt("Enter your choice: ");
            switch (choice) {
                case 1: addVehicleUI(); break;
                case 2: calculateAndDisplayVehicles(); break;
                case 3: displayVehicles(false); break;
                case 4: vehicleRun = false; break;
                default: System.out.println("Invalid Choice! Enter a number between 1-4.");
            }
        }
    }


    private static void addPropertyUI() {
        double area;
        do {
            area = InputUtil.getDouble("Enter Built-up Area: ");
            if (area <= 0) System.out.println("Built-up area must be positive.");
        } while (area <= 0);

        double baseValue;
        do {
            baseValue = InputUtil.getDouble("Enter Base Value: ");
            if (baseValue <= 0) System.out.println("Base value must be positive and non-zero.");
        } while (baseValue <= 0);

        String city;
        boolean validCity = false;
        boolean inCity = false;
        do {
            city = InputUtil.getString("In City? (Y/N): ").toUpperCase();
            if (city.equals("Y") || city.equals("N")) {
                validCity = true;
                inCity = city.equals("Y");
            } else {
                System.out.println("Invalid input! Enter 'Y' or 'N'.");
            }
        } while (!validCity);

        int age;
        do {
            age = InputUtil.getInt("Age of Construction: ");
            if (age <= 0) System.out.println("Age must be positive and non-zero.");
        } while (age <= 0);

        Property p = new Property(area, baseValue, inCity, age);
        propertyService.addProperty(p);
        System.out.println("Property Added Successfully!");
    }

    private static void calculateAndDisplayProperties() {
        if (Database.properties.isEmpty()) {
            System.out.println("No Properties to calculate tax!");
            return;
        }

        System.out.println("\n--- Calculated Property Taxes ---");
        System.out.printf("%-5s %-10s %-12s %-10s %-10s\n", "No", "Area", "BaseValue", "InCity", "Tax");
        int count = 1;
        for (Property p : Database.properties) {
            p.calculateTax();
            System.out.printf("%-5d %-10.2f %-12.2f %-10s %-10.2f\n",
                    count++, p.getBuiltUpArea(), p.getBaseValue(),
                    p.isInCity() ? "Yes" : "No", p.getTax());
        }
    }

    private static void displayProperties(boolean showTotal) {
        if (Database.properties.isEmpty()) {
            System.out.println("No Properties Added.");
            return;
        }
        System.out.println("\n--- Properties ---");
        System.out.printf("%-5s %-10s %-12s %-10s %-10s\n", "No", "Area", "BaseValue", "InCity", "Tax");
        int count = 1;
        for (Property p : Database.properties) {
            System.out.printf("%-5d %-10.2f %-12.2f %-10s %-10.2f\n",
                    count++, p.getBuiltUpArea(), p.getBaseValue(),
                    p.isInCity() ? "Yes" : "No", p.getTax());
        }
        if (showTotal) {
            System.out.printf("Total Property Tax: ₹%.2f\n", propertyService.getTotalPropertyTax());
        }
    }


    private static void addVehicleUI() {
        String reg;
        boolean validReg;
        do {
            reg = InputUtil.getString("Enter 4-digit Registration Number: ");
            validReg = reg.matches("\\d{4}") && !reg.equals("0000");
            if (!validReg) System.out.println("Invalid! Enter a 4-digit number not 0000.");
            else {
                for (Vehicle v : Database.vehicles) {
                    if (v.getRegNumber().equals(reg)) {
                        validReg = false;
                        System.out.println("Registration number already exists!");
                        break;
                    }
                }
            }
        } while (!validReg);

        String brand;
        do {
            brand = InputUtil.getString("Enter Brand: ");
            if (brand.trim().isEmpty()) System.out.println("Brand cannot be empty!");
        } while (brand.trim().isEmpty());

        double cost;
        do {
            cost = InputUtil.getDouble("Enter Cost (50,000 - 1,000,000): ");
            if (cost < 50000 || cost > 1000000)
                System.out.println("Cost must be between 50,000 and 1,000,000.");
        } while (cost < 50000 || cost > 1000000);

        int velocity;
        do {
            velocity = InputUtil.getInt("Enter Max Velocity (120-300 kmph): ");
            if (velocity < 120 || velocity > 300) System.out.println("Velocity must be 120-300 kmph.");
        } while (velocity < 120 || velocity > 300);

        int capacity;
        do {
            capacity = InputUtil.getInt("Enter Capacity (2-50): ");
            if (capacity < 2 || capacity > 50) System.out.println("Capacity must be 2-50.");
        } while (capacity < 2 || capacity > 50);

        int type;
        do {
            type = InputUtil.getInt("Enter Type (1=Petrol, 2=Diesel, 3=CNG): ");
            if (type < 1 || type > 3) System.out.println("Invalid type! Choose 1, 2, or 3.");
        } while (type < 1 || type > 3);

        Vehicle v = new Vehicle(reg, brand, cost, velocity, capacity, type);
        vehicleService.addVehicle(v);
        System.out.println("Vehicle Added Successfully!");
    }


    private static void calculateAndDisplayVehicles() {
        if (Database.vehicles.isEmpty()) {
            System.out.println("No Vehicles to calculate tax!");
            return;
        }

        System.out.println("\n--- Calculated Vehicle Taxes ---");
        System.out.printf("%-5s %-12s %-10s %-10s %-10s %-10s\n", "No", "RegNo", "Brand", "Cost", "Type", "Tax");
        int count = 1;
        for (Vehicle v : Database.vehicles) {
            v.calculateTax();
            String typeStr = v.getType() == 1 ? "Petrol" : v.getType() == 2 ? "Diesel" : "CNG";
            System.out.printf("%-5d %-12s %-10s %-10.2f %-10s %-10.2f\n",
                    count++, v.getRegNumber(), v.getBrand(),
                    v.getCost(), typeStr, v.getTax());
        }
    }

    private static void displayVehicles(boolean showTotal) {
        if (Database.vehicles.isEmpty()) {
            System.out.println("No Vehicles Added.");
            return;
        }
        System.out.println("\n--- Vehicles ---");
        System.out.printf("%-5s %-12s %-10s %-10s %-10s %-10s\n", "No", "RegNo", "Brand", "Cost", "Type", "Tax");
        int count = 1;
        for (Vehicle v : Database.vehicles) {
            String typeStr = v.getType() == 1 ? "Petrol" : v.getType() == 2 ? "Diesel" : "CNG";
            System.out.printf("%-5d %-12s %-10s %-10.2f %-10s %-10.2f\n",
                    count++, v.getRegNumber(), v.getBrand(),
                    v.getCost(), typeStr, v.getTax());
        }
        if (showTotal) {
            System.out.printf("Total Vehicle Tax: ₹%.2f\n", vehicleService.getTotalVehicleTax());
        }
    }


    private static void displayAllTaxes() {
        displayProperties(true);
        displayVehicles(true);
        double totalTax = propertyService.getTotalPropertyTax() + vehicleService.getTotalVehicleTax();
        System.out.printf("\nGrand Total Tax Payable: ₹%.2f\n", totalTax);
    }
}
