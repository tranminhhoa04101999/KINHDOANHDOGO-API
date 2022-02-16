package com.example.kddgmn.repository;

import com.example.kddgmn.model.ImgProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImgProductRepository extends JpaRepository<ImgProduct,Integer> {
    @Query("SELECT v FROM ImgProduct v WHERE v.product.idProduct= :idProd")
    List<ImgProduct> getImgWithIdProd (@Param("idProd") Integer idProd);
}
