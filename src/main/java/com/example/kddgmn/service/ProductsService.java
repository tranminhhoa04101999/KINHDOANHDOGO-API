package com.example.kddgmn.service;

import com.example.kddgmn.model.ImgProduct;
import com.example.kddgmn.model.OrderItems;
import com.example.kddgmn.model.Product;
import com.example.kddgmn.payload.CommonResponse;
import com.example.kddgmn.payload.OrderItemProduct;
import com.example.kddgmn.payload.PagedResponse;
import com.example.kddgmn.payload.TopSaleResponse;
import com.example.kddgmn.repository.ImgProductRepository;
import com.example.kddgmn.repository.OrderItemsRepository;
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
import java.util.*;

@Service
public class ProductsService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private ImgProductRepository imgProductRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }
    public Product getProductwithid(Integer id){
       return productRepository.findById(id).get();
    }
    public Integer save(Product product){
        try {

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

    public List<CommonResponse> checkQuantity(List<OrderItemProduct> orderItemProducts){
        List<CommonResponse> commonResponses = new ArrayList<>();
        for (int i = 0; i < orderItemProducts.size(); i++) {
            Product product = productRepository.findById(orderItemProducts.get(i).getIdProduct()).get();
            if(product.getQuantity() - orderItemProducts.get(i).getQuantity() >= 0){
            }
            else{
                CommonResponse item = new CommonResponse(product.getIdProduct(), product.getNameProduct() +" không đủ số lượng");
                commonResponses.add(item);
            }
        }
        return commonResponses;
    }
    public List<TopSaleResponse> getTop3Sale(){
        List<TopSaleResponse> topSaleResponseList = new ArrayList<>();
        List<OrderItems> orderItemProductList = orderItemsRepository.findAll();

        for (int i = 0; i < orderItemProductList.size(); i++) {
            Product product = productRepository.findById(orderItemProductList.get(i).getProduct().getIdProduct()).get();
            List<ImgProduct> imgProductList = imgProductRepository.getImgWithIdProd(product.getIdProduct());
            if(topSaleResponseList.size() == 0){
                double priceDiscount = 0;
                if(product.getDiscount() != null){
                    if(product.getDiscount().getIsActive() == 1){
                        priceDiscount= product.getPrice() * (1-product.getDiscount().getPercent());
                    }
                }
                TopSaleResponse topSaleResponse = new TopSaleResponse(product.getIdProduct(),product.getNameProduct(),product.getPrice(),priceDiscount
                ,orderItemProductList.get(i).getQuantity(),imgProductList.get(0).getImgURL());
                topSaleResponseList.add(topSaleResponse);
            }else{
                for (int j = 0; j < topSaleResponseList.size(); j++) {
                    if(topSaleResponseList.get(j).getIdProduct() == orderItemProductList.get(i).getProduct().getIdProduct()){
                        TopSaleResponse topSaleResponse = topSaleResponseList.get(j);
                        topSaleResponse.setQuantity(topSaleResponse.getQuantity() + orderItemProductList.get(i).getQuantity());
                        topSaleResponseList.set(j,topSaleResponse);
                    }

                    if(j == topSaleResponseList.size() - 1){
                        double priceDiscount = 0;
                        if(product.getDiscount() != null){
                            if(product.getDiscount().getIsActive() == 1){
                                priceDiscount= product.getPrice() * (1 - product.getDiscount().getPercent());
                            }
                        }
                        TopSaleResponse topSaleResponse = new TopSaleResponse(product.getIdProduct(),product.getNameProduct(),product.getPrice(),priceDiscount
                                ,orderItemProductList.get(i).getQuantity(),imgProductList.get(0).getImgURL());
                        topSaleResponseList.add(topSaleResponse);
                        break;
                    }

                }
            }
        }
        Collections.sort(topSaleResponseList, new Comparator<TopSaleResponse>() {
            @Override
            public int compare(TopSaleResponse o1, TopSaleResponse o2) {
                return o1.getQuantity() > o2.getQuantity() ? -1 : 0;
            }

        });
        List<TopSaleResponse> topSaleResponseList1 = topSaleResponseList.subList(0,3);
        return topSaleResponseList1;
    }
}














