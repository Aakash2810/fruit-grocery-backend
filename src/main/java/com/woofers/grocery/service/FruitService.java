package com.woofers.grocery.service;

import com.woofers.grocery.entity.Fruit;
import com.woofers.grocery.repository.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitService {

    private final FruitRepository repository;

    public FruitService(FruitRepository repository) {
        this.repository = repository;
    }

    public Fruit addFruit(Fruit fruit) {
        return repository.save(fruit);
    }

    public List<Fruit> getAll() {
        return repository.findAll();
    }

    public Fruit updateFruit(Long id, Fruit updatedFruit) {

        Fruit existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fruit not found"));

        existing.setName(updatedFruit.getName());
        existing.setPrice(updatedFruit.getPrice());
        existing.setAvailableQuantity(updatedFruit.getAvailableQuantity());
        existing.setImageUrl(updatedFruit.getImageUrl());

        return repository.save(existing);
    }

    public void deleteFruit(Long id) {

        Fruit existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fruit not found"));

        repository.delete(existing);
    }
}
