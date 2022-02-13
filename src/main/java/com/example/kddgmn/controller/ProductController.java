package com.example.kddgmn.controller;

import com.example.kddgmn.model.Product;
import com.example.kddgmn.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("/allproduct")
    public List<Product> getAll(){
        return productsService.getAll();

    }
}
