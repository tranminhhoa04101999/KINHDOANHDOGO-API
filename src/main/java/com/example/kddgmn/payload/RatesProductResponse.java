package com.example.kddgmn.payload;

public class RatesProductResponse {
    private String nameCustomer;
    private String timeUsed;
    private float pointRate;
    private String descRate;

    public RatesProductResponse() {
    }

    public RatesProductResponse(String nameCustomer, String timeUsed, float pointRate, String descRate) {
        this.nameCustomer = nameCustomer;
        this.timeUsed = timeUsed;
        this.pointRate = pointRate;
        this.descRate = descRate;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(String timeUsed) {
        this.timeUsed = timeUsed;
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
