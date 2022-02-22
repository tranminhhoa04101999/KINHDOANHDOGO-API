package com.example.kddgmn.repository;

import com.example.kddgmn.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT max(v.idProduct) FROM Product v")
    Integer findIdMax();
    @Query("SELECT v FROM Product v")
    Page<Product> findAllWithPage (Pageable pageable);

}
