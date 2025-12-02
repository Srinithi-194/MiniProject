package com.mph.MiniProject.Service;

import com.mph.MiniProject.Model.Property;
import com.mph.MiniProject.Storage.Database;
import com.mph.MiniProject.Util.InputUtil;

public class PropertyServiceImpl implements PropertyService {

    private static int idCounter = 1;

    @Override
    public void addProperty() {
        double area;
        do {
            area = InputUtil.getDouble("Enter Built-up Area: ");
            if (area <= 0) System.out.println("Built-up area must be positive.");
        } while (area <= 0);

        double baseValue;
        do {
            baseValue = InputUtil.getDouble("Enter Base Value: ");
            if (baseValue <= 0) System.out.println("Base value must be positive.");
        } while (baseValue <= 0);

        boolean inCity = false;
        while (true) {
            String city = InputUtil.getString("In City? (Y/N): ").toUpperCase();
            if (city.equals("Y")) { inCity = true; break; }
            else if (city.equals("N")) break;
            else System.out.println("Enter Y or N.");
        }

        int age;
        do {
            age = InputUtil.getInt("Age of Construction: ");
            if (age <= 0) System.out.println("Age must be positive.");
        } while (age <= 0);

        Property p = new Property(idCounter++, area, baseValue, inCity, age);
        Database.properties.add(p);
        System.out.println("Property Added Successfully!");
    }

    @Override
    public void calculateTaxById() {
        if (Database.properties.isEmpty()) {
            System.out.println("No properties.");
            return;
        }

        displayProperties(false);
        int id = InputUtil.getInt("Enter Property ID to calculate tax: ");
        Property p = Database.properties.stream()
                .filter(prop -> prop.getId() == id)
                .findFirst()
                .orElse(null);

        if (p != null) {
            p.calculateTax();
            System.out.println("Calculated Tax: ₹" + p.getTax());
        } else System.out.println("Property not found!");
    }

    @Override
    public void displayProperties(boolean showTotal) {
        if (Database.properties.isEmpty()) {
            System.out.println("No properties.");
            return;
        }

        System.out.println("\nID  Area       BaseValue  InCity  Age  Tax");
        for (Property p : Database.properties) {
            System.out.printf("%-3d %-10.2f %-10.2f %-6s %-4d %-7.2f\n",
                    p.getId(), p.getBuiltUpArea(), p.getBaseValue(),
                    p.isInCity() ? "Yes" : "No", p.getAge(), p.getTax());
        }

        if (showTotal)
            System.out.printf("Total Property Tax: ₹%.2f\n", getTotalPropertyTax());
    }

    @Override
    public double getTotalPropertyTax() {
        return Database.properties.stream().mapToDouble(Property::getTax).sum();
    }
}
