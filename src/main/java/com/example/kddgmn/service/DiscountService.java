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
    public Integer save(Discount discount){
        try {
            discountRepository.save(discount);
        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
    public Integer deleteById(Integer id){
        try {
            discountRepository.deleteById(id);
        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
}
