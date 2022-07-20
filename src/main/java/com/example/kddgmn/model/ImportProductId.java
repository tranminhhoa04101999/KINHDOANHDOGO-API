package com.example.kddgmn.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ImportProductId implements Serializable {
    @Column(name = "idimportproduct")
    private Integer idImportProduct;

    @Column(name = "idproduct")
    private Integer idProduct;

    public ImportProductId() {
    }

    public ImportProductId(Integer idImportProduct, Integer idProduct) {
        this.idImportProduct = idImportProduct;
        this.idProduct = idProduct;
    }

    public Integer getIdImportProduct() {
        return idImportProduct;
    }

    public void setIdImportProduct(Integer idImportProduct) {
        this.idImportProduct = idImportProduct;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImportProductId that = (ImportProductId) o;
        return Objects.equals(idImportProduct, that.idImportProduct) && Objects.equals(idProduct, that.idProduct);
    }
}
