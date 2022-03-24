package com.example.kddgmn.service;

import com.example.kddgmn.model.OrderItems;
import com.example.kddgmn.model.OrderItemsId;
import com.example.kddgmn.model.Orders;
import com.example.kddgmn.model.Product;
import com.example.kddgmn.payload.OrderItemProduct;
import com.example.kddgmn.repository.OrderItemsRepository;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsService {
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ProductsService productsService;

    public List<OrderItems> getAll(){
        return orderItemsRepository.findAll();
    }

    public Integer saveOrderItem(List<OrderItemProduct> orderItemProduct,int thanhToanPaypal){
        try{
            int idMaxOrders = ordersService.findMaxId();
            for (int i = 0; i < orderItemProduct.size(); i++) {
                OrderItems orderItems = new OrderItems();
                Orders orders = new Orders(idMaxOrders);
                Product product = new Product(orderItemProduct.get(i).getIdProduct());
                OrderItemsId orderItemsId = new OrderItemsId();
                orderItemsId.setIdOrders(idMaxOrders);
                orderItemsId.setIdProduct(orderItemProduct.get(i).getIdProduct());
                orderItems.setIdOrderItems(orderItemsId);
                orderItems.setOrders(orders);
                orderItems.setProduct(product);
                orderItems.setQuantity(orderItemProduct.get(i).getQuantity());

                // lấy product ra trừ số lượng khi thêm thành công~
                Product prod = productsService.getProductwithid(orderItemProduct.get(i).getIdProduct());
                Double price = prod.getPrice();
                if(prod.getDiscount() != null ){
                    if(prod.getDiscount().getIsActive() == 1) {
                        price = price - (price * prod.getDiscount().getPercent());
                    }
                }
                orderItems.setPriceCurrent(price);

                orderItemsRepository.save(orderItems);

                // thanh toán online thì không trừ số lượng vì FE chưa check đc
                if(thanhToanPaypal == 1){
                    prod.setQuantity(prod.getQuantity() - orderItemProduct.get(i).getQuantity());
                    productsService.save(prod);
                }

            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }

        return  1;
    }
    public List<OrderItems> findByIdOrders (int idOrders){
        return orderItemsRepository.findByIdOrders(idOrders);
    }
    public int checkProductHaveOrderItems(int idProduct){
        return orderItemsRepository.checkProductHaveOrderItems(idProduct);
    }
}
