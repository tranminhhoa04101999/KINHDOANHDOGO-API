package com.example.kddgmn.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddiscount")
    private Integer idDiscount;
    private String name;
    private String desc;
    private Double percent;
    @Column(name = "datecreate")
    private Date dateCreate;
    @Column(name = "datemodified")
    private Date dateModified;
    @Column(name = "isactive")
    private Integer isActive;

    public Discount() {
    }

    public Discount(Integer idDiscount, String name, String desc, Double percent, Date dateCreate, Date dateModified, Integer isActive) {
        this.idDiscount = idDiscount;
        this.name = name;
        this.desc = desc;
        this.percent = percent;
        this.dateCreate = dateCreate;
        this.dateModified = dateModified;
        this.isActive = isActive;
    }

    public Integer getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(Integer idDiscount) {
        this.idDiscount = idDiscount;
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

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "idDiscount=" + idDiscount +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", percent=" + percent +
                ", dateCreate=" + dateCreate +
                ", dateModified=" + dateModified +
                ", isActive=" + isActive +
                '}';
    }
}
