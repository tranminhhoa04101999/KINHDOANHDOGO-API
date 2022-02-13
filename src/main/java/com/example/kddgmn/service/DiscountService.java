package com.example.kddgmn.service;

import com.example.kddgmn.model.Discount;
import com.example.kddgmn.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;

    public List<Discount> getAll(){
        return discountRepository.findAll();
    }
}
