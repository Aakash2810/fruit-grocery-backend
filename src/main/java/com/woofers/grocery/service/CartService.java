package com.woofers.grocery.service;

import com.woofers.grocery.entity.Cart;
import com.woofers.grocery.entity.CartItem;
import com.woofers.grocery.repository.CartRepository;
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

    public void clearCart(Cart cart) {
        repository.delete(cart);
    }
}
