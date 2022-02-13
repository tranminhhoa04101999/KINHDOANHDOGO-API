package com.example.kddgmn.controller;

import com.example.kddgmn.model.ImgProduct;
import com.example.kddgmn.service.ImgProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImgProductController {
    @Autowired
    private ImgProductService imgProductService;

    @GetMapping("/allimgproduct")
    public List<ImgProduct> getAll(){
        return imgProductService.getAll();
    }
}
