package com.example.kddgmn.payload;

public class OrderCheckRateResponse {
    private int idOrder;
    private int check;

    public OrderCheckRateResponse(int idOrder, int check) {
        this.idOrder = idOrder;
        this.check = check;
    }

    public OrderCheckRateResponse() {
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
}
