package com.example.kddgmn.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idemployee")
    private Integer idEmployee;

    private String name;
    private String phone;
    private String address;

    @Column(name = "datebegin")
    private Date dateBegin;
    @Column(name = "dateend")
    private Date dateEnd;
    @Column(name = "isworking")
    private Integer isWorking;

    @ManyToOne
    @JoinColumn(name = "idaccount")
    private Account account;

    public Employee() {
    }

    public Employee(Integer idEmployee, String name, String phone, String address, Date dateBegin, Date dateEnd, Integer isWorking, Account account) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.phone = phone;
        this.address = address;

        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.isWorking = isWorking;
        this.account = account;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
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


    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getIsWorking() {
        return isWorking;
    }

    public void setIsWorking(Integer isWorking) {
        this.isWorking = isWorking;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee=" + idEmployee +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                ", isWorking=" + isWorking +
                ", account=" + account +
                '}';
    }
}
