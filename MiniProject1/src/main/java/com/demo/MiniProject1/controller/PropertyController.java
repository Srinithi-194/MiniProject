package com.demo.MiniProject1.controller;

import java.util.List;

import com.demo.MiniProject1.exception.DataNotFoundException;
import com.demo.MiniProject1.exception.InvalidInputException;
import com.demo.MiniProject1.model.Property;
import com.demo.MiniProject1.repository.MemoryPropertyRepository;
import com.demo.MiniProject1.repository.PropertyRepository;
import com.demo.MiniProject1.service.PropertyTaxService;
import com.demo.MiniProject1.util.InputUtil;
import com.demo.MiniProject1.view.ConsoleView;

public class PropertyController {
    private final ConsoleView view;
    private final PropertyRepository propertyRepo = new MemoryPropertyRepository();
    private final PropertyTaxService taxService = new PropertyTaxService();

    public PropertyController(ConsoleView view) {
        this.view = view;
    }

    public PropertyRepository getPropertyRepository() {
        return propertyRepo;
    }

    public void propertyMenu() {
        while (true) {
            view.printTitle("PROPERTY MENU");
            view.printLine("1. Add Property");
            view.printLine("2. Calculate Property Tax");
            view.printLine("3. Display Properties");
            view.printLine("4. Back");

            try {
                int ch = InputUtil.getInt("Choice: ");
                switch (ch) {
                    case 1 -> addProperty();
                    case 2 -> calculateTaxById();
                    case 3 -> displayProperties(true);
                    case 4 -> { return; }
                    default -> view.warn("Invalid choice.");
                }
            } catch (InvalidInputException ex) {
                view.error(ex.getMessage());
            }
        }
    }

    public void addProperty() {
        try {
            double area = InputUtil.getPositiveDouble("Enter Built-up Area: ");
            double baseValue = InputUtil.getPositiveDouble("Enter Base Value: ");
            boolean inCity = InputUtil.getYesNo("In City? (Y/N): ");
            int age = InputUtil.getPositiveInt("Age of Construction: ");

            Property p = new Property(/* id will be assigned */ 0, area, baseValue, inCity, age, 0.0);
            int id = propertyRepo.add(p);
            view.info("Property Added Successfully with ID: " + id);
        } catch (InvalidInputException ex) {
            view.error(ex.getMessage());
            // No print inside services—exceptions used
        }
    }

    public void calculateTaxById() {
        List<Property> list = propertyRepo.findAll();
        if (list.isEmpty()) {
            view.warn("No properties.");
            return;
        }
        displayProperties(false);

        try {
            int id = InputUtil.getPositiveInt("Enter Property ID to calculate tax: ");
            Property p = propertyRepo.findById(id)
                    .orElseThrow(() -> new DataNotFoundException("Property not found with ID: " + id));

            double tax = taxService.calculate(p);
            p.setTax(tax);
            propertyRepo.update(p);
            view.success(String.format("Calculated Tax for ID %d: ₹%.2f", id, tax));
        } catch (InvalidInputException | DataNotFoundException ex) {
            view.error(ex.getMessage());
        }
    }

    public void displayProperties(boolean showTotal) {
        List<Property> list = propertyRepo.findAll();
        if (list.isEmpty()) {
            view.warn("No properties.");
            return;
        }
        view.renderProperties(list);
        if (showTotal) {
            double total = list.stream().mapToDouble(Property::getTax).sum();
            view.printLine(String.format("Total Property Tax: ₹%.2f", total));
        }
    }
}
