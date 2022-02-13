package com.example.kddgmn.service;

import com.example.kddgmn.model.ImgProduct;
import com.example.kddgmn.repository.ImgProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgProductService {
    @Autowired
    private ImgProductRepository imgProductRepository;

    public List<ImgProduct> getAll(){
        return imgProductRepository.findAll();
    }
}
