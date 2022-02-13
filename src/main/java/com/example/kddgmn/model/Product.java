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
    private String name;
    private Float price;
    private String color;
    private String desc;
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

    public Product(Integer idProduct, String name, Float price, String color, String desc, Integer quantity, Date addDate, Integer isActive, Discount discount) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.color = color;
        this.desc = desc;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
                ", name='" + name + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", desc='" + desc + '\'' +
                ", quantity=" + quantity +
                ", addDate=" + addDate +
                ", isActive=" + isActive +
                ", discount=" + discount +
                '}';
    }
}
