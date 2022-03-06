package com.example.kddgmn.service;

import com.example.kddgmn.model.Category;
import com.example.kddgmn.model.Product;
import com.example.kddgmn.repository.CategoryRepository;
import com.example.kddgmn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Category> getAll(){
         return categoryRepository.findAll();
    }
    public Integer save(Category category){
        try{
            categoryRepository.save(category);
        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
    public Integer deleteById(Integer id){
        try{
            categoryRepository.deleteById(id);
        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
    public Integer update(Category category){
        try{
            Category categoryOld = categoryRepository.findById(category.getIdCategory()).get();
            int hoatDongcu = categoryOld.getIsActive();
            int hoatDongMoi = category.getIsActive();
            if(hoatDongcu != hoatDongMoi){
                if(category.getIsActive() == 0){
                    List<Product> productList = productRepository.findByIdcategory(category.getIdCategory());
                    for (int i = 0; i < productList.size(); i++) {
                        Product product = productList.get(i);
                        product.setIsActive(0);
                        productRepository.save(product);
                    }
                }else{
                    List<Product> productList = productRepository.findByIdcategory(category.getIdCategory());
                    for (int i = 0; i < productList.size(); i++) {
                        Product product = productList.get(i);
                        product.setIsActive(1);
                        productRepository.save(product);
                    }
                }
            }

            categoryRepository.save(category);
        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
}
