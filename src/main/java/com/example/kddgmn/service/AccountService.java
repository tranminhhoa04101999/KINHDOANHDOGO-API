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
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.security.MessageDigest;
import java.util.Properties;

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
                employee.setAddress("");
                employee.setDateBegin(new Date());
                employee.setIsWorking(account.getIsActive());
                accountRepository.save(account);
                Integer idMax = accountRepository.findIdMax();
                employee.setIdAccount(idMax);
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
            if(account.getPassword() == ""){ //mật khẩu không có thay đổi
                accountRepository.updateIsActiveById(account.getIsActive(),account.getRole().getIdRole(),account.getIdAccount());
            }else{
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(account.getPassword().getBytes());
                byte[] digest = md.digest();
                String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
                account.setPassword(myHash);
                accountRepository.save(account);
            }
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
    public int getforgotPassword(String email){
        try{
            final String fromEmail = "tmhoa111@gmail.com";
            // Mat khai email cua ban
            final String password = "eunhyukL1";
            // dia chi email nguoi nhan
            final String toEmail = email;
            final String subject = "TMH ĐỒ GỖ MỸ NGHỆ";

            Random rand = new Random();
            int randomPass = rand.nextInt((999999-100000) + 1) + 100000;

            final String body = "Mật khẩu mới của bạn là " + randomPass;
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

            List<Account> account = accountRepository.findByEmail(email);
            if(account.size() == 0){
                return 2; // email khong ton tai
            }

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(String.valueOf(randomPass).getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            account.get(0).setPassword(myHash);
            accountRepository.save(account.get(0));

        }catch (Exception ex){
            return 0;
        }

        return 1;
    }
}
