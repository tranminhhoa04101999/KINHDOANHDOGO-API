package com.example.kddgmn.model;

import javax.persistence.*;

@Entity
@Table(name = "ratesproduct")
public class RatesProduct {
    @EmbeddedId
    private RateProductId idRateProduct;

    @ManyToOne
    @JoinColumn(name = "idproduct", referencedColumnName = "idproduct",updatable = false,insertable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "idorders", referencedColumnName = "idorders",updatable = false,insertable = false)
    private Orders orders;

    @Column(name = "descrate")
    private String descRate;

    @Column(name = "pointrate")
    private float pointRate;

    public RatesProduct() {
    }

    public RateProductId getIdRateProduct() {
        return idRateProduct;
    }

    public void setIdRateProduct(RateProductId idRateProduct) {
        this.idRateProduct = idRateProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public String getDescRate() {
        return descRate;
    }

    public void setDescRate(String descRate) {
        this.descRate = descRate;
    }

    public float getPointRate() {
        return pointRate;
    }

    public void setPointRate(float pointRate) {
        this.pointRate = pointRate;
    }
}
