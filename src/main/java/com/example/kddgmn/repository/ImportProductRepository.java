package com.example.kddgmn.repository;

import com.example.kddgmn.model.ImportProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImportProductRepository extends JpaRepository<ImportProduct,Integer> {
    @Query("SELECT MAX(v.idImportProduct) FROM ImportProduct v")
    Integer findIdMax();
}
