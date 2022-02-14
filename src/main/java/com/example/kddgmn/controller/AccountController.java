package com.example.kddgmn.controller;

import com.example.kddgmn.model.Account;
import com.example.kddgmn.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts(){
        return accountService.getAccounts();
    }

    @GetMapping("/loginadmin")
    @ResponseBody
    public List<Account> getAccountLogin(@RequestParam("email") String email, @RequestParam("password") String password){
        return accountService.getAccountLogined(email,password);
    }
}
