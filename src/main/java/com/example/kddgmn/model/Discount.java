package com.example.kddgmn.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddiscount")
    private Integer idDiscount;
    @Column(name = "namediscount")
    private String nameDiscount;
    @Column(name = "descdiscount")
    private String descDiscount;
    private Double percent;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "datecreate")
    private Date dateCreate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "datemodified")
    private Date dateModified;

    @Column(name = "isactive")
    private Integer isActive;

    public Discount() {
    }

    public Discount(Integer idDiscount, String nameDiscount, String descDiscount, Double percent, Date dateCreate, Date dateModified, Integer isActive) {
        this.idDiscount = idDiscount;
        this.nameDiscount = nameDiscount;
        this.descDiscount = descDiscount;
        this.percent = percent;
        this.dateCreate = dateCreate;
        this.dateModified = dateModified;
        this.isActive = isActive;
    }

    public Discount(String nameDiscount, String descDiscount, Double percent, Date dateCreate, Date dateModified, Integer isActive) {
        this.nameDiscount = nameDiscount;
        this.descDiscount = descDiscount;
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

    public String getNameDiscount() {
        return nameDiscount;
    }

    public void setNameDiscount(String nameDiscount) {
        this.nameDiscount = nameDiscount;
    }

    public String getDescDiscount() {
        return descDiscount;
    }

    public void setDescDiscount(String descDiscount) {
        this.descDiscount = descDiscount;
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
                ", nameDiscount='" + nameDiscount + '\'' +
                ", descDiscount='" + descDiscount + '\'' +
                ", percent=" + percent +
                ", dateCreate=" + dateCreate +
                ", dateModified=" + dateModified +
                ", isActive=" + isActive +
                '}';
    }
}
