package com.example.kddgmn.service;

import com.example.kddgmn.model.Employee;
import com.example.kddgmn.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }
    public List<Employee> findByIdAccount(int idAccount) {
        return employeeRepository.findByIdAccount(idAccount);
    }
    public Integer save(Employee employee){
        try {
             employeeRepository.save(employee);
        }catch (Exception ex){
            return 0;
        }
        return 1;
    }

}
