package com.example.kddgmn.service;

import com.example.kddgmn.model.Account;
import com.example.kddgmn.model.Customer;
import com.example.kddgmn.model.Employee;
import com.example.kddgmn.model.Role;
import com.example.kddgmn.repository.AccountRepository;
import com.example.kddgmn.repository.CustomerRepository;
import com.example.kddgmn.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

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

            if(accountRepository.findByEmail(account.getEmail()).size() > 0){
                return 2; //tai khoan da ton tai
            }

            accountRepository.save(account);
        }catch (Exception ex){
            return 0;
        }
        return 1;
    }
    public Integer saveAddmin(Account account){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(account.getPassword().getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            account.setPassword(myHash);


            if(accountRepository.findByEmail(account.getEmail()).size() > 0){
                return 2; //tai khoan da ton tai
            }
            Employee employee = new Employee();
            Customer customer = new Customer();

            if(account.getRole().getIdRole() == 1 || account.getRole().getIdRole() == 2)
            {
                employee.setName("");
                employee.setPhone("");
                employee.setAccount(account);
                employee.setAddress("");
                employee.setDateBegin(new Date());
                employee.setIsWorking(account.getIsActive());
                accountRepository.save(account);
                employeeRepository.save(employee);
            }
            if(account.getRole().getIdRole() == 3){
                customer.setName("");
                customer.setPhone("");
                customer.setAddress("");
                customer.setDateCreate(new Date());
                customer.setAccount(account);
                accountRepository.save(account);
                customerRepository.save(customer);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }
        return 1;
    }
    public Integer updateAccountWithAdmin(Account account){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(account.getPassword().getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            account.setPassword(myHash);

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
    public List<Account> findByEmailByPasswordIsCustomer(String email,String password){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            return accountRepository.findByEmailByPasswordIsCustomer(email,myHash);

        }catch (Exception ex){
            System.out.println("error get account login: ");
        }

        return accountRepository.findByEmailByPasswordIsCustomer(email,password);
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

    public Integer changePassword(String email,String OldPass,String newPass){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(OldPass.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

            MessageDigest md1 = MessageDigest.getInstance("MD5");
            md1.update(newPass.getBytes());
            byte[] digest1 = md1.digest();
            String myHash1 = DatatypeConverter.printHexBinary(digest1).toUpperCase();

            List<Account> accounts = accountRepository.layAccountLogin(email,myHash);
            if(accounts.size() == 0){
                return  3;
            }
            Account account = accounts.get(0);
            account.setPassword(myHash1);
            accountRepository.save(account);
        }catch (Exception ex){
            return 0;
        }
        return  1;
    }
}
