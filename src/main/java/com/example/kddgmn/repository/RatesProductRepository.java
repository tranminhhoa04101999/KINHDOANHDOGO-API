package com.example.kddgmn.repository;

import com.example.kddgmn.model.RatesProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatesProductRepository extends JpaRepository<RatesProduct,Integer> {
    @Query("SELECT v FROM RatesProduct v WHERE v.product.idProduct= :idProduct AND v.orders.idOrders= :idOrders")
    RatesProduct findByIdProductAndIdOrders(@Param("idProduct") int idProduct,@Param("idOrders") int idOrders);

    @Query("SELECT v FROM RatesProduct v WHERE v.orders.idOrders= :idOrders")
    List<RatesProduct> findByIdOrders(@Param("idOrders") int idOrders);
}
