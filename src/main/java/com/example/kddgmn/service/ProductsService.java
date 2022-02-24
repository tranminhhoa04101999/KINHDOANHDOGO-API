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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
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
    public PagedResponse<Product> findWithIdCategoryPage(Integer page, Integer size, Integer idCategory){
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC,"price");
        Page<Product> products = productRepository.findWithIdCategoryPage(idCategory,pageable);

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
    public Integer allQuantityByIdDiscount(Integer idDiscount){
        List<Product> products = productRepository.findByIdDiscount(idDiscount);
        var countLength = products.size();

        return countLength;
    }
    public Integer updateIdDiscountWhenRemoveDiscount(Integer idDiscount){
        try{
            List<Product> products = productRepository.findByIdDiscount(idDiscount);
            for (int i = 0; i < products.size(); i++) {
                products.get(i).setDiscount(null);
                save(products.get(i));
            }

        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
    public Integer updateIdCategoryWhenRemoveCategory(Integer idCategory){
        try{
            List<Product> products = productRepository.findByIdcategory(idCategory);
            for (int i = 0; i < products.size(); i++) {
                products.get(i).setCategory(null);
                save(products.get(i));
            }

        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
    public List<Product> findByNewOneWeek(){
        LocalDate dateNowSub = LocalDate.now().minusDays(7); // ngày hiện tại trừ 7 ngày
        Date dateAdd = Date.from(dateNowSub.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return productRepository.findByNewOneWeek(dateAdd);
    }
    public PagedResponse<Product> findByNewOneWeekPage(Integer page,Integer size){
        LocalDate dateNowSub = LocalDate.now().minusDays(7); // ngày hiện tại trừ 7 ngày
        Date dateAdd = Date.from(dateNowSub.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC,"price");
        Page<Product> products = productRepository.findByNewOneWeekPage(dateAdd,pageable);

        if (products.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), products.getNumber(), products.getSize(),
                    products.getTotalElements(), products.getTotalPages(), products.isLast());
        }
        List<Product> productRes = products.getContent();


        return new PagedResponse<>(productRes, products.getNumber(), products.getSize(), products.getTotalElements(),
                products.getTotalPages(), products.isLast());
    }
    public List<Product> findByHaveDiscount(){return productRepository.findByHaveDiscount();}
    public PagedResponse<Product> findByNamePage(Integer page, Integer size, String name){
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC,"price");
        Page<Product> products = productRepository.findByNamePage(name,pageable);

        if (products.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), products.getNumber(), products.getSize(),
                    products.getTotalElements(), products.getTotalPages(), products.isLast());
        }
        List<Product> productRes = products.getContent();


        return new PagedResponse<>(productRes, products.getNumber(), products.getSize(), products.getTotalElements(),
                products.getTotalPages(), products.isLast());
    }
    public PagedResponse<Product> findByHaveDiscountPage(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC,"price");
        Page<Product> products = productRepository.findByHaveDiscountPage(pageable);

        if (products.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), products.getNumber(), products.getSize(),
                    products.getTotalElements(), products.getTotalPages(), products.isLast());
        }
        List<Product> productRes = products.getContent();


        return new PagedResponse<>(productRes, products.getNumber(), products.getSize(), products.getTotalElements(),
                products.getTotalPages(), products.isLast());
    }
}
