package com.demo.MiniProject1.repository;

import java.util.List;
import java.util.Optional;

import com.demo.MiniProject1.model.Property;

public interface PropertyRepository {
	

int add(Property property);              // returns assigned ID
    void update(Property property);
    Optional<Property> findById(int id);
    List<Property> findAll();
    boolean existsId(int id);


}
