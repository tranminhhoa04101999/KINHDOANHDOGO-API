package com.example.kddgmn.payload;

import java.util.Date;

public class ChartOrdersResponse {
    private Date date;
    private int quantity;

    public ChartOrdersResponse() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
