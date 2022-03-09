package com.example.kddgmn.repository;

import com.example.kddgmn.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    @Query("SELECT v FROM Account v WHERE v.email = :email and v.password = :matkhau")
    List<Account> layAccountLogin(@Param("email") String email, @Param("matkhau") String matkhau);

    @Query("SELECT v FROM Account v WHERE v.email = :email and v.password = :matkhau and v.role.idRole= 3 and v.isActive = 1")
    List<Account> findByEmailByPasswordIsCustomer(@Param("email") String email, @Param("matkhau") String matkhau);

    @Query("SELECT v FROM Account v WHERE v.email = :email")
    List<Account> findByEmail(@Param("email") String email);

    @Query("SELECT MAX(v.idAccount) FROM Account v")
    Integer findIdMax();

    @Transactional
    @Modifying
    @Query("UPDATE Account v SET v.isActive= :isActive, v.role.idRole= :idRole WHERE v.idAccount= :idAccount")
    void updateIsActiveById(@Param("isActive") int isActive,@Param("idRole") int idRole,@Param("idAccount") int idAccount);

}
