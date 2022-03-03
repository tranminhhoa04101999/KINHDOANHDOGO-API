package com.example.kddgmn.controller;

import com.example.kddgmn.model.Customer;
import com.example.kddgmn.payload.ChartOrdersResponse;
import com.example.kddgmn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/allcustomer")
    public List<Customer> getAll(){
        return customerService.getALl();
    }
    @PostMapping("/saveCustomer")
    public Integer save(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @GetMapping("/findCustomerByIdAccount")
    public Customer findByIdAccount(@RequestParam("idAccount") Integer idAccount){
        return customerService.findByIdAccount(idAccount);
    }

    @GetMapping("/findByDatecreateInYear")
    public List<ChartOrdersResponse> findByDatecreateInYear(){
        return customerService.findByDatecreateInYear();
    }
}
