package com.example.kddgmn.service;

import com.example.kddgmn.model.Status;
import com.example.kddgmn.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public List<Status> getAll(){
        return  statusRepository.getAllCustom();
    }
    public List<Status> getThanhtoanroi(){
        return  statusRepository.gettren3();
    }
    public List<Status> getLonhon (int idStatus){
        return statusRepository.getlonhon(idStatus);
    }

}
