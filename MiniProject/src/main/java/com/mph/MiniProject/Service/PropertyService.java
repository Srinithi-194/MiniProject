package com.mph.MiniProject.Service;

import java.util.List;

import com.mph.MiniProject.Model.Property;

public interface PropertyService {
	
	void addProperty(Property p);
    List<Property> getAllProperties();
    double getTotalPropertyTax();

}
