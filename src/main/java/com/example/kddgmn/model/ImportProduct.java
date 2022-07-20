package com.example.kddgmn.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "importproduct")
public class ImportProduct {
    @Id
    @Column(name = "idimportproduct")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImportProduct;

    @Column(name = "sourcename")
    private String sourceName;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "datecreate")
    private Date dateCreate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "datemodified")
    private Date dateModified;

    @ManyToOne
    @JoinColumn(name = "idEmployee")
    private Employee employee;

    public ImportProduct() {
    }

    public ImportProduct(Integer idImportProduct, String sourceName, Date dateCreate, Date dateModified, Employee employee) {
        this.idImportProduct = idImportProduct;
        this.sourceName = sourceName;
        this.dateCreate = dateCreate;
        this.dateModified = dateModified;
        this.employee = employee;
    }

    public Integer getIdImportProduct() {
        return idImportProduct;
    }

    public void setIdImportProduct(Integer idImportProduct) {
        this.idImportProduct = idImportProduct;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}



















