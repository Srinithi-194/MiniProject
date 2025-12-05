package com.demo.MiniProject1.model;

public class Property {


	 private int id;
	    private double builtUpArea;
	    private double baseValue;
	    private boolean inCity;
	    private int age;
	    private double tax;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
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
		public double getTax() {
			return tax;
		}
		public void setTax(double tax) {
			this.tax = tax;
		}
		public Property(int id, double builtUpArea, double baseValue, boolean inCity, int age, double tax) {
			super();
			this.id = id;
			this.builtUpArea = builtUpArea;
			this.baseValue = baseValue;
			this.inCity = inCity;
			this.age = age;
			this.tax = tax;
		}

	    
	
}
