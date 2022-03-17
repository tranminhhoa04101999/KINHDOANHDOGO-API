package com.example.kddgmn.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderItemsId implements Serializable {
    @Column(name = "idorders")
    private Integer idOrders;

    @Column(name = "idproduct")
    private Integer idProduct;

    public OrderItemsId() {
    }

    public OrderItemsId(Integer idOrders, Integer idProduct) {
        this.idOrders = idOrders;
        this.idProduct = idProduct;
    }

    public Integer getIdOrders() {
        return idOrders;
    }

    public void setIdOrders(Integer idOrders) {
        this.idOrders = idOrders;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemsId that = (OrderItemsId) o;
        return Objects.equals(idOrders, that.idOrders) && Objects.equals(idProduct, that.idProduct);
    }
}
