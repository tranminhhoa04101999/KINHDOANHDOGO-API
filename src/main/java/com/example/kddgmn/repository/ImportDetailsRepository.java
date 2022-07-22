package com.example.kddgmn.repository;

import com.example.kddgmn.model.ImportDetails;
import com.example.kddgmn.model.ImportDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImportDetailsRepository extends JpaRepository<ImportDetails, ImportDetailsId> {
    @Query("SELECT v FROM ImportDetails v where v.importProduct.idImportProduct= :idImportProduct")
    List<ImportDetails> findByIdImportProduct(@Param("idImportProduct") int idImportProduct);

    @Query("select v from ImportDetails v where v.product.idProduct= :idProduct")
    List<ImportDetails> findByIdProduct(@Param("idProduct") int idProduct);
}
