package com.example.kddgmn.service;

import com.example.kddgmn.model.Role;
import com.example.kddgmn.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }
    public List<Role> getRole1(){
        return roleRepository.getRoleId1();
    }
}
