package com.mph.MiniProject.Model;

public class Property implements Asset{

	private double builtUpArea;
    private double baseValue;
    private boolean inCity;
    private int age;
    private double tax;

    public Property(double builtUpArea, double baseValue, boolean inCity, int age) {
        this.builtUpArea = builtUpArea;
        this.baseValue = baseValue;
        this.inCity = inCity;
        this.age = age;
    }

    @Override
    public double calculateTax() {
        double ageFactor = age / 10.0;

        if (inCity) {
            tax = (builtUpArea * ageFactor * baseValue) + (0.5 * builtUpArea);
        } else {
            tax = builtUpArea * ageFactor * baseValue;
        }
        return tax;
    }

    public double getTax() {
        return tax;
	}

	public double getBuiltUpArea() {
		return builtUpArea;
	}

	public void setBuiltUpArea(double builtUpArea) {
		this.builtUpArea = builtUpArea;
	}

	public double getBaseValue() {
		return baseValue;
	}

	public void setBaseValue(double baseValue) {
		this.baseValue = baseValue;
	}

	public boolean isInCity() {
		return inCity;
	}

	public void setInCity(boolean inCity) {
		this.inCity = inCity;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}
    
    

}
