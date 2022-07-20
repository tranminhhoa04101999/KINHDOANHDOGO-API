package com.example.kddgmn.model;


import javax.persistence.*;

@Entity
@Table(name = "importdetails")
public class ImportDetails {
    @EmbeddedId
    private ImportProductId importProductId;

    private Double price;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "idimportproduct" , referencedColumnName = "idimportproduct" ,insertable = false,updatable = false)
    private ImportProduct importProduct;

    @ManyToOne
    @JoinColumn(name = "idproduct" , referencedColumnName = "idproduct" ,insertable = false,updatable = false)
    private Product product;

    public ImportDetails() {
    }


    public ImportProductId getImportProductId() {
        return importProductId;
    }

    public void setImportProductId(ImportProductId importProductId) {
        this.importProductId = importProductId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ImportProduct getImportProduct() {
        return importProduct;
    }

    public void setImportProduct(ImportProduct importProduct) {
        this.importProduct = importProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
