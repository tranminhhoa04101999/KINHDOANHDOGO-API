package com.example.kddgmn.controller;

import com.example.kddgmn.model.Product;
import com.example.kddgmn.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("/allproduct")
    public List<Product> getAll(){
        return productsService.getAll();
    }
    @PostMapping("/addproduct")
    public Integer addProduct(@RequestBody Product product){
        return productsService.save(product); // return 1 la thanh cong
    }
}
