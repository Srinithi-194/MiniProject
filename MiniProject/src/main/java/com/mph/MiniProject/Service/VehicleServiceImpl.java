package com.mph.MiniProject.Service;

import java.util.List;

import com.mph.MiniProject.Model.Vehicle;
import com.mph.MiniProject.Storage.Database;

public class VehicleServiceImpl implements VehicleService{


    @Override
    public void addVehicle(Vehicle v) {
        Database.vehicles.add(v);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return Database.vehicles;
    }

    @Override
    public double getTotalVehicleTax() {
        return Database.vehicles.stream()
                .mapToDouble(Vehicle::getTax)
                .sum();
	}

}
