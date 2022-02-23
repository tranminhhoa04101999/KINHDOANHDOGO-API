package com.example.kddgmn.service;

import com.example.kddgmn.model.Product;
import com.example.kddgmn.payload.PagedResponse;
import com.example.kddgmn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
public class ProductsService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }
    public Product getProductwithid(Integer id){
       return productRepository.findById(id).get();
    }
    public Integer save(Product product){
        try {
            System.out.println(product.getPrice());

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

    public PagedResponse<Product> getAllProductPage(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC,"price");
        Page<Product> products = productRepository.findAllWithPage(pageable);

        if (products.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), products.getNumber(), products.getSize(),
                    products.getTotalElements(), products.getTotalPages(), products.isLast());
        }
        List<Product> productRes = products.getContent();


        return new PagedResponse<>(productRes, products.getNumber(), products.getSize(), products.getTotalElements(),
                products.getTotalPages(), products.isLast());
    }
    public List<Product> findByIdCategory(Integer idCategory){
        return productRepository.findByIdcategory(idCategory);
    }
    public Integer allQuantityByIdCategory(Integer idCategory){
        List<Product> products = productRepository.findByIdcategory(idCategory);
        var countLength = products.size();

        return countLength;
    }

}
