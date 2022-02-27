package com.example.kddgmn.service;

import com.example.kddgmn.model.Account;
import com.example.kddgmn.model.Customer;
import com.example.kddgmn.repository.AccountRepository;
import com.example.kddgmn.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
