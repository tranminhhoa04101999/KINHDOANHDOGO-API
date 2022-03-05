package com.example.kddgmn.payload;

import java.util.List;

public class RatesProductDetailResponse {
    private int totalRate;
    private int one;
    private int two;
    private int three;
    private int four;
    private int five;
    private float percentTotal;
    private List<RatesProductResponse> ratesProductList;

    public RatesProductDetailResponse() {
    }

    public RatesProductDetailResponse(int totalRate, int one, int two, int three, int four, int five, float percentTotal, List<RatesProductResponse> ratesProductList) {
        this.totalRate = totalRate;
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
        this.percentTotal = percentTotal;
        this.ratesProductList = ratesProductList;
    }

    public int getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(int totalRate) {
        this.totalRate = totalRate;
    }

    public int getOne() {
        return one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public int getThree() {
        return three;
    }

    public void setThree(int three) {
        this.three = three;
    }

    public int getFour() {
        return four;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public int getFive() {
        return five;
    }

    public void setFive(int five) {
        this.five = five;
    }

    public float getPercentTotal() {
        return percentTotal;
    }

    public void setPercentTotal(float percentTotal) {
        this.percentTotal = percentTotal;
    }

    public List<RatesProductResponse> getRatesProductList() {
        return ratesProductList;
    }

    public void setRatesProductList(List<RatesProductResponse> ratesProductList) {
        this.ratesProductList = ratesProductList;
    }
}
