package com.example.kddgmn.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RateProductId implements Serializable {
    @Column(name = "idproduct")
    private Integer idProduct;

    @Column(name = "idorders")
    private Integer idOrders;

    public RateProductId() {
    }

    public RateProductId(Integer idProduct, Integer idOrders) {
        this.idProduct = idProduct;
        this.idOrders = idOrders;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdOrders() {
        return idOrders;
    }

    public void setIdOrders(Integer idOrders) {
        this.idOrders = idOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateProductId that = (RateProductId) o;
        return Objects.equals(idProduct, that.idProduct) && Objects.equals(idOrders, that.idOrders);
    }

}
