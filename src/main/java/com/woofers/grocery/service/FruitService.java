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
}
