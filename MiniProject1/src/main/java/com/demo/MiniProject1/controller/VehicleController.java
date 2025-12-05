package com.demo.MiniProject1.controller;

import java.util.List;

import com.demo.MiniProject1.exception.DataNotFoundException;
import com.demo.MiniProject1.exception.InvalidInputException;
import com.demo.MiniProject1.model.FuelType;
import com.demo.MiniProject1.model.Vehicle;
import com.demo.MiniProject1.repository.MemoryVehicleRepository;
import com.demo.MiniProject1.repository.VehicleRepository;
import com.demo.MiniProject1.service.VehicleTaxService;
import com.demo.MiniProject1.util.InputUtil;
import com.demo.MiniProject1.view.ConsoleView;

public class VehicleController {
    private final ConsoleView view;
    private final VehicleRepository vehicleRepo = new MemoryVehicleRepository();
    private final VehicleTaxService taxService = new VehicleTaxService();

    public VehicleController(ConsoleView view) {
        this.view = view;
    }

    public VehicleRepository getVehicleRepository() {
        return vehicleRepo;
    }

    public void vehicleMenu() {
        while (true) {
            view.printTitle("VEHICLE MENU");
            view.printLine("1. Add Vehicle");
            view.printLine("2. Calculate Vehicle Tax");
            view.printLine("3. Display Vehicles");
            view.printLine("4. Back");

            try {
                int ch = InputUtil.getInt("Choice: ");
                switch (ch) {
                    case 1 -> addVehicle();
                    case 2 -> calculateTaxByReg();
                    case 3 -> displayVehicles(true);
                    case 4 -> { return; }
                    default -> view.warn("Invalid choice.");
                }
            } catch (InvalidInputException ex) {
                view.error(ex.getMessage());
            }
        }
    }

    public void addVehicle() {
        try {
            String reg = InputUtil.getRegex("Enter 4-digit RegNo: ", "\\d{4}", "RegNo must be exactly 4 digits.");
            if ("0000".equals(reg)) throw new InvalidInputException("RegNo cannot be 0000.");
            if (vehicleRepo.existsByReg(reg)) throw new InvalidInputException("RegNo already exists.");

            String brand = InputUtil.getNonEmptyString("Enter Brand: ");
            double cost = InputUtil.getDoubleInRange("Enter Cost (50,000 - 1,000,000): ", 50000, 1000000);
            int vel = InputUtil.getIntInRange("Enter Max Velocity (120-300): ", 120, 300);
            int cap = InputUtil.getIntInRange("Enter Capacity (2-50): ", 2, 50);

            int t = InputUtil.getIntInRange("Enter Type (1=Petrol,2=Diesel,3=CNG): ", 1, 3);
            FuelType type = FuelType.fromInt(t);

            Vehicle v = new Vehicle(reg, brand, cost, vel, cap, type, 0.0);
            vehicleRepo.add(v);
            view.info("Vehicle Added Successfully!");
        } catch (InvalidInputException ex) {
            view.error(ex.getMessage());
        }
    }

    public void calculateTaxByReg() {
        List<Vehicle> list = vehicleRepo.findAll();
        if (list.isEmpty()) {
            view.warn("No vehicles.");
            return;
        }
        displayVehicles(false);

        try {
            String reg = InputUtil.getRegex("Enter Vehicle RegNo to calculate tax: ", "\\d{4}", "RegNo must be exactly 4 digits.");
            Vehicle v = vehicleRepo.findByReg(reg)
                    .orElseThrow(() -> new DataNotFoundException("Vehicle not found with RegNo: " + reg));

            double tax = taxService.calculate(v);
            v.setTax(tax);
            vehicleRepo.update(v);
            view.success(String.format("Calculated Tax for RegNo %s: ₹%.2f", reg, tax));
        } catch (InvalidInputException | DataNotFoundException ex) {
            view.error(ex.getMessage());
        }
    }

    public void displayVehicles(boolean showTotal) {
        List<Vehicle> list = vehicleRepo.findAll();
        if (list.isEmpty()) {
            view.warn("No vehicles.");
            return;
        }
        view.renderVehicles(list);
        if (showTotal) {
            double total = list.stream().mapToDouble(Vehicle::getTax).sum();
            view.printLine(String.format("Total Vehicle Tax: ₹%.2f", total));
        }
    }
}
