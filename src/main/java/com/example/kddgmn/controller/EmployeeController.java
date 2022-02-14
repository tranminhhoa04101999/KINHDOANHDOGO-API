package com.example.kddgmn.controller;


import com.example.kddgmn.model.Employee;
import com.example.kddgmn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/allemployee")
    public List<Employee> getAllEmployee(){
        return employeeService.getAll();
    }
}
