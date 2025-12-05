package com.demo.MiniProject1.view;

import java.util.List;

import com.demo.MiniProject1.model.Property;
import com.demo.MiniProject1.model.Vehicle;

public class ConsoleView {

	

public void printTitle(String title) {
        System.out.println("\n=== " + title + " ===");
    }

    public void printLine(String msg) {
        System.out.println(msg);
    }

    public void info(String msg) {
        System.out.println("[INFO] " + msg);
    }

    public void warn(String msg) {
        System.out.println("[WARN] " + msg);
    }

    public void error(String msg) {
        System.out.println("[ERROR] " + msg);
    }

    public void success(String msg) {
        System.out.println("[SUCCESS] " + msg);
    }

    public void renderProperties(List<Property> properties) {
        System.out.println("\nID  Area       BaseValue  InCity  Age  Tax");
        for (Property p : properties) {
            System.out.printf("%-3d %-10.2f %-10.2f %-6s %-4d %-7.2f%n",
                    p.getId(),
                    p.getBuiltUpArea(),
                    p.getBaseValue(),
                    p.isInCity() ? "Yes" : "No",
                    p.getAge(),
                    p.getTax());
        }
    }

    public void renderVehicles(List<Vehicle> vehicles) {
        System.out.println("\nRegNo  Brand      Cost        Vel  Cap  Type   Tax");
        for (Vehicle v : vehicles) {
            System.out.printf("%-6s %-10s %-10.2f %-4d %-4d %-6s %-7.2f%n",
                    v.getRegNumber(),
                    v.getBrand(),
                    v.getCost(),
                    v.getVelocity(),
                    v.getCapacity(),
                    v.getFuelType().name(),
                    v.getTax());
        }
    }

    public void printTotals(double totalPropertyTax, double totalVehicleTax, double grandTotal) {
        System.out.printf("%nTotal Property Tax: ₹%.2f%n", totalPropertyTax);
        System.out.printf("Total Vehicle Tax: ₹%.2f%n", totalVehicleTax);
        System.out.printf("Grand Total Tax Payable: ₹%.2f%n", grandTotal);
    }

}
