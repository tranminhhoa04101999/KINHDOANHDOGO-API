package com.example.kddgmn.payload;

import java.util.Date;

public class ProductSearchResponse {
    private Integer idProduct;
    private String nameProduct;
    private Double price;
    private String color;
    private Integer quantity;
    private String imgURL;

    public ProductSearchResponse() {
    }

    public ProductSearchResponse(Integer idProduct, String nameProduct, Double price, String color, Integer quantity, String imgURL) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.color = color;
        this.quantity = quantity;
        this.imgURL = imgURL;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
