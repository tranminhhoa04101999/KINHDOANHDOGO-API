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
    public List<Account> getAll(){
        return accountService.getAll();
    }

    @GetMapping("/loginadmin")
    public List<Account> getAccountLoginadmin(@RequestParam("email") String email, @RequestParam("password") String password){
        return accountService.getAccountLogined(email,password);
    }
    @GetMapping("/login")
    public List<Account> findByEmailByPasswordIsCustomer(@RequestParam("email") String email, @RequestParam("password") String password){
        return accountService.findByEmailByPasswordIsCustomer(email,password);
    }
    @PostMapping("/addAccount")
    public Integer save(@RequestBody Account account){
        return  accountService.save(account);
    }
    @PostMapping("/deleteById")
    public Integer deleteById(@RequestParam("id") Integer id){
        return accountService.deleteById(id);
    }
    @GetMapping("/getAccountById")
    public Account getAccountById(@RequestParam("id") Integer id){
        return accountService.getAccountById(id);
    }

    @PostMapping("/changePass")
    public Integer changePass(@RequestParam("email") String email,@RequestParam("oldPass") String oldPass,@RequestParam("newPass") String newPass){
        return accountService.changePassword(email, oldPass, newPass);
    }
    @PostMapping("/updateAccountWithAdmin")
    public Integer updateAccountWithAdmin(@RequestBody Account account){
        return accountService.updateAccountWithAdmin(account);
    }
}
