package com.example.kddgmn.model;

import javax.persistence.*;

@Entity
@Table(name = "ratesproduct")
public class RatesProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idratesproduct")
    private Integer idRateProduct;

    @ManyToOne
    @JoinColumn(name = "idproduct")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "idorders")
    private Orders orders;

    @Column(name = "descrate")
    private String descRate;

    @Column(name = "pointrate")
    private float pointRate;

    public RatesProduct() {
    }

    public RatesProduct(Integer idRateProduct) {
        this.idRateProduct = idRateProduct;
    }

    public RatesProduct(Integer idRateProduct, Product product, Orders orders, String descRate, float pointRate) {
        this.idRateProduct = idRateProduct;
        this.product = product;
        this.orders = orders;
        this.descRate = descRate;
        this.pointRate = pointRate;
    }

    public RatesProduct(Product product, Orders orders, String descRate, float pointRate) {
        this.product = product;
        this.orders = orders;
        this.descRate = descRate;
        this.pointRate = pointRate;
    }

    public Integer getIdRateProduct() {
        return idRateProduct;
    }

    public void setIdRateProduct(Integer idRateProduct) {
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

    @Override
    public String toString() {
        return "RateProduct{" +
                "idRateProduct=" + idRateProduct +
                ", product=" + product +
                ", orders=" + orders +
                ", descRate='" + descRate + '\'' +
                ", pointRate=" + pointRate +
                '}';
    }
}
