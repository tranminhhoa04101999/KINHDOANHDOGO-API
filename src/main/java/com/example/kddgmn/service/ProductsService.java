package com.example.kddgmn.service;

import com.example.kddgmn.model.Product;
import com.example.kddgmn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class ProductsService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }
    public Product getProduct(Integer id){
       return productRepository.getById(id);
    }
    public Integer save(Product product){
        try {
            productRepository.save(product);
        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
    public Integer remove(Integer id){
        try {
            productRepository.deleteById(id);
        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
    public Integer getIdMax(){
        return productRepository.findIdMax();
   }
}
