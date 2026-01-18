package com.woofers.grocery.strategy;

import org.springframework.stereotype.Component;

@Component("REGULAR")
public class RegularPricingStrategy implements PricingStrategy {

    @Override
    public double calculate(double basePrice) {
        return basePrice;
    }
}
