package com.demo.MiniProject1.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.demo.MiniProject1.model.Vehicle;

public class MemoryVehicleRepository implements VehicleRepository{


	 private final List<Vehicle> vehicles = new ArrayList<>();

	    @Override
	    public void add(Vehicle vehicle) { vehicles.add(vehicle); }

	    @Override
	    public void update(Vehicle vehicle) { /* in-memory, no-op */ }

	    @Override
	    public Optional<Vehicle> findByReg(String reg) {
	        return vehicles.stream().filter(v -> v.getRegNumber().equals(reg)).findFirst();
	    }

	    @Override
	    public List<Vehicle> findAll() {
	        return new ArrayList<>(vehicles);
	    }

	    @Override
	    public boolean existsByReg(String reg) {
	        return vehicles.stream().anyMatch(v -> v.getRegNumber().equals(reg));
	    }


}
