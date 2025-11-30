package com.mph.MiniProject.Model;


public class Vehicle implements Asset{

	private String regNumber;
    private String brand;
    private double cost;
    private int velocity;
    private int capacity;
    private int type;
    private double tax;

    public Vehicle(String regNumber, String brand, double cost, int velocity, int capacity, int type) {
        this.regNumber = regNumber;
        this.brand = brand;
        this.cost = cost;
        this.velocity = velocity;
        this.capacity = capacity;
        this.type = type;
    }

    @Override
    public double calculateTax() {
        switch (type) {
            case 1: tax = velocity + capacity + (0.10 * cost); break;
            case 2: tax = velocity + capacity + (0.11 * cost); break;
            case 3: tax = velocity + capacity + (0.12 * cost); break;
        }
        return tax;
    }

    public double getTax() {
        return tax;
	}

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}
    
    

}
