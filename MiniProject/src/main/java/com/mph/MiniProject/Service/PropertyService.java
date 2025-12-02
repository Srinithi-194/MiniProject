package com.mph.MiniProject.Service;

public interface PropertyService {
    void addProperty();
    void calculateTaxById();
    void displayProperties(boolean showTotal);
    double getTotalPropertyTax();
}
