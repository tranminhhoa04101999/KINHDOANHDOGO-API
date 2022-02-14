package com.example.kddgmn.controller;

import com.example.kddgmn.model.Status;
import com.example.kddgmn.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping("/allstatus")
    public List<Status> getAll(){
        return  statusService.getAll();
    }
}
