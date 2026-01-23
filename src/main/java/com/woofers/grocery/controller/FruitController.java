package com.woofers.grocery.controller;

import com.woofers.grocery.entity.Fruit;
import com.woofers.grocery.service.FruitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FruitController {

    private final FruitService service;

    public FruitController(FruitService service) {
        this.service = service;
    }

   
    @GetMapping("/fruits")
    public List<Fruit> list() {
        return service.getAll();
    }

   

    @PostMapping("/admin/fruits")
    public Fruit add(@RequestBody Fruit fruit) {
        return service.addFruit(fruit);
    }

    @PutMapping("/admin/fruits/{id}")
    public Fruit update(@PathVariable Long id, @RequestBody Fruit fruit) {
        return service.updateFruit(id, fruit);
    }

    @DeleteMapping("/admin/fruits/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteFruit(id);
    }

    @GetMapping("/admin/fruits")
    public List<Fruit> listAllForAdmin() {
        return service.getAll();
    }
}
