package com.example.kddgmn.payload;

public class TopSaleResponse {
    private Integer idProduct;
    private String nameProduct;
    private Double price;
    private Double priceDiscount;
    private Integer quantity;
    private String imgURL;

    public TopSaleResponse() {
    }

    public TopSaleResponse(Integer idProduct, String nameProduct, Double price, Double priceDiscount, Integer quantity, String imgURL) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.priceDiscount = priceDiscount;
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

    public Double getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(Double priceDiscount) {
        this.priceDiscount = priceDiscount;
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
