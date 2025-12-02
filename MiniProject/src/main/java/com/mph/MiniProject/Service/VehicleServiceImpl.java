package com.mph.MiniProject.Service;

import com.mph.MiniProject.Model.Vehicle;
import com.mph.MiniProject.Storage.Database;
import com.mph.MiniProject.Util.InputUtil;

public class VehicleServiceImpl implements VehicleService {

    @Override
    public void addVehicle() {
        String reg = null;
        boolean valid;
        do {
            final String tempReg = InputUtil.getString("Enter 4-digit RegNo: ");
            valid = tempReg.matches("\\d{4}") && !tempReg.equals("0000");
            if (!valid) System.out.println("Invalid RegNo!");
            else if (Database.vehicles.stream().anyMatch(v -> v.getRegNumber().equals(tempReg))) {
                valid = false;
                System.out.println("RegNo already exists!");
            } else reg = tempReg;
        } while (!valid);

        String brand;
        do {
            brand = InputUtil.getString("Enter Brand: ");
            if (brand.trim().isEmpty()) System.out.println("Brand cannot be empty!");
        } while (brand.trim().isEmpty());

        double cost;
        do {
            cost = InputUtil.getDouble("Enter Cost (50,000 - 1,000,000): ");
            if (cost < 50000 || cost > 1000000) System.out.println("Invalid Cost!");
        } while (cost < 50000 || cost > 1000000);

        int vel;
        do {
            vel = InputUtil.getInt("Enter Max Velocity (120-300): ");
            if (vel < 120 || vel > 300) System.out.println("Invalid Velocity!");
        } while (vel < 120 || vel > 300);

        int cap;
        do {
            cap = InputUtil.getInt("Enter Capacity (2-50): ");
            if (cap < 2 || cap > 50) System.out.println("Invalid Capacity!");
        } while (cap < 2 || cap > 50);

        int type;
        do {
            type = InputUtil.getInt("Enter Type (1=Petrol,2=Diesel,3=CNG): ");
            if (type < 1 || type > 3) System.out.println("Invalid Type!");
        } while (type < 1 || type > 3);

        Vehicle v = new Vehicle(reg, brand, cost, vel, cap, type);
        Database.vehicles.add(v);
        System.out.println("Vehicle Added Successfully!");
    }

    @Override
    public void calculateTaxByReg() {
        if (Database.vehicles.isEmpty()) {
            System.out.println("No vehicles.");
            return;
        }
        displayVehicles(false);
        String reg = InputUtil.getString("Enter Vehicle RegNo to calculate tax: ");
        Vehicle v = Database.vehicles.stream()
                .filter(vehicle -> vehicle.getRegNumber().equals(reg))
                .findFirst()
                .orElse(null);
        if (v != null) {
            v.calculateTax();
            System.out.println("Calculated Tax: ₹" + v.getTax());
        } else System.out.println("Vehicle not found!");
    }

    @Override
    public void displayVehicles(boolean showTotal) {
        if (Database.vehicles.isEmpty()) {
            System.out.println("No vehicles.");
            return;
        }
        System.out.println("\nRegNo  Brand      Cost        Vel  Cap  Tax");
        for (Vehicle v : Database.vehicles) {
            System.out.printf("%-6s %-10s %-10.2f %-4d %-4d %-7.2f\n",
                    v.getRegNumber(), v.getBrand(), v.getCost(),
                    v.getVelocity(), v.getCapacity(), v.getTax());
        }
        if (showTotal)
            System.out.printf("Total Vehicle Tax: ₹%.2f\n", getTotalVehicleTax());
    }

    @Override
    public double getTotalVehicleTax() {
        return Database.vehicles.stream().mapToDouble(Vehicle::getTax).sum();
    }
}
