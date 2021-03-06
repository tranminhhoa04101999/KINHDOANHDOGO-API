package com.example.kddgmn.controller;

import com.example.kddgmn.model.Product;
import com.example.kddgmn.payload.CommonResponse;
import com.example.kddgmn.payload.OrderItemProduct;
import com.example.kddgmn.payload.PagedResponse;
import com.example.kddgmn.payload.TopSaleResponse;
import com.example.kddgmn.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("/allproduct")
    public List<Product> getAll(){
        return productsService.getAll();
    }
    @PostMapping("/addproduct")
    public Integer addProduct(@RequestBody Product product){
        return productsService.save(product); // return 1 la thanh cong
    }
    @PostMapping("/deleteproduct")
    public Integer deleteProduct(@RequestParam("idProd") Integer idProd){
        return productsService.remove(idProd);// return 1 la thanh cong
    }
    @GetMapping("/getIdProductMax")
    public Integer getIdProductMax(){
        return productsService.getIdMax();
    }
    @GetMapping("/getproductbyid")
    public Product getProductById(@RequestParam("idProduct") Integer idProduct){
        return productsService.getProductwithid(idProduct);
    }
    @GetMapping("/allProductPage")
    public PagedResponse<Product> getAllPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size
            ,@RequestParam("priceBegin") double priceBegin,@RequestParam("priceEnd") double priceEnd){
        return productsService.getAllProductPage(page,size,priceBegin,priceEnd);
    }
    @GetMapping("/findWithIdCategoryPage")
    public PagedResponse<Product> findWithIdCategoryPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size,@RequestParam("idCategory") Integer idCategory
            ,@RequestParam("priceBegin") double priceBegin,@RequestParam("priceEnd") double priceEnd){
        return productsService.findWithIdCategoryPage(page,size,idCategory,priceBegin,priceEnd);
    }
    @GetMapping("/allproductby")
    public List<Product> findByIdCategory(@RequestParam("idCategory") Integer idCategory){
        return productsService.findByIdCategory(idCategory);
    }
    @GetMapping("/allquantityby")
    public Integer allQuantityByIdCategory(@RequestParam("idCategory") Integer idCategory){
        return productsService.allQuantityByIdCategory(idCategory);
    }
    @GetMapping("/allquantitybyIdDiscount")
    public Integer allQuantityByIdDiscount(@RequestParam("idDiscount") Integer idDiscount){
        return productsService.allQuantityByIdDiscount(idDiscount);
    }
    @PostMapping("/updateIdDiscountWhenRemoveDiscount")
    public Integer updateIdDiscountWhenRemoveDiscount(@RequestParam("idDiscount") Integer idDiscount){
        return productsService.updateIdDiscountWhenRemoveDiscount(idDiscount);
    }
    @PostMapping("/updateIdCategoryWhenRemoveCategory")
    public Integer updateIdCategoryWhenRemoveCategory(@RequestParam("idCategory") Integer idCategory){
        return productsService.updateIdCategoryWhenRemoveCategory(idCategory);
    }
    @GetMapping("/findByNewOneWeek")
    public List<Product> findByNewOneWeek(){
        return productsService.findByNewOneWeek();
    }

    @GetMapping("/findByHaveDiscount")
    public List<Product> findByHaveDiscount(){
        return productsService.findByHaveDiscount();
    }

    @GetMapping("/findByHaveDiscountPage")
    public PagedResponse<Product> findByHaveDiscount(@RequestParam("page") Integer page, @RequestParam("size") Integer size
            ,@RequestParam("priceBegin") double priceBegin,@RequestParam("priceEnd") double priceEnd){
        return productsService.findByHaveDiscountPage(page,size,priceBegin,priceEnd);
    }
    @GetMapping("/findByNamePage")
    public PagedResponse<Product> findByNamePage(@RequestParam("page") Integer page, @RequestParam("size") Integer size,@RequestParam("nameProduct") String name){
        return productsService.findByNamePage(page,size,name);
    }
    @GetMapping("/findByNewOneWeekPage")
    public PagedResponse<Product> findByNewOneWeekPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size
            ,@RequestParam("priceBegin") double priceBegin,@RequestParam("priceEnd") double priceEnd){
        return productsService.findByNewOneWeekPage(page,size,priceBegin,priceEnd);
    }
    @PostMapping("/checkQuantityProduct")
    public List<CommonResponse> checkQuantity(@RequestBody List<OrderItemProduct> orderItemProducts){
        return productsService.checkQuantity(orderItemProducts);
    }
    @GetMapping("/getTop3Sale")
    public List<TopSaleResponse> getTop3Sale(){
        return productsService.getTop3Sale();
    }

    @PostMapping("/updateDiscountWithListIdCate")
    public int updateDiscountWithListIdCate(@RequestBody List<Integer> idCateList,@RequestParam("idDiscount") int idDiscount){
        return productsService.updateDiscountByListIdCate(idCateList, idDiscount);
    }

    @PostMapping("/updateDiscountNullByListIdCate")
    public int updateDiscountNullByListIdCate(@RequestBody List<Integer> idCateList){
        return productsService.updateDiscountNullByListIdCate(idCateList);
    }

    @GetMapping("/getTopSaleWithDate")
    public List<TopSaleResponse> getTopSaleWithDate(@RequestParam("begin")Date begin,@RequestParam("end") Date end){
        return productsService.getTopSaleWithDate(begin, end);
    }
}
