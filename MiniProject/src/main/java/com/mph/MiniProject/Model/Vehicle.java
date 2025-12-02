package com.mph.MiniProject.Model;

public class Vehicle implements Asset {

    private String regNumber;
    private String brand;
    private double cost;
    private int velocity;
    private int capacity;
    private int type; // 1=Petrol, 2=Diesel, 3=CNG
    private double tax;

    public Vehicle(String regNumber, String brand, double cost, int velocity, int capacity, int type) {
        this.regNumber = regNumber;
        this.brand = brand;
        this.cost = cost;
        this.velocity = velocity;
        this.capacity = capacity;
        this.type = type;
        this.tax = 0;
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

    // Getters
    public String getRegNumber() { return regNumber; }
    public String getBrand() { return brand; }
    public double getCost() { return cost; }
    public int getVelocity() { return velocity; }
    public int getCapacity() { return capacity; }
    public int getType() { return type; }
    public double getTax() { return tax; }
}
