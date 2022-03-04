package com.example.kddgmn.payload;

public class RatesProductRecive {
    private int idOrder;
    private int idProduct;
    private float pointRate;
    private String descRate;

    public RatesProductRecive() {
    }

    public RatesProductRecive(int idOrder, int idProduct, float pointRate, String descRate) {
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.pointRate = pointRate;
        this.descRate = descRate;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public float getPointRate() {
        return pointRate;
    }

    public void setPointRate(float pointRate) {
        this.pointRate = pointRate;
    }

    public String getDescRate() {
        return descRate;
    }

    public void setDescRate(String descRate) {
        this.descRate = descRate;
    }
}
