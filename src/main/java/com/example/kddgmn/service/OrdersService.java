package com.example.kddgmn.service;

import com.example.kddgmn.model.Customer;
import com.example.kddgmn.model.Orders;
import com.example.kddgmn.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomerService customerService;

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
}
