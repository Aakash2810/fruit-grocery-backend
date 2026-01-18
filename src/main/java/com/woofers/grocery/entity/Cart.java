package com.woofers.grocery.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
