package com.demo.MiniProject1.service;

import com.demo.MiniProject1.repository.PropertyRepository;
import com.demo.MiniProject1.repository.VehicleRepository;

public class TotalService {


private final PropertyRepository propertyRepo;
    private final VehicleRepository vehicleRepo;

    public TotalService(PropertyRepository propertyRepo, VehicleRepository vehicleRepo) {
        this.propertyRepo = propertyRepo;
        this.vehicleRepo = vehicleRepo;
    }

    public double getTotalPropertyTax() {
        return propertyRepo.findAll().stream().mapToDouble(p -> p.getTax()).sum();
    }

    public double getTotalVehicleTax() {
        return vehicleRepo.findAll().stream().mapToDouble(v -> v.getTax()).sum();
    }

}
