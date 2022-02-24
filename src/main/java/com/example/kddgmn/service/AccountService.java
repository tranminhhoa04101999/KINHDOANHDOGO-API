package com.example.kddgmn.service;

import com.example.kddgmn.model.Account;
import com.example.kddgmn.model.Role;
import com.example.kddgmn.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
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
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(account.getPassword().getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            account.setPassword(myHash);
            Role role = new Role(3);

            account.setRole(role);
            accountRepository.save(account);
        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
    public List<Account> getAccountLogined(String email,String password){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            return accountRepository.layAccountLogin(email,myHash);

        }catch (Exception ex){
            System.out.println("error get account login: ");
        }

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
