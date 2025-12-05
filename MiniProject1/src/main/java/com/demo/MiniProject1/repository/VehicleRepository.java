package com.demo.MiniProject1.repository;

import java.util.List;
import java.util.Optional;

import com.demo.MiniProject1.model.Vehicle;

public interface VehicleRepository {


void add(Vehicle vehicle);
    void update(Vehicle vehicle);
    Optional<Vehicle> findByReg(String reg);
    List<Vehicle> findAll();
    boolean existsByReg(String reg);

}
