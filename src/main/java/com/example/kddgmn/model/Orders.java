package com.example.kddgmn.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorders")
    private Integer idOrders;
    private String phone;
    private String address;
    private String note;
    private Float total;
    @Column(name = "datecreate")
    private Date dateCreate;
    @Column(name = "datemodified")
    private Date dateModified;
    @Column(name = "dateend")
    private Date dateEnd;

    @ManyToOne
    @JoinColumn(name = "idstatus")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "idcustomer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "idemployee")
    private Employee employee;

    public Orders() {
    }

    public Orders(Integer idOrders, String phone, String address, String note, Float total, Date dateCreate, Date dateModified, Date dateEnd, Status status, Customer customer, Employee employee) {
        this.idOrders = idOrders;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.total = total;
        this.dateCreate = dateCreate;
        this.dateModified = dateModified;
        this.dateEnd = dateEnd;
        this.status = status;
        this.customer = customer;
        this.employee = employee;
    }

    public Integer getIdOrder() {
        return idOrders;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrders = idOrder;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
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

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrders +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", note='" + note + '\'' +
                ", total=" + total +
                ", dateCreate=" + dateCreate +
                ", dateModified=" + dateModified +
                ", dateEnd=" + dateEnd +
                ", status=" + status +
                ", customer=" + customer +
                ", employee=" + employee +
                '}';
    }
}
