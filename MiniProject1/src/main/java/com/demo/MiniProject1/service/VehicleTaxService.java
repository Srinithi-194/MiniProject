package com.demo.MiniProject1.service;

import com.demo.MiniProject1.model.Vehicle;

public class VehicleTaxService {


public double calculate(Vehicle v) {
        double rate = v.getFuelType().rate();
        return v.getVelocity() + v.getCapacity() + (rate * v.getCost());
    }

}
