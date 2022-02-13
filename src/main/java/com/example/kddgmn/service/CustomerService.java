package com.example.kddgmn.service;

import com.example.kddgmn.model.Customer;
import com.example.kddgmn.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getALl(){
        return customerRepository.findAll();
    }
}
