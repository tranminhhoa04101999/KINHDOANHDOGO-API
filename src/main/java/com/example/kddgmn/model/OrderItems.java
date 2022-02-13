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

    @ManyToOne
    @JoinColumn(name = "idorders")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "idproduct")
    private Product product;

    public OrderItems() {
    }

    public OrderItems(Integer idOrderItems, Integer quantity, Orders orders, Product product) {
        this.idOrderItems = idOrderItems;
        this.quantity = quantity;
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

    public Orders getOrder() {
        return orders;
    }

    public void setOrder(Orders orders) {
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
                ", order=" + orders +
                ", product=" + product +
                '}';
    }
}
