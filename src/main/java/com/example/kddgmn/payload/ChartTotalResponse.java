package com.example.kddgmn.payload;

import java.util.Date;

public class ChartTotalResponse {
    private Date date;
    private Double total;

    public ChartTotalResponse(Date date, Double total) {
        this.date = date;
        this.total = total;
    }

    public ChartTotalResponse() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
