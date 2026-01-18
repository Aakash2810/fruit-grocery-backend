package com.woofers.grocery.controller;

import com.woofers.grocery.entity.Fruit;
import com.woofers.grocery.service.FruitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruits")
public class FruitController {

    private final FruitService service;

    public FruitController(FruitService service) {
        this.service = service;
    }

    @PostMapping
    public Fruit add(@RequestBody Fruit fruit) {
        return service.addFruit(fruit);
    }

    @GetMapping
    public List<Fruit> list() {
        return service.getAll();
    }
}
