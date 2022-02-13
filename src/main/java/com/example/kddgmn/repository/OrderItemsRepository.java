package com.example.kddgmn.repository;

import com.example.kddgmn.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems,Integer> {
}
