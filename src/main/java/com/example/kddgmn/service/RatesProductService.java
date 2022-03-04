package com.example.kddgmn.service;

import com.example.kddgmn.model.Orders;
import com.example.kddgmn.model.Product;
import com.example.kddgmn.model.RatesProduct;
import com.example.kddgmn.payload.CommonResponse;
import com.example.kddgmn.payload.OrderCheckRateResponse;
import com.example.kddgmn.payload.RatesProductRecive;
import com.example.kddgmn.repository.OrdersRepository;
import com.example.kddgmn.repository.RatesProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatesProductService {
    @Autowired
    private RatesProductRepository ratesProductRepository;
    @Autowired
    private OrdersRepository ordersRepository;

    public List<RatesProduct> getAll(){
        return ratesProductRepository.findAll();
    }

    public CommonResponse saveList(List<RatesProductRecive> ratesProductReciveList){
        try{
            List<RatesProduct> ratesProductList = new ArrayList<>();
            for (int i = 0; i < ratesProductReciveList.size(); i++) {
                // check coi đánh giá chưa
                RatesProduct ratesProductCheck = ratesProductRepository.findByIdProductAndIdOrders(ratesProductReciveList.get(i).getIdProduct()
                ,ratesProductReciveList.get(i).getIdOrder());
                if(ratesProductCheck != null){
                    return new CommonResponse(0,"Đơn hàng đã được đánh giá");
                }

                Product product = new Product(ratesProductReciveList.get(i).getIdProduct());
                Orders orders =  new Orders(ratesProductReciveList.get(i).getIdOrder());
                RatesProduct ratesProduct = new RatesProduct(product,orders,ratesProductReciveList.get(i).getDescRate()
                        ,ratesProductReciveList.get(i).getPointRate());
                ratesProductList.add(ratesProduct);
            }

            for (int i = 0; i < ratesProductList.size(); i++) {
                ratesProductRepository.save(ratesProductList.get(i));
            }

        }catch (Exception ex){
            return new CommonResponse(0,"Bạn đã đánh giá thất bại! catch error");
        }
        return new CommonResponse(1,"Bạn đã đánh giá thành công!");
    }
    public List<Integer> OrderCheckRate(int idCustomer){
        List<Integer> idOrderList = ordersRepository.findIdByCustomerAndStatus5(idCustomer);
        List<Integer> checkList = new ArrayList<>();

        for (int i = 0; i < idOrderList.size(); i++) {
            List<RatesProduct> ratesProductList = ratesProductRepository.findByIdOrders(idOrderList.get(i));
            if(ratesProductList.size() > 0){
                checkList.add(idOrderList.get(i));
            }
        }

        return checkList;
    }
}













