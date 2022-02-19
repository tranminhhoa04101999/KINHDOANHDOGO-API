package com.example.kddgmn.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct")
    private Integer idProduct;
    @Column(name = "nameproduct")
    private String nameProduct;
    private Double price;
    private String color;
    @Column(name = "descproduct")
    private String descProduct;
    private Integer quantity;
    @Column(name = "adddate")
    private Date addDate;
    @Column(name = "isactive")
    private Integer isActive;

    @ManyToOne
    @JoinColumn(name = "iddiscount")
    private Discount discount;

    public Product() {
    }

    public Product(Integer idProduct, String nameProduct, Double price, String color, String descProduct, Integer quantity, Date addDate, Integer isActive, Discount discount) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.color = color;
        this.descProduct = descProduct;
        this.quantity = quantity;
        this.addDate = addDate;
        this.isActive = isActive;
        this.discount = discount;
    }

    public Product(String nameProduct, Double price, String color, String descProduct, Integer quantity, Date addDate, Integer isActive, Discount discount) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.color = color;
        this.descProduct = descProduct;
        this.quantity = quantity;
        this.addDate = addDate;
        this.isActive = isActive;
        this.discount = discount;
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

    public String getDescProduct() {
        return descProduct;
    }

    public void setDescProduct(String descProduct) {
        this.descProduct = descProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", nameProduct='" + nameProduct + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", desc='" + descProduct + '\'' +
                ", quantity=" + quantity +
                ", addDate=" + addDate +
                ", isActive=" + isActive +
                ", discount=" + discount +
                '}';
    }
}
