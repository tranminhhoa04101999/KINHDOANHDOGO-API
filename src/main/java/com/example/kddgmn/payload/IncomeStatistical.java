package com.example.kddgmn.payload;

public class IncomeStatistical {
    private int id;
    private String name;
    private Double averageImportPrice;
    private Double totalPriceSale;
    private int quantitySale;

    public IncomeStatistical() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAverageImportPrice() {
        return averageImportPrice;
    }

    public void setAverageImportPrice(Double averageImportPrice) {
        this.averageImportPrice = averageImportPrice;
    }

    public Double getTotalPriceSale() {
        return totalPriceSale;
    }

    public void setTotalPriceSale(Double totalPriceSale) {
        this.totalPriceSale = totalPriceSale;
    }

    public int getQuantitySale() {
        return quantitySale;
    }

    public void setQuantitySale(int quantitySale) {
        this.quantitySale = quantitySale;
    }

}
