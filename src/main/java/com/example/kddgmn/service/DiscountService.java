package com.example.kddgmn.service;

import com.example.kddgmn.model.Discount;
import com.example.kddgmn.model.Product;
import com.example.kddgmn.payload.ImgProductResponse;
import com.example.kddgmn.payload.ProductSearchResponse;
import com.example.kddgmn.repository.DiscountRepository;
import com.example.kddgmn.repository.ImgProductRepository;
import com.example.kddgmn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImgProductService imgProductService;

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
    public Integer UpdateDiscountByidDiscountAndId(List<Integer> listIdProduct,int idDiscount){
        try{
            for (int i = 0; i < listIdProduct.size(); i++) {
                productRepository.UpdateDiscountByidDiscountAndId(idDiscount,listIdProduct.get(i));
            }
        }catch (Exception ex){

            return 0;
        }

        return 1;
    }
    public List<ProductSearchResponse> ShowfindProductByIdDiscount (int idDiscount){
        List<ProductSearchResponse> productSearchResponses = new ArrayList<>();
        List<Product> products = productRepository.findByIdDiscount(idDiscount);

        for (int i = 0; i < products.size(); i++) {
            List<ImgProductResponse> imgProductResponses = imgProductService.getImgByIdProd(products.get(i).getIdProduct());

            ProductSearchResponse productSearchResponse = new ProductSearchResponse(
                    products.get(i).getIdProduct(),products.get(i).getNameProduct(),products.get(i).getPrice(),products.get(i).getColor()
                    ,products.get(i).getQuantity(),imgProductResponses.get(0).getImgURL()
            );
            productSearchResponses.add(productSearchResponse);
        }

        return productSearchResponses;
    }
    public Integer RemoveByidDiscountAndId(int idProduct){
        try{
            productRepository.RemoveDiscountById(idProduct);
        }catch (Exception ex){

            return 0;
        }

        return 1;
    }
}
