package com.example.kddgmn.controller;

import com.example.kddgmn.model.Discount;
import com.example.kddgmn.model.Product;
import com.example.kddgmn.payload.ProductSearchResponse;
import com.example.kddgmn.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping("/alldiscount")
    public List<Discount> getAll(){
        return discountService.getAll();
    }

    @PostMapping("/adddiscount")
    public  Integer save(@RequestBody  Discount discount){
        return  discountService.save(discount);
    }
    @PostMapping("/deleleByidDiscount")
    public Integer deleteById(@RequestParam("idDiscount") Integer id){
        return  discountService.deleteById(id);
    }
    @PostMapping("/UpdateProductByidDiscountAndId")
    public Integer UpdateProductByidDiscountAndId (@RequestBody List<Integer> listIdProduct,@RequestParam("idDiscount") int idDiscount){
        return discountService.UpdateDiscountByidDiscountAndId(listIdProduct, idDiscount);
    }
    @GetMapping("/discountFindProductByIdDiscount")
    public List<ProductSearchResponse> ShowProductByIdDiscount(@RequestParam("idDiscount") int idDiscount){
        return discountService.ShowfindProductByIdDiscount(idDiscount);
    }
    @PostMapping("/RemoveByIdProduct")
    public Integer RemoveByIdProduct(@RequestParam("idProduct") int idProduct){
        return discountService.RemoveByidDiscountAndId(idProduct);
    }
}
