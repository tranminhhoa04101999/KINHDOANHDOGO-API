package com.example.kddgmn.service;

import com.example.kddgmn.model.Account;
import com.example.kddgmn.model.Customer;
import com.example.kddgmn.model.Orders;
import com.example.kddgmn.payload.ChartOrdersResponse;
import com.example.kddgmn.repository.AccountRepository;
import com.example.kddgmn.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<Customer> getALl(){
        return customerRepository.findAll();
    }
    public Integer save(Customer customer){
        try{
            Integer idMax = accountRepository.findIdMax();
            Account acc = new Account(idMax);
            customer.setAccount(acc);
            customerRepository.save(customer);
        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
    public Customer findByIdAccount(Integer idAccount){
        return customerRepository.findByIdAccount(idAccount);
    }
    public Integer saveWithNoAccount(Customer customer){
        try {
            customerRepository.save(customer);
        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
    public Integer findMaxId (){
        return customerRepository.findIdMax();
    }

    public List<ChartOrdersResponse> findByDatecreateInYear (){
        LocalDate dateNowSub = LocalDate.now();
        Date date = Date.from(dateNowSub.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<ChartOrdersResponse> chartOrdersResponses = new ArrayList<>();

        List<Customer> customersList = customerRepository.findByDatecreateInYear(date);

        for (int i = 0; i < 12; i++) {
            ChartOrdersResponse chartOrdersResponse = new ChartOrdersResponse();
            Date date1 = new Date();
            date1.setTime(1);
            date1.setMonth(i);
            date1.setYear(new Date().getYear());
            chartOrdersResponse.setDate(date1);
            chartOrdersResponse.setQuantity(0);
            chartOrdersResponses.add(chartOrdersResponse);
        }

        for (int i = 0; i < customersList.size(); i++) {
            ChartOrdersResponse chartOrdersResponse = new ChartOrdersResponse();
            for (int j = 0; j < chartOrdersResponses.size(); j++) {
                if(chartOrdersResponses.get(j).getDate().getMonth() == customersList.get(i).getDateCreate().getMonth()){
                    chartOrdersResponse.setQuantity(chartOrdersResponses.get(j).getQuantity() + 1);
                    chartOrdersResponse.setDate(chartOrdersResponses.get(j).getDate());
                    chartOrdersResponses.set(j,chartOrdersResponse);
                }

            }
        }
        return  chartOrdersResponses;
    }
}
