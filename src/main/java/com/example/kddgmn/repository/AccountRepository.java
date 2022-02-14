package com.example.kddgmn.repository;

import com.example.kddgmn.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    @Query("SELECT v FROM Account v WHERE v.email = :email and v.password = :matkhau")
    List<Account> layAccountLogin(@Param("email") String email, @Param("matkhau") String matkhau);
}
