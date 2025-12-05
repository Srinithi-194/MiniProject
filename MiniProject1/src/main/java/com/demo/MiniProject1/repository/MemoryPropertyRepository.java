package com.demo.MiniProject1.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.demo.MiniProject1.model.Property;

public class MemoryPropertyRepository implements PropertyRepository{


private final List<Property> properties = new ArrayList<>();
    private int idSeq = 1;

    @Override
    public int add(Property property) {
        if (property.getId() == 0) {
            property.setId(idSeq++);
        }
        properties.add(property);
        return property.getId();
    }

    @Override
    public void update(Property property) {
        // In-memory list, nothing special needed
    }

    @Override
    public Optional<Property> findById(int id) {
        return properties.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public List<Property> findAll() {
        return new ArrayList<>(properties);
    }

    @Override
    public boolean existsId(int id) {
        return properties.stream().anyMatch(p -> p.getId() == id);
    }


}
