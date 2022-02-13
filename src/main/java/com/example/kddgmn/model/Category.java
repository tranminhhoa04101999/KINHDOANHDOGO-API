package com.example.kddgmn.model;

import javax.persistence.*;


@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategory")
    private Integer idCategory;
    private String name;
    private String desc;
    @Column(name = "isactive")
    private Integer isActive;

    @ManyToOne
    @JoinColumn(name = "idproduct")
    private Product product;

    public Category() {
    }

    public Category(Integer idCategory, String name, String desc, Integer isActive, Product product) {
        this.idCategory = idCategory;
        this.name = name;
        this.desc = desc;
        this.isActive = isActive;
        this.product = product;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", isActive=" + isActive +
                ", product=" + product +
                '}';
    }
}
