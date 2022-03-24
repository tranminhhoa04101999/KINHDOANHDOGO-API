package com.example.kddgmn.controller;

import com.example.kddgmn.model.Status;
import com.example.kddgmn.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/statusThanhToan")
    public List<Status> gettren3 (){
        return  statusService.getThanhtoanroi();
    }
    @GetMapping("/statusgetlonhon")
    public List<Status> getLonHon (@RequestParam("idStatus")int idStatus){
        return  statusService.getLonhon(idStatus);
    }
}
