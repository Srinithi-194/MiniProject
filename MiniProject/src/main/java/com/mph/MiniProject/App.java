package com.mph.MiniProject;

import com.mph.MiniProject.Service.*;
import com.mph.MiniProject.Util.InputUtil;

public class App {
    static LoginService loginService = new LoginServiceImpl();
    static PropertyService propertyService = new PropertyServiceImpl();
    static VehicleService vehicleService = new VehicleServiceImpl();

    public static void main(String[] args) {
        boolean authenticated = false;
        while (!authenticated) {
            String user = InputUtil.getString("Enter Username: ");
            String pass = InputUtil.getString("Enter Password: ");
            if (loginService.login(user, pass)) authenticated = true;
            else System.out.println("Invalid Credentials!");
        }

        boolean run = true;
        while (run) {
            System.out.println("\nMAIN MENU");
            System.out.println("1. Property Menu");
            System.out.println("2. Vehicle Menu");
            System.out.println("3. Display All Taxes");
            System.out.println("4. Exit");

            int choice = InputUtil.getInt("Enter choice: ");
            switch (choice) {
                case 1: propertyMenu(); break;
                case 2: vehicleMenu(); break;
                case 3: displayAllTaxes(); break; // <-- updated
                case 4: run = false; break;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    private static void propertyMenu() {
        boolean prun = true;
        while (prun) {
            System.out.println("\nPROPERTY MENU");
            System.out.println("1. Add Property");
            System.out.println("2. Calculate Property Tax");
            System.out.println("3. Display Properties");
            System.out.println("4. Back");

            int ch = InputUtil.getInt("Choice: ");
            switch (ch) {
                case 1: propertyService.addProperty(); break;
                case 2: propertyService.calculateTaxById(); break;
                case 3: propertyService.displayProperties(false); break;
                case 4: prun = false; break;
            }
        }
    }

    private static void vehicleMenu() {
        boolean vrun = true;
        while (vrun) {
            System.out.println("\nVEHICLE MENU");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Calculate Vehicle Tax");
            System.out.println("3. Display Vehicles");
            System.out.println("4. Back");

            int ch = InputUtil.getInt("Choice: ");
            switch (ch) {
                case 1: vehicleService.addVehicle(); break;
                case 2: vehicleService.calculateTaxByReg(); break;
                case 3: vehicleService.displayVehicles(false); break;
                case 4: vrun = false; break;
            }
        }
    }

 
    private static void displayAllTaxes() {
        System.out.println("\n--- PROPERTIES ---");
        propertyService.displayProperties(false);

        System.out.println("\n--- VEHICLES ---");
        vehicleService.displayVehicles(false);

        double totalPropertyTax = propertyService.getTotalPropertyTax();
        double totalVehicleTax = vehicleService.getTotalVehicleTax();
        double grandTotal = totalPropertyTax + totalVehicleTax;

        System.out.printf("\nTotal Property Tax: ₹%.2f\n", totalPropertyTax);
        System.out.printf("Total Vehicle Tax: ₹%.2f\n", totalVehicleTax);
        System.out.printf("Grand Total Tax Payable: ₹%.2f\n", grandTotal);
    }
}
