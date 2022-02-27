package com.example.kddgmn.repository;

import com.example.kddgmn.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    @Query("SELECT MAX(v.idOrders) FROM Orders v")
    Integer findIdMax();
}
