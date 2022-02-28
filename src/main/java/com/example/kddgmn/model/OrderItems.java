package com.example.kddgmn.model;


import javax.persistence.*;

@Entity
@Table(name = "orderitems")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorderitems")
    private Integer idOrderItems;
    private Integer quantity;

    @Column(name= "pricecurrent")
    private Double priceCurrent;

    @ManyToOne
    @JoinColumn(name = "idorders")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "idproduct")
    private Product product;

    public OrderItems() {
    }

    public OrderItems(Integer quantity, Double priceCurrent, Orders orders, Product product) {
        this.quantity = quantity;
        this.priceCurrent = priceCurrent;
        this.orders = orders;
        this.product = product;
    }

    public OrderItems(Integer idOrderItems, Integer quantity, Double priceCurrent, Orders orders, Product product) {
        this.idOrderItems = idOrderItems;
        this.quantity = quantity;
        this.priceCurrent = priceCurrent;
        this.orders = orders;
        this.product = product;
    }

    public Integer getIdOrderItems() {
        return idOrderItems;
    }

    public void setIdOrderItems(Integer idOrderItems) {
        this.idOrderItems = idOrderItems;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPriceCurrent() {
        return priceCurrent;
    }

    public void setPriceCurrent(Double priceCurrent) {
        this.priceCurrent = priceCurrent;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "idOrderItems=" + idOrderItems +
                ", quantity=" + quantity +
                ", priceCurrent=" + priceCurrent +
                ", orders=" + orders +
                ", product=" + product +
                '}';
    }
}
