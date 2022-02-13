package com.example.kddgmn.controller;

import com.example.kddgmn.model.OrderItems;
import com.example.kddgmn.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderItemsController {
    @Autowired
    private OrderItemsService orderItemsService;

    @GetMapping("/allorderitems")
    public List<OrderItems> getAll(){
        return orderItemsService.getAll();
    }
}
