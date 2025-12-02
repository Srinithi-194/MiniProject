package com.mph.MiniProject.Service;

public interface VehicleService {
    void addVehicle();
    void calculateTaxByReg();
    void displayVehicles(boolean showTotal);
    double getTotalVehicleTax();
}
