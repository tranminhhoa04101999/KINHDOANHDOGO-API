package com.example.kddgmn.service;

import com.example.kddgmn.model.OrderItems;
import com.example.kddgmn.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsService {
    @Autowired
    private OrderItemsRepository orderItemsRepository;

    public List<OrderItems> getAll(){
        return orderItemsRepository.findAll();
    }
}
