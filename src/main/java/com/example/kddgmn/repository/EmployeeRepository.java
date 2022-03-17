package com.example.kddgmn.repository;

import com.example.kddgmn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query("SELECT v FROM Employee v WHERE v.idAccount= :idAccount")
    List<Employee> findByIdAccount (@Param("idAccount") int idAccount);
}
