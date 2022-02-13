package com.example.kddgmn.service;

import com.example.kddgmn.model.Orders;
import com.example.kddgmn.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    public List<Orders> getAll(){
        return ordersRepository.findAll();
    }
}
