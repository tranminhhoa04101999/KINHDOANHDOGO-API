package com.example.kddgmn.repository;

import com.example.kddgmn.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    @Query("SELECT MAX(v.idOrders) FROM Orders v")
    Integer findIdMax();

    @Query("SELECT v FROM Orders v WHERE v.status.idStatus<= :idStatus AND (v.idOrders= :idOrders OR v.phone= :phone)")
    List<Orders> searchOrderByIdOrPhone (@Param("idStatus") int idStatus,@Param("idOrders") int idOrders,@Param("phone") String phone);
}
