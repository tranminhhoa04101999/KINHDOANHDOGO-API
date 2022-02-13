package com.example.kddgmn.repository;

import com.example.kddgmn.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
