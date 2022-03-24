package com.example.kddgmn.repository;

import com.example.kddgmn.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status,Integer> {
    @Query("SELECT v FROM Status v WHERE v.idStatus != 3")
    List<Status> getAllCustom();

    @Query("SELECT v FROM Status v WHERE v.idStatus >= 3")
    List<Status> gettren3();

    @Query("SELECT v FROM Status v WHERE v.idStatus >= :idStatus AND v.idStatus != 3")
    List<Status> getlonhon(@Param("idStatus") int idStatus);
}
