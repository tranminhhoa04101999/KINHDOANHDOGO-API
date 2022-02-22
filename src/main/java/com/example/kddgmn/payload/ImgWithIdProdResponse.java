package com.example.kddgmn.payload;

public class ImgWithIdProdResponse {
    private Integer idImgProduct;
    private String imgURL;
    private Integer idProduct;

    public ImgWithIdProdResponse() {
    }

    public ImgWithIdProdResponse(Integer idImgProduct, String imgURL, Integer idProduct) {
        this.idImgProduct = idImgProduct;
        this.imgURL = imgURL;
        this.idProduct = idProduct;
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

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }
}
