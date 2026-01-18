package com.woofers.grocery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    private Long fruitId;
    private int quantity;
    private double price;

    public Long getFruitId() {
        return fruitId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setFruitId(Long fruitId) {
        this.fruitId = fruitId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
