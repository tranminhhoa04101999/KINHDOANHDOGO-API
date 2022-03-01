package com.example.kddgmn.service;

import com.example.kddgmn.model.*;
import com.example.kddgmn.payload.ImgProductResponse;
import com.example.kddgmn.payload.ProductSearchResponse;
import com.example.kddgmn.payload.SearchOrderResponse;
import com.example.kddgmn.repository.OrderItemsRepository;
import com.example.kddgmn.repository.OrdersRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private  ImgProductService imgProductService;

    public List<Orders> getAll(){
        return ordersRepository.findAll();
    }
    public Integer saveOrder(Orders orders,String name){
        try{
            if(orders.getCustomer().getIdCustomer() == 0){
                Customer customer = new Customer();
                customer.setPhone(orders.getPhone());
                customer.setName(name);
                customer.setAddress(orders.getAddress());
                customer.setDateCreate(new Date());
                customer.setAccount(null);
                int check = customerService.saveWithNoAccount(customer);
                if(check == 0){
                    return 0;
                }
                int idMax = customerService.findMaxId();
                Customer customer1 = new Customer(idMax);
                orders.setCustomer(customer1);

                ordersRepository.save(orders);
            }
            else{
                ordersRepository.save(orders);
            }
        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
    public Integer findMaxId(){
        return ordersRepository.findIdMax();
    }
    public List<SearchOrderResponse> searchOrderByIdOrPhone(int idOrders, int idStatus, String phone){
        List<Orders> orders = ordersRepository.searchOrderByIdOrPhone(idStatus, idOrders, phone);
        List<SearchOrderResponse> searchOrderResponses = new ArrayList<>();


        for (int i = 0; i < orders.size(); i++) {
            List<ProductSearchResponse> productSearchResponses = new ArrayList<>();
            SearchOrderResponse searchOrderResponse = new SearchOrderResponse();
            List<OrderItems> orderitems = orderItemsRepository.findByIdOrders(orders.get(i).getIdOrder());

            for (int j = 0; j < orderitems.size(); j++) {
                Product product = orderitems.get(j).getProduct();
                List<ImgProductResponse> imgProductResponses = imgProductService.getImgByIdProd(product.getIdProduct());
                ProductSearchResponse productSearchResponse = new ProductSearchResponse(product.getIdProduct(), product.getNameProduct(),orderitems.get(j).getPriceCurrent()
                        ,product.getColor(),product.getQuantity(),imgProductResponses.get(0).getImgURL());
                productSearchResponses.add(productSearchResponse);
            }

            searchOrderResponse.setProductSearchResponses(productSearchResponses);
            searchOrderResponse.setOrders(orders.get(i));

            searchOrderResponses.add(searchOrderResponse);
        }

        return searchOrderResponses;
    }
    public Integer UpdateStatusByidStatusAndId(int idStatus,int idOrders,int idEmployee){
        try {
            if(idStatus >= 5){
                Date date = new Date();
                Orders orders = ordersRepository.findById(idOrders).get();
                orders.setDateEnd(date);
                Employee employee = new Employee(idEmployee);
                orders.setEmployee(employee);
                Status status = new Status(idStatus);
                orders.setStatus(status);
                ordersRepository.save(orders);
            }
            else{
                Date date = new Date();
                Orders orders = ordersRepository.findById(idOrders).get();
                orders.setDateModified(date);
                Employee employee = new Employee(idEmployee);
                orders.setEmployee(employee);
                Status status = new Status(idStatus);
                orders.setStatus(status);
                ordersRepository.save(orders);
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }
        return 1;
    }
}
