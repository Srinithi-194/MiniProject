package com.mph.MiniProject.Model;

public class Property implements Asset {

    private int id;
    private double builtUpArea;
    private double baseValue;
    private boolean inCity;
    private int age;
    private double tax;

    public Property(int id, double builtUpArea, double baseValue, boolean inCity, int age) {
        this.id = id;
        this.builtUpArea = builtUpArea;
        this.baseValue = baseValue;
        this.inCity = inCity;
        this.age = age;
        this.tax = 0;
    }

    @Override
    public double calculateTax() {
        double ageFactor = age / 10.0;
        if (inCity) tax = (builtUpArea * ageFactor * baseValue) + (0.5 * builtUpArea);
        else tax = builtUpArea * ageFactor * baseValue;
        return tax;
    }

    // Getters and Setters
    public int getId() { return id; }
    public double getBuiltUpArea() { return builtUpArea; }
    public double getBaseValue() { return baseValue; }
    public boolean isInCity() { return inCity; }
    public int getAge() { return age; }
    public double getTax() { return tax; }
}
