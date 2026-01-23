package com.woofers.grocery.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cart_id", "fruit_id"})
 })
public class CartItem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonBackReference
    private Cart cart;

    @Column(name = "fruit_id", nullable = false)
    private Long fruitId;

    private int quantity;

    public Long getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public Long getFruitId() {
        return fruitId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setFruitId(Long fruitId) {
        this.fruitId = fruitId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
