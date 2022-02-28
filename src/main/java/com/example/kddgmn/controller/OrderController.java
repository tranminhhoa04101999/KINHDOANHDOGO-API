package com.example.kddgmn.controller;

import com.example.kddgmn.model.Orders;
import com.example.kddgmn.payload.SearchOrderResponse;
import com.example.kddgmn.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/allorder")
    public List<Orders> getAll(){
        return  ordersService.getAll();
    }
    @PostMapping("/saveOrder")
    public Integer saveOrder(@RequestBody Orders orders,@RequestParam("name") String name){
        return ordersService.saveOrder(orders,name);
    }
    @GetMapping("/searchOrderByIdOrPhone")
    public List<SearchOrderResponse> searchOrderByIdOrPhone(@RequestParam("idStatus") int idStatus, @RequestParam("idOrders") int idOrders, @RequestParam("phone") String phone){
        return ordersService.searchOrderByIdOrPhone(idOrders,idStatus,phone);
    }
}
