package com.woofers.grocery.service;

import com.woofers.grocery.entity.Cart;
import com.woofers.grocery.entity.CartItem;
import com.woofers.grocery.repository.CartRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository repository;

    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    public Cart addItem(String username, Long fruitId, int quantity) {

        Cart cart = repository.findByUsername(username)
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUsername(username);
                    return c;
                });

        CartItem item = new CartItem();
        item.setFruitId(fruitId);
        item.setQuantity(quantity);

        cart.getItems().add(item);
        return repository.save(cart);
    }

    public Cart getCart(String username) {
        return repository.findByUsername(username).orElseThrow();
    }

    @Transactional
    public void clearCart(String username) {
        Cart cart = getCart(username);
        repository.delete(cart);
    }

    @Transactional
    public Cart removeItem(String username, Long fruitId) {
    	Cart cart = repository.findByUsername(username)
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUsername(username);
                    return c;
                });
        
        cart.getItems().removeIf(item -> item.getFruitId().equals(fruitId));
        return repository.save(cart);
    }
    
    @Transactional
    public Cart updateQuantity(String username, Long fruitId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        Cart cart = repository.findByUsername(username)
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUsername(username);
                    return c;
                });

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getFruitId().equals(fruitId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found in cart"));

        item.setQuantity(quantity);

        return repository.save(cart);
    }


}
