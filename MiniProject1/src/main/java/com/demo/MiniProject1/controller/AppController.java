package com.demo.MiniProject1.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import com.demo.MiniProject1.exception.AuthenticationException;
import com.demo.MiniProject1.exception.InvalidInputException;
import com.demo.MiniProject1.service.AuthService;
import com.demo.MiniProject1.service.AuthServiceImpl;
import com.demo.MiniProject1.service.TotalService;
import com.demo.MiniProject1.util.InputUtil;
import com.demo.MiniProject1.view.ConsoleView;

public class AppController {

	

private final ConsoleView view = new ConsoleView();
    private final AuthService authService = new AuthServiceImpl();
    private final PropertyController propertyController = new PropertyController(view);
    private final VehicleController vehicleController = new VehicleController(view);
    private final TotalService totalsService = new TotalService(
            propertyController.getPropertyRepository(),
            vehicleController.getVehicleRepository()
    );

    public void start() {
        authenticateLoop();
        mainMenuLoop();
    }

    private void authenticateLoop() {
        view.printTitle("LOGIN");
        while (true) {
            try {
                String user = InputUtil.getString("Enter Username: ");
                String pass = InputUtil.getString("Enter Password: ");
                authService.login(user, pass); // throws on failure
                view.info("Login successful.");
                return;
            } catch (AuthenticationException ex) {
                view.error(ex.getMessage());
            }
        }
    }

    private void mainMenuLoop() {
        Map<Integer, Runnable> actions = new LinkedHashMap<>();
        actions.put(1, propertyController::propertyMenu);
        actions.put(2, vehicleController::vehicleMenu);
        actions.put(3, this::displayAllTaxes);
        actions.put(4, () -> {
            view.info("Goodbye!");
            System.exit(0);
        });

        while (true) {
            view.printTitle("MAIN MENU");
            view.printLine("1. Property Menu");
            view.printLine("2. Vehicle Menu");
            view.printLine("3. Display All Taxes");
            view.printLine("4. Exit");

            try {
                int choice = InputUtil.getInt("Enter choice: ");
                Runnable action = actions.get(choice);
                if (action != null) action.run();
                else view.warn("Invalid choice.");
            } catch (InvalidInputException ex) {
                view.error(ex.getMessage());
            }
        }
    }

    private void displayAllTaxes() {
        view.printTitle("ALL TAXES");
        propertyController.displayProperties(false);
        vehicleController.displayVehicles(false);

        double totalPropertyTax = totalsService.getTotalPropertyTax();
        double totalVehicleTax = totalsService.getTotalVehicleTax();
        double grandTotal = totalPropertyTax + totalVehicleTax;

        view.printTotals(totalPropertyTax, totalVehicleTax, grandTotal);
    }

}
