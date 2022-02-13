package com.example.kddgmn.controller;

import com.example.kddgmn.model.Customer;
import com.example.kddgmn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/allcustomer")
    public List<Customer> getAll(){
        return customerService.getALl();
    }
}
