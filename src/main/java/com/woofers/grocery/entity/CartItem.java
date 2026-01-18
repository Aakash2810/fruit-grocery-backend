package com.woofers.grocery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class CartItem {

    @Id
    @GeneratedValue
    private Long id;

    private Long fruitId;
    private int quantity;

    public Long getFruitId() {
        return fruitId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setFruitId(Long fruitId) {
        this.fruitId = fruitId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
