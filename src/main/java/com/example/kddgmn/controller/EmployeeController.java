package com.example.kddgmn.controller;


import com.example.kddgmn.model.Employee;
import com.example.kddgmn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/employeeFindByIdAccount")
    public List<Employee> findByIdAccount(@RequestParam("idAccount") int idAccount){
        return  employeeService.findByIdAccount(idAccount);
    }

    @PostMapping("/employeeSave")
    public Integer save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }
}
