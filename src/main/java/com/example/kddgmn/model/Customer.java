package com.example.kddgmn.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcustomer")
    private Integer idCustomer;
    private String name;
    private String phone;
    private String address;
    @Column(name = "imgurl")
    private String imgURL;
    @Column(name = "datecreate")
    private Date dateCreate;
    @ManyToOne
    @JoinColumn(name = "idaccount")
    private Account account;

    public Customer() {
    }

    public Customer(Integer idCustomer, String name, String phone, String address, String imgURL, Date dateCreate, Account account) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.imgURL = imgURL;
        this.dateCreate = dateCreate;
        this.account = account;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer=" + idCustomer +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", imgURL='" + imgURL + '\'' +
                ", dateCreate=" + dateCreate +
                ", account=" + account +
                '}';
    }
}
