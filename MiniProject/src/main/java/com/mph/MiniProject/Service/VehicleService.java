package com.mph.MiniProject.Service;

import java.util.List;

import com.mph.MiniProject.Model.Vehicle;

public interface VehicleService {
	
	 void addVehicle(Vehicle v);
	    List<Vehicle> getAllVehicles();
	    double getTotalVehicleTax();

}
