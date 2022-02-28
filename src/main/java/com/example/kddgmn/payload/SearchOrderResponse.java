package com.example.kddgmn.payload;

import com.example.kddgmn.model.Orders;


import java.util.List;

public class SearchOrderResponse {
    private Orders orders;
    private List<ProductSearchResponse> productSearchResponses;

    public SearchOrderResponse() {
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public List<ProductSearchResponse> getProductSearchResponses() {
        return productSearchResponses;
    }

    public void setProductSearchResponses(List<ProductSearchResponse> productSearchResponses) {
        this.productSearchResponses = productSearchResponses;
    }
}
