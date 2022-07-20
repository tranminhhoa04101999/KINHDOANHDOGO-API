package com.example.kddgmn.model;


import javax.persistence.*;


@Entity
@Table(name = "orderitems")
public class OrderItems {
    @EmbeddedId
    private OrderItemsId idOrderItems;

    private Integer quantity;

    @Column(name= "pricecurrent")
    private Double priceCurrent;

    @Column(name = "discountcurrent")
    private Double discountcurrent;

    public OrderItems() {
    }

    @ManyToOne
    @JoinColumn(name = "idorders", referencedColumnName = "idorders" ,insertable = false,updatable = false )
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "idproduct", referencedColumnName = "idproduct" ,insertable = false,updatable = false )
    private Product product;

    public OrderItemsId getIdOrderItems() {
        return idOrderItems;
    }

    public void setIdOrderItems(OrderItemsId idOrderItems) {
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

    public Double getDiscountcurrent() {
        return discountcurrent;
    }

    public void setDiscountcurrent(Double discountcurrent) {
        this.discountcurrent = discountcurrent;
    }
}
