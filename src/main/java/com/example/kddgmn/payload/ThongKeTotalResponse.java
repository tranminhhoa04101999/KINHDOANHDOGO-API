package com.example.kddgmn.payload;

import java.util.Date;
import java.util.List;

public class ThongKeTotalResponse {
    private List<ChartTotalResponse> chartTotalResponseList;
    private Double totalAll;

    public ThongKeTotalResponse() {
    }

    public ThongKeTotalResponse(List<ChartTotalResponse> chartTotalResponseList, Double totalAll) {
        this.chartTotalResponseList = chartTotalResponseList;
        this.totalAll = totalAll;
    }

    public List<ChartTotalResponse> getChartTotalResponseList() {
        return chartTotalResponseList;
    }

    public void setChartTotalResponseList(List<ChartTotalResponse> chartTotalResponseList) {
        this.chartTotalResponseList = chartTotalResponseList;
    }

    public Double getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(Double totalAll) {
        this.totalAll = totalAll;
    }
}
