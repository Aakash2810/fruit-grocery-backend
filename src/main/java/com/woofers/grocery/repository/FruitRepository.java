package com.woofers.grocery.repository;

import com.woofers.grocery.entity.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitRepository extends JpaRepository<Fruit, Long> {
}
