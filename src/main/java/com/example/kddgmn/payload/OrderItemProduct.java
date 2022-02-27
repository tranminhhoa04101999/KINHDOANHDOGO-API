package com.example.kddgmn.payload;

public class OrderItemProduct {
    private Integer idProduct;
    private Integer quantity;

    public OrderItemProduct() {
    }

    public OrderItemProduct(Integer idProduct, Integer quantity) {
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
