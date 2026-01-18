package com.woofers.grocery.strategy;

import org.springframework.stereotype.Component;

@Component("BULK")
public class BulkPricingStrategy implements PricingStrategy {

    @Override
    public double calculate(double basePrice) {
        return basePrice * 0.9;
    }
}
