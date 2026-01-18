package com.woofers.grocery.service;

import com.woofers.grocery.entity.Cart;
import com.woofers.grocery.entity.CartItem;
import com.woofers.grocery.entity.Fruit;
import com.woofers.grocery.entity.Order;
import com.woofers.grocery.entity.OrderItem;
import com.woofers.grocery.factory.PricingStrategyFactory;
import com.woofers.grocery.repository.CartRepository;
import com.woofers.grocery.repository.FruitRepository;
import com.woofers.grocery.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final CartRepository cartRepository;
    private final FruitRepository fruitRepository;
    private final OrderRepository orderRepository;
    private final PricingStrategyFactory pricingFactory;

    public OrderService(CartRepository cartRepository,
                        FruitRepository fruitRepository,
                        OrderRepository orderRepository,
                        PricingStrategyFactory pricingFactory) {
        this.cartRepository = cartRepository;
        this.fruitRepository = fruitRepository;
        this.orderRepository = orderRepository;
        this.pricingFactory = pricingFactory;
    }

    @Transactional
    public Order checkout(String username, String pricingType) {

        Cart cart = cartRepository.findByUsername(username).orElseThrow();

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        for (CartItem item : cart.getItems()) {
            Fruit fruit = fruitRepository.findById(item.getFruitId()).orElseThrow();

            if (fruit.getAvailableQuantity() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock");
            }

            fruit.setAvailableQuantity(
                    fruit.getAvailableQuantity() - item.getQuantity()
            );

            double price = pricingFactory.get(pricingType)
                    .calculate(fruit.getPrice() * item.getQuantity());

            OrderItem orderItem = new OrderItem();
            orderItem.setFruitId(fruit.getId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(price);

            orderItems.add(orderItem);
            total += price;
        }

        Order order = new Order();
        order.setItems(orderItems);
        order.setTotalAmount(total);
        order.setStatus("CONFIRMED");

        cartRepository.delete(cart);
        return orderRepository.save(order);
    }
}
