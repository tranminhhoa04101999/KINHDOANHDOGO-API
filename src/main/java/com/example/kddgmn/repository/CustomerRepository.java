package com.example.kddgmn.repository;

import com.example.kddgmn.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query("SELECT v FROM Customer v WHERE v.account.idAccount= :idAccount")
    Customer findByIdAccount (@Param("idAccount") Integer idAccount);

    @Query("SELECT MAX(v.idCustomer) FROM Customer v")
    Integer findIdMax();

    @Query("SELECT v FROM Customer v WHERE year(v.dateCreate) = year(:date)")
    List<Customer> findByDatecreateInYear(@Param("date")Date date);
}
