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
    @Column(name = "desccategory")
    private String descCategory;
    @Column(name = "imgurl")
    private String imgURL;
    @Column(name = "isactive")
    private Integer isActive;



    public Category() {
    }

    public Category(Integer idCategory, String name, String descCategory, String imgURL, Integer isActive) {
        this.idCategory = idCategory;
        this.name = name;
        this.descCategory = descCategory;
        this.imgURL = imgURL;
        this.isActive = isActive;
    }

    public Category(String name, String descCategory, String imgURL, Integer isActive) {
        this.name = name;
        this.descCategory = descCategory;
        this.imgURL = imgURL;
        this.isActive = isActive;
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

    public String getDescCategory() {
        return descCategory;
    }

    public void setDescCategory(String descCategory) {
        this.descCategory = descCategory;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", name='" + name + '\'' +
                ", descCategory='" + descCategory + '\'' +
                ", imgURL='" + imgURL + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
