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

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }
}
