package com.example.kddgmn.controller;

import com.example.kddgmn.model.RatesProduct;
import com.example.kddgmn.service.RatesProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class RatesProductController {
    @Autowired
    private RatesProductService rateProductService;

    @GetMapping("/rateProductGetAll")
    public List<RatesProduct> getAll(){
        return rateProductService.getAll();
    }
}
