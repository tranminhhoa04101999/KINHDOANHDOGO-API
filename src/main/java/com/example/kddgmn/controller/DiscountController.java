package com.example.kddgmn.controller;

import com.example.kddgmn.model.Discount;
import com.example.kddgmn.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping("/alldiscount")
    public List<Discount> getAll(){
        return discountService.getAll();
    }
}
