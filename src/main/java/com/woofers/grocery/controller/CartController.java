package com.woofers.grocery.controller;

import com.woofers.grocery.entity.Cart;
import com.woofers.grocery.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/items")
    public Cart addItem(@RequestParam Long fruitId,
                        @RequestParam int quantity,
                        Authentication authentication) {
        return service.addItem(authentication.getName(), fruitId, quantity);
    }

    @GetMapping
    public Cart view(Authentication authentication) {
        return service.getCart(authentication.getName());
    }
}
