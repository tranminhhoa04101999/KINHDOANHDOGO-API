package com.example.kddgmn.repository;

import com.example.kddgmn.model.ImgProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ImgProductRepository extends JpaRepository<ImgProduct,Integer> {
    @Query("SELECT v FROM ImgProduct v WHERE v.product.idProduct= :idProd")
    List<ImgProduct> getImgWithIdProd (@Param("idProd") Integer idProd);
    // xóa img theo idProduct
    @Modifying
    @Transactional
    @Query("DELETE FROM ImgProduct v WHERE v.product.idProduct=:idProd")
    void deleteByIdProduct(@Param("idProd") Integer idProd);
}
