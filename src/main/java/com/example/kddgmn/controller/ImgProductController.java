package com.example.kddgmn.controller;

import com.example.kddgmn.model.ImgProduct;
import com.example.kddgmn.payload.ImgProductResponse;
import com.example.kddgmn.payload.ImgWithIdProdResponse;
import com.example.kddgmn.service.ImgProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ImgProductController {
    @Autowired
    private ImgProductService imgProductService;

    @GetMapping("/allimgproduct")
    public List<ImgProduct> getAll(){
        return imgProductService.getAll();
    }

    @GetMapping("/imgproductwith")
    public List<ImgProductResponse> getImgWithIdProd (@RequestParam("idProduct") Integer id){
        return imgProductService.getImgByIdProd(id);
    }
    @GetMapping("/imgwithidprod")
    public List<ImgWithIdProdResponse> getImgHaveIdProd(@RequestParam("idProduct")Integer id){
        return imgProductService.getImgWithIdProd(id);
    }
    @GetMapping("/allimghaveidprod")
    public List<ImgWithIdProdResponse> getAllImgHaveIdProd(){
        return imgProductService.getAllHaveIdProd();
    }
    @PostMapping("/saveimageproduct")
    public Integer save(@RequestParam("imgURL") String imgURL,@RequestParam("idProduct") Integer idProduct){
        return imgProductService.Save(imgURL,idProduct); // return 1 la thanh cong
    }
    @PostMapping("/deleteImgById")
    public Integer delete(@RequestParam("idImgProduct") Integer idImgProduct){
        return imgProductService.deleteById(idImgProduct);
    }
    @PostMapping("/deleteImgByIdProduct")
    public Integer deleteImgByIdProduct(@RequestParam("idProduct") Integer idProduct){
        return imgProductService.deleteByIdProduct(idProduct);
    }

}
