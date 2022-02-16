package com.example.kddgmn.payload;

public class ImgProductResponse {
    private Integer idImgProduct;
    private String imgURL;

    public ImgProductResponse() {
    }

    public ImgProductResponse(Integer idImgProduct, String imgURL) {
        this.idImgProduct = idImgProduct;
        this.imgURL = imgURL;
    }

    public Integer getIdImgProduct() {
        return idImgProduct;
    }

    public void setIdImgProduct(Integer idImgProduct) {
        this.idImgProduct = idImgProduct;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
