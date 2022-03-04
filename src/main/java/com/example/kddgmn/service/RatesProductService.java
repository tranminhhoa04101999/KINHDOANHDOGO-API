package com.example.kddgmn.service;

import com.example.kddgmn.model.RatesProduct;
import com.example.kddgmn.repository.RatesProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatesProductService {
    @Autowired
    private RatesProductRepository rateProductRepository;

    public List<RatesProduct> getAll(){
        return rateProductRepository.findAll();
    }

}
