package com.example.kddgmn.service;

import com.example.kddgmn.model.*;
import com.example.kddgmn.payload.*;
import com.example.kddgmn.repository.CustomerRepository;
import com.example.kddgmn.repository.OrderItemsRepository;
import com.example.kddgmn.repository.OrdersRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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

    @Autowired
    private CustomerRepository customerRepository;

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
                String imgProd = "";
                if(imgProductResponses.size()==0){
                    imgProd = "defaultImage";
                }else{
                    imgProd = imgProductResponses.get(0).getImgURL();
                }
                ProductSearchResponse productSearchResponse = new ProductSearchResponse(product.getIdProduct(), product.getNameProduct(),orderitems.get(j).getPriceCurrent()
                        ,product.getColor(),orderitems.get(j).getQuantity(),imgProd);
                productSearchResponses.add(productSearchResponse);
            }

            searchOrderResponse.setProductSearchResponses(productSearchResponses);
            searchOrderResponse.setOrders(orders.get(i));

            searchOrderResponses.add(searchOrderResponse);
        }

        return searchOrderResponses;
    }

    public List<SearchOrderResponse> searchOrderByIdAccount(int idAccount){
        // lấy ra customer
        Customer customer = customerRepository.findByIdAccount(idAccount);
        List<SearchOrderResponse> searchOrderResponses = new ArrayList<>();

        if(customer == null){
            return searchOrderResponses;
        }
        List<Orders> ordersList = ordersRepository.findByIdCustomer(customer.getIdCustomer());

        for (int i = 0; i < ordersList.size(); i++) {
            List<ProductSearchResponse> productSearchResponses = new ArrayList<>();
            SearchOrderResponse searchOrderResponse = new SearchOrderResponse();
            List<OrderItems> orderitems = orderItemsRepository.findByIdOrders(ordersList.get(i).getIdOrder());

            for (int j = 0; j < orderitems.size(); j++) {
                Product product = orderitems.get(j).getProduct();
                List<ImgProductResponse> imgProductResponses = imgProductService.getImgByIdProd(product.getIdProduct());
                ProductSearchResponse productSearchResponse = new ProductSearchResponse(product.getIdProduct(), product.getNameProduct(),orderitems.get(j).getPriceCurrent()
                        ,product.getColor(),orderitems.get(j).getQuantity(),imgProductResponses.get(0).getImgURL());
                productSearchResponses.add(productSearchResponse);
            }

            searchOrderResponse.setProductSearchResponses(productSearchResponses);
            searchOrderResponse.setOrders(ordersList.get(i));

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
            // khi xác nhận đơn hàng thì gửi mail thông báo
            if(idStatus == 2){
                Orders orders = ordersRepository.findById(idOrders).get();
                final String fromEmail = "tmhoa111@gmail.com";
                // Mat khai email cua ban
                final String password = "eunhyukL1";
                // dia chi email nguoi nhan
                final String toEmail = orders.getCustomer().getAccount().getEmail();
                final String subject = "TMH ĐỒ GỖ MỸ NGHỆ";

                final String body = "Đơn hàng có mã " +orders.getIdOrder()+ " đặt bên shop đã được xác nhận";
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
                props.put("mail.smtp.port", "587"); //TLS Port
                props.put("mail.smtp.auth", "true"); //enable authentication
                props.put("mail.smtp.starttls.enable", "true"); //enable
                Authenticator auth = new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, password);
                    }
                };
                Session session = Session.getInstance(props, auth);
                MimeMessage msg = new MimeMessage(session);
                //set message headers
                msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                msg.addHeader("format", "flowed");
                msg.addHeader("Content-Transfer-Encoding", "8bit");
                msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));
                msg.setReplyTo(InternetAddress.parse(fromEmail, false));
                msg.setSubject(subject, "UTF-8");
                msg.setText(body, "UTF-8");
                msg.setSentDate(new Date());
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
                Transport.send(msg);
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }
        return 1;
    }
    public List<ChartOrdersResponse> getDataChartOrders (){
        LocalDate dateNowSub = LocalDate.now().minusDays(6); // ngày hiện tại trừ 7 ngày
        Date dateAdd = Date.from(dateNowSub.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<ChartOrdersResponse> chartOrdersResponses = new ArrayList<>();
        List<Orders> ordersList = ordersRepository.findByDateCreateLonHon(dateAdd);

        for (int i = 0; i < ordersList.size(); i++) {
            ChartOrdersResponse chartOrdersResponse = new ChartOrdersResponse();
            if(chartOrdersResponses.size() == 0){
                chartOrdersResponse.setDate(ordersList.get(i).getDateCreate());
                chartOrdersResponse.setQuantity(1);
                chartOrdersResponses.add(chartOrdersResponse);

            }else{
                for (int j = 0; j < chartOrdersResponses.size(); j++) {
                    if(chartOrdersResponses.get(j).getDate().equals(ordersList.get(i).getDateCreate())){
                        chartOrdersResponse.setQuantity(chartOrdersResponses.get(j).getQuantity() +1);
                        chartOrdersResponse.setDate(chartOrdersResponses.get(j).getDate());
                        chartOrdersResponses.set(j,chartOrdersResponse);
                    }
                    else {
                        if(j == chartOrdersResponses.size() - 1){
                            chartOrdersResponse.setDate(ordersList.get(i).getDateCreate());
                            chartOrdersResponse.setQuantity(1);
                            chartOrdersResponses.add(chartOrdersResponse);
                            break;
                        }
                    }
                }
            }
        }
        return  chartOrdersResponses;
    }
    public List<ChartTotalResponse> getDataChartTotal (){
        LocalDate dateNowSub = LocalDate.now();
        Date date = Date.from(dateNowSub.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<ChartTotalResponse> ChartTotalResponses = new ArrayList<>();

        List<Orders> ordersList = ordersRepository.findByYearCreate(date);

        for (int i = 0; i < 12; i++) {
            ChartTotalResponse chartTotalResponse = new ChartTotalResponse();
            Date date1 = new Date();
            date1.setTime(1);
            date1.setMonth(i);
            date1.setYear(new Date().getYear());
            chartTotalResponse.setDate(date1);
            chartTotalResponse.setTotal(0.0);
            ChartTotalResponses.add(chartTotalResponse);
        }

        for (int i = 0; i < ordersList.size(); i++) {
            ChartTotalResponse chartTotalResponse = new ChartTotalResponse();
                for (int j = 0; j < ChartTotalResponses.size(); j++) {
                    if(ChartTotalResponses.get(j).getDate().getMonth() == ordersList.get(i).getDateCreate().getMonth()){
                        chartTotalResponse.setTotal(ChartTotalResponses.get(j).getTotal() );
                        chartTotalResponse.setDate(ChartTotalResponses.get(j).getDate());
                        ChartTotalResponses.set(j,chartTotalResponse);
                    }

            }
        }
        return  ChartTotalResponses;
    }
    public int huyOrder(int idOrder){
        try{
            ordersRepository.UpdateStatusByidStatusAndId(6,idOrder);
        }catch (Exception ex){
            return 0;
        }
        return 1;
    }

    public List<ChartTotalResponse> findBydateBeginAnddateEnd(Date dateBegin,Date dateEnd){
        List<ChartTotalResponse> chartTotalResponseList = new ArrayList<>();
        List<Orders> ordersList = ordersRepository.findBydateBeginAnddateEnd(dateBegin, dateEnd);
        Double totalEnd = 0.00;
        Double totalBegin = 0.00;
        for (int i = 0; i < ordersList.size(); i++) {
            if(dateBegin.getDay() == ordersList.get(i).getDateCreate().getDay() && dateBegin.getMonth() == ordersList.get(i).getDateCreate().getMonth()
            && dateBegin.getYear() == ordersList.get(i).getDateCreate().getDay()){

            }

        }

        ChartTotalResponse chartTotalResponsebegin = new ChartTotalResponse(dateBegin,totalBegin);
        chartTotalResponseList.add(chartTotalResponsebegin);

        ChartTotalResponse chartTotalResponseEnd = new ChartTotalResponse(dateEnd,totalEnd);
        chartTotalResponseList.add(chartTotalResponseEnd);

        return chartTotalResponseList;
    }
    public List<ChartOrdersResponse> findBydateBeginAnddateEndAll(Date dateBegin, Date dateEnd){
        List<ChartOrdersResponse> chartOrdersResponseList = new ArrayList<>();
        List<Orders> ordersList = ordersRepository.findBydateBeginAnddateEndAll(dateBegin, dateEnd);
        int quantityEnd = 0;
        int quantityBegin = 0;
        for (int i = 0; i < ordersList.size(); i++) {
            if(dateBegin.getDay() == ordersList.get(i).getDateCreate().getDay() && dateBegin.getMonth() == ordersList.get(i).getDateCreate().getMonth()
                    && dateBegin.getYear() == ordersList.get(i).getDateCreate().getDay()){
                quantityBegin += 1;
            }
            quantityEnd += 1;
        }

        ChartOrdersResponse chartOrdersResponsebe = new ChartOrdersResponse(dateBegin,quantityBegin);
        chartOrdersResponseList.add(chartOrdersResponsebe);

        ChartOrdersResponse chartOrdersResponseen = new ChartOrdersResponse(dateEnd,quantityEnd);
        chartOrdersResponseList.add(chartOrdersResponseen);


        return chartOrdersResponseList;
    }

}
