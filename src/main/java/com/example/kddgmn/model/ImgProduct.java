package com.example.kddgmn.model;

import javax.persistence.*;

@Entity
@Table(name = "imgproduct")
public class ImgProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idimgproduct")
    private Integer idImgProduct;

    @Column(name = "imgurl")
    private String imgURL;

    @ManyToOne
    @JoinColumn(name = "idproduct")
    private Product product;

    public ImgProduct() {
    }

    public ImgProduct(Integer idImgProduct, String imgURL, Product product) {
        this.idImgProduct = idImgProduct;
        this.imgURL = imgURL;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ImgProduct{" +
                "idImgProduct=" + idImgProduct +
                ", imgURL='" + imgURL + '\'' +
                ", product=" + product +
                '}';
    }
}
