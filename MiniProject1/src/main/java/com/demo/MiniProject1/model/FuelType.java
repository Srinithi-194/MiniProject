package com.demo.MiniProject1.model;

public enum FuelType {
	

PETROL(0.10), DIESEL(0.11), CNG(0.12);

    private final double rate;

    FuelType(double rate) {
        this.rate = rate;
    }

    public double rate() {
        return rate;
    }

    public static FuelType fromInt(int i) {
        return switch (i) {
            case 1 -> PETROL;
            case 2 -> DIESEL;
            case 3 -> CNG;
            default -> throw new IllegalArgumentException("Invalid fuel type: " + i);
        };
    }


}
