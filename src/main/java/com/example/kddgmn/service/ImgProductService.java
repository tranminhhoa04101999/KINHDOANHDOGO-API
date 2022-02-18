package com.example.kddgmn.service;

import com.example.kddgmn.model.ImgProduct;
import com.example.kddgmn.payload.ImgProductResponse;
import com.example.kddgmn.repository.ImgProductRepository;
import com.example.kddgmn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<ImgProductResponse> getImgWithIdProd(Integer idProd){
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
}
