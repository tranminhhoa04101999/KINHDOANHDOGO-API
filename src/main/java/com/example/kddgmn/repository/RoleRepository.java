package com.example.kddgmn.repository;

import com.example.kddgmn.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query("SELECT v FROM Role v WHERE v.idRole = 1")
    List<Role> getRoleId1();
}
