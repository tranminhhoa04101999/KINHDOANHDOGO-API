package com.example.kddgmn.controller;


import com.example.kddgmn.model.Category;
import com.example.kddgmn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/allcategory")
    public List<Category> getAll(){
        return categoryService.getAll();
    }
    @PostMapping("/saveCategory")
    public Integer save(@RequestBody Category category){
        return categoryService.save(category);
    }
}
