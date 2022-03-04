package com.example.kddgmn.controller;

import com.example.kddgmn.model.RatesProduct;
import com.example.kddgmn.payload.CommonResponse;
import com.example.kddgmn.payload.RatesProductRecive;
import com.example.kddgmn.service.RatesProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RatesProductController {

    @Autowired
    private RatesProductService ratesProductService;

    @GetMapping("/rateProductGetAll")
    public List<RatesProduct> getAll(){
        return ratesProductService.getAll();
    }

    @PostMapping("/saveRatesProduct")
    public CommonResponse saveRatesProduct(@RequestBody List<RatesProductRecive> ratesProductRecive){
        return ratesProductService.saveList(ratesProductRecive);
    }

    @GetMapping("/orderCheckRate")
    public List<Integer> OrderCheckRate(@RequestParam("idCustomer") int idCustomer){
        return ratesProductService.OrderCheckRate(idCustomer);
    }
}
