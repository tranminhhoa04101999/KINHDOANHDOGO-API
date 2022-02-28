package com.example.kddgmn.controller;

import com.example.kddgmn.model.OrderItems;
import com.example.kddgmn.payload.OrderItemProduct;
import com.example.kddgmn.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrderItemsController {
    @Autowired
    private OrderItemsService orderItemsService;

    @GetMapping("/allorderitems")
    public List<OrderItems> getAll(){
        return orderItemsService.getAll();
    }
    @PostMapping("/saveOrderItems")
    public Integer saveOrderItems(@RequestBody List<OrderItemProduct> orderItemProduct){
        return orderItemsService.saveOrderItem(orderItemProduct);
    }
    @GetMapping("/orderItemsfindByIdOrders")
    public List<OrderItems> findByIdOrders(@RequestParam("idOrders") int idOrders){
        return orderItemsService.findByIdOrders(idOrders);
    }
}
