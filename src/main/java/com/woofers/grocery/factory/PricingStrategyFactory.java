package com.woofers.grocery.factory;

import com.woofers.grocery.strategy.PricingStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PricingStrategyFactory {

    private final Map<String, PricingStrategy> strategies;

    public PricingStrategyFactory(Map<String, PricingStrategy> strategies) {
        this.strategies = strategies;
    }

    public PricingStrategy get(String type) {
        return strategies.getOrDefault(type, strategies.get("REGULAR"));
    }
}
