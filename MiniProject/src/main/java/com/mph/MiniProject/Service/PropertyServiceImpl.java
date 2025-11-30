package com.mph.MiniProject.Service;

import java.util.List;

import com.mph.MiniProject.Model.Property;
import com.mph.MiniProject.Storage.Database;

public class PropertyServiceImpl implements PropertyService{

	@Override
    public void addProperty(Property p) {
        Database.properties.add(p);
    }

    @Override
    public List<Property> getAllProperties() {
        return Database.properties;
    }

    @Override
    public double getTotalPropertyTax() {
        return Database.properties.stream()
                .mapToDouble(Property::getTax)
                .sum();
	}

}
