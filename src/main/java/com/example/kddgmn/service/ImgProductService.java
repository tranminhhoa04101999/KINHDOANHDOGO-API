package com.example.kddgmn.service;

import com.example.kddgmn.model.ImgProduct;
import com.example.kddgmn.payload.ImgProductResponse;
import com.example.kddgmn.repository.ImgProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImgProductService {
    @Autowired
    private ImgProductRepository imgProductRepository;

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
}
