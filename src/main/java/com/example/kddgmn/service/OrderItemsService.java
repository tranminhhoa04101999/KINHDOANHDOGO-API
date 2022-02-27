package com.example.kddgmn.service;

import com.example.kddgmn.model.OrderItems;
import com.example.kddgmn.model.Orders;
import com.example.kddgmn.model.Product;
import com.example.kddgmn.payload.OrderItemProduct;
import com.example.kddgmn.repository.OrderItemsRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsService {
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private OrdersService ordersService;

    public List<OrderItems> getAll(){
        return orderItemsRepository.findAll();
    }

    public Integer saveOrderItem(List<OrderItemProduct> orderItemProduct){
        try{
            int idMaxOrders = ordersService.findMaxId();
            for (int i = 0; i < orderItemProduct.size(); i++) {
                OrderItems orderItems = new OrderItems();
                Orders orders = new Orders(idMaxOrders);
                Product product = new Product(orderItemProduct.get(i).getIdProduct());
                orderItems.setOrder(orders);
                orderItems.setProduct(product);
                orderItems.setQuantity(orderItemProduct.get(i).getQuantity());

                orderItemsRepository.save(orderItems);
            }
        }catch (Exception ex){
            return 0;
        }

        return  1;
    }
}
