package com.demo.MiniProject1.model;

public class Vehicle {
	

private String regNumber;
    private String brand;
    private double cost;
    private int velocity;
    private int capacity;
    private FuelType fuelType;
    private double tax;
	public String getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getVelocity() {
		return velocity;
	}
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public FuelType getFuelType() {
		return fuelType;
	}
	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public Vehicle(String regNumber, String brand, double cost, int velocity, int capacity, FuelType fuelType,
			double tax) {
		super();
		this.regNumber = regNumber;
		this.brand = brand;
		this.cost = cost;
		this.velocity = velocity;
		this.capacity = capacity;
		this.fuelType = fuelType;
		this.tax = tax;
	}

}
