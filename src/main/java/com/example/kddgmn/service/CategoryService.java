package com.example.kddgmn.service;

import com.example.kddgmn.model.Category;
import com.example.kddgmn.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

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
}
