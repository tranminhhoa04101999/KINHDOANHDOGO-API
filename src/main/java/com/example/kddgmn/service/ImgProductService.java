package com.example.kddgmn.service;

import com.example.kddgmn.model.ImgProduct;
import com.example.kddgmn.model.Product;
import com.example.kddgmn.payload.ImgProductResponse;
import com.example.kddgmn.payload.ImgWithIdProdResponse;
import com.example.kddgmn.payload.PagedResponse;
import com.example.kddgmn.repository.ImgProductRepository;
import com.example.kddgmn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ImgProductService {
    @Autowired
    private ImgProductRepository imgProductRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<ImgProduct> getAll(){
        return imgProductRepository.findAll();
    }

    //get khong product
    public List<ImgProductResponse> getImgByIdProd(Integer idProd){
        List<ImgProduct> data = imgProductRepository.getImgWithIdProd(idProd);
        List<ImgProductResponse> dataRes = new ArrayList<>();
        for (int i=0;i<data.size();i++){
            ImgProductResponse dt = new ImgProductResponse();
            dt.setIdImgProduct(data.get(i).getIdImgProduct());
            dt.setImgURL(data.get(i).getImgURL());

            dataRes.add(dt);
        }

        return dataRes;
    }

    //get with idProduct
    public List<ImgWithIdProdResponse> getImgWithIdProd(Integer idProduct){
        List<ImgProduct> data = imgProductRepository.getImgWithIdProd(idProduct);
        List<ImgWithIdProdResponse> dataRes = new ArrayList<>();
        for (int i=0;i<data.size();i++){
            ImgWithIdProdResponse dt = new ImgWithIdProdResponse();
            dt.setIdImgProduct(data.get(i).getIdImgProduct());
            dt.setImgURL(data.get(i).getImgURL());
            dt.setIdProduct(data.get(i).getProduct().getIdProduct());

            dataRes.add(dt);
        }

        return dataRes;
    }
    public List<ImgWithIdProdResponse> getAllHaveIdProd(){
        List<ImgProduct> data = imgProductRepository.findAll();
        List<ImgWithIdProdResponse> dataRes = new ArrayList<>();
        for (int i=0;i<data.size();i++){
            ImgWithIdProdResponse dt = new ImgWithIdProdResponse();
            dt.setIdImgProduct(data.get(i).getIdImgProduct());
            dt.setImgURL(data.get(i).getImgURL());
            dt.setIdProduct(data.get(i).getProduct().getIdProduct());

            dataRes.add(dt);
        }
        return dataRes;
    }

    public Integer Save(String imgURL,Integer idProduct) {
        try {
            var item = new ImgProduct();
            var product = productRepository.getById(idProduct);
            item.setImgURL(imgURL);
            item.setProduct(product);
            imgProductRepository.save(item);
        }catch (Exception ex){
            return 0;
        }
        return  1;
    };
    public Integer deleteById(Integer idImg){
        try {
            imgProductRepository.deleteById(idImg);
        }catch (Exception ex){
            return 0;
        }
        return  1;
    }
    public Integer deleteByIdProduct(Integer idProduct){
        try {
            imgProductRepository.deleteByIdProduct(idProduct);
        }catch (Exception ex){
            System.out.println(ex);

            return 0;
        }
        return  1;
    }

}
