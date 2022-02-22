package com.example.kddgmn.service;

import com.example.kddgmn.model.Account;
import com.example.kddgmn.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAll(){
        return accountRepository.findAll();
    }
    public Integer save(Account account){
        try{
            accountRepository.save(account);
        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
    public List<Account> getAccountLogined(String email,String password){
        return accountRepository.layAccountLogin(email,password);
    }
    public Integer deleteById(Integer id){
        try{
            accountRepository.deleteById(id);

        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
    public Account getAccountById(Integer id){
        return accountRepository.findById(id).get();
    }
}
