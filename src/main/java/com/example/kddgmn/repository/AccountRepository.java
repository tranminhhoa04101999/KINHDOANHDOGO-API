package com.example.kddgmn.repository;

import com.example.kddgmn.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account,Integer> {
}
