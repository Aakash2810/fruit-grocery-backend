package com.woofers.grocery.service;

import com.woofers.grocery.entity.Cart;
import com.woofers.grocery.entity.CartItem;
import com.woofers.grocery.entity.Fruit;
import com.woofers.grocery.repository.CartRepository;
import com.woofers.grocery.repository.FruitRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final FruitRepository fruitRepository;

    public CartService(CartRepository cartRepository,
                       FruitRepository fruitRepository) {
        this.cartRepository = cartRepository;
        this.fruitRepository = fruitRepository;
    }

    @Transactional
    public Cart addItem(String username, Long fruitId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        Fruit fruit = fruitRepository.findById(fruitId)
                .orElseThrow(() -> new RuntimeException("Fruit not found"));

        Cart cart = cartRepository.findByUsername(username)
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUsername(username);
                    return c;
                });

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getFruitId().equals(fruitId))
                .findFirst()
                .orElse(null);

        int newQty = quantity;

        if (item != null) {
            newQty = item.getQuantity() + quantity;
        }

        if (fruit.getAvailableQuantity() < newQty) {
            throw new RuntimeException("Insufficient stock");
        }

        if (item == null) {
            item = new CartItem();
            item.setFruitId(fruitId);
            item.setQuantity(quantity);
            item.setCart(cart);
            cart.getItems().add(item);
        } else {
            item.setQuantity(newQty);
        }

        return cartRepository.save(cart);
    }

    public Cart getCart(String username) {
        return cartRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Transactional
    public Cart removeItem(String username, Long fruitId) {

        Cart cart = getCart(username);

        boolean removed = cart.getItems()
                .removeIf(i -> i.getFruitId().equals(fruitId));

        if (!removed) {
            throw new RuntimeException("Item not found in cart");
        }

        return cartRepository.save(cart);
    }

    @Transactional
    public Cart updateQuantity(String username, Long fruitId, int quantity) {
    	
//        if (quantity <= 0) {
//            return removeItem(username, fruitId);
//        }

        Fruit fruit = fruitRepository.findById(fruitId)
                .orElseThrow(() -> new RuntimeException("Fruit not found"));
        
        Cart cart = getCart(username);

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getFruitId().equals(fruitId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not in cart"));

	      if (quantity > 0) {
	        if (fruit.getAvailableQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }
	        else {
	        	item.setQuantity(item.getQuantity()+quantity);
	        }

	      }
	      else {
		        if (item.getQuantity()+quantity<=0) {
	            return removeItem(username, fruitId);
	        }
		        else {
		        	item.setQuantity(item.getQuantity()+quantity);
		        }
	    	  
	      }
       

        return cartRepository.save(cart);
    }

    @Transactional
    public void clearCart(String username) {
        Cart cart = getCart(username);
        cartRepository.delete(cart);
    }
}
