package com.demo.MiniProject1.service;

import com.demo.MiniProject1.model.Property;

public class PropertyTaxService {


public double calculate(Property p) {
        double ageFactor = p.getAge() / 10.0;
        double base = p.getBuiltUpArea() * ageFactor * p.getBaseValue();
        if (p.isInCity()) {
            base += (0.5 * p.getBuiltUpArea());
        }
        return base;
    }

}
