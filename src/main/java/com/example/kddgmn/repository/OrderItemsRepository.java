package com.example.kddgmn.repository;

import com.example.kddgmn.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItems,Integer> {
    @Query("SELECT v FROM OrderItems v WHERE v.orders.idOrders= :idOrders")
    List<OrderItems> findByIdOrders (@Param("idOrders") int idOrders);

    @Query("SELECT count(v.idOrderItems) FROM OrderItems v WHERE v.product.idProduct= :idProduct")
    int checkProductHaveOrderItems(@Param("idProduct") int idProduct);
}
