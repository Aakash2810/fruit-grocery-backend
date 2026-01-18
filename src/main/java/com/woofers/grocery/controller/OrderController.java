package com.woofers.grocery.controller;

import com.woofers.grocery.entity.Order;
import com.woofers.grocery.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/checkout")
    public Order checkout(@RequestParam(defaultValue = "REGULAR") String pricingType,
                          Authentication authentication) {
        return service.checkout(authentication.getName(), pricingType);
    }
}
