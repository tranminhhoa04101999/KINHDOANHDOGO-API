package com.example.kddgmn.service;

import com.example.kddgmn.model.Orders;
import com.example.kddgmn.model.Product;
import com.example.kddgmn.model.RatesProduct;
import com.example.kddgmn.payload.*;
import com.example.kddgmn.repository.OrdersRepository;
import com.example.kddgmn.repository.RatesProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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

    public RatesProductDetailResponse getRateDetail(int idProduct){
        List<RatesProduct> ratesProductList = ratesProductRepository.findByIdProduct(idProduct);
        List<RatesProductResponse> ratesProductResponseList = new ArrayList<>();
        int one = 0;
        int two = 0 ;
        int three = 0;
        int four = 0;
        int five = 0;
        for (int i = 0; i < ratesProductList.size(); i++) {
            RatesProductResponse ratesProductResponse = new RatesProductResponse(ratesProductList.get(i).getOrders().getCustomer().getName()
            ,"",ratesProductList.get(i).getPointRate(),ratesProductList.get(i).getDescRate());
            // thêm time uused
            String timeUsed = "Đã sử dụng được ";
            Date date = new Date();
            int years = date.getYear() - ratesProductList.get(i).getOrders().getDateEnd().getYear();
            int months = date.getMonth() - ratesProductList.get(i).getOrders().getDateEnd().getMonth();
            int days = date.getDay() - ratesProductList.get(i).getOrders().getDateEnd().getDay();
            long sss= date.getTime() - ratesProductList.get(i).getOrders().getDateEnd().getTime();
            timeUsed = timeUsed + sss/86400000+" ngày";
            ratesProductResponse.setTimeUsed(timeUsed);
            ratesProductResponseList.add(ratesProductResponse);
            if(ratesProductList.get(i).getPointRate() == 1){
                one +=1;
            } else if(ratesProductList.get(i).getPointRate() == 2){
                two +=1;
            }else if(ratesProductList.get(i).getPointRate() == 3){
                three +=1;
            }else if(ratesProductList.get(i).getPointRate() == 4){
                four +=1;
            }else if(ratesProductList.get(i).getPointRate() == 5){
                five +=1;
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        int mauso = 1;
        if(ratesProductList.size()>0) mauso=ratesProductList.size();
        float percentTotal = (float)(one*1 + two*2 + three*3 + four*4 + five*5) / mauso;

        return new RatesProductDetailResponse(ratesProductList.size(),one,two,three,four,five,percentTotal,ratesProductResponseList);
    }
}













