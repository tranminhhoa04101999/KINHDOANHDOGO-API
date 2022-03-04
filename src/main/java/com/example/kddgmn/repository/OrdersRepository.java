package com.example.kddgmn.repository;

import com.example.kddgmn.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    @Query("SELECT MAX(v.idOrders) FROM Orders v")
    Integer findIdMax();

    @Query("SELECT v FROM Orders v WHERE v.status.idStatus<= :idStatus AND (v.idOrders= :idOrders OR v.phone= :phone)")
    List<Orders> searchOrderByIdOrPhone (@Param("idStatus") int idStatus,@Param("idOrders") int idOrders,@Param("phone") String phone);

    @Transactional
    @Modifying
    @Query("UPDATE Orders v SET v.status.idStatus= :idStatus WHERE v.idOrders= :idOrders")
    void UpdateStatusByidStatusAndId(@Param("idStatus") int idStatus,@Param("idOrders") int idOrders);

    @Query("SELECT v FROM Orders v WHERE v.customer.idCustomer= :idCustomer")
    List<Orders> findByIdCustomer(@Param("idCustomer") int idCustomer);

    @Query("SELECT v FROM Orders v WHERE v.dateCreate >= :date")
    List<Orders> findByDateCreateLonHon(@Param("date") Date date);

    @Query("SELECT v FROM Orders v WHERE year(v.dateCreate) = year(:date) AND v.status.idStatus != 6")
    List<Orders> findByYearCreate(@Param("date") Date date);

    @Query("SELECT v.idOrders FROM Orders v WHERE v.customer.idCustomer= :idCustomer AND v.status.idStatus = 5")
    List<Integer> findIdByCustomerAndStatus5(@Param("idCustomer") int idCustomer);
}
