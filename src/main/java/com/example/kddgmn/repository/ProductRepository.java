package com.example.kddgmn.repository;

import com.example.kddgmn.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT max(v.idProduct) FROM Product v")
    Integer findIdMax();
    @Query("SELECT v FROM Product v WHERE v.isActive = 1")
    Page<Product> findAllWithPage (Pageable pageable);

    @Query("SELECT v FROM Product v WHERE v.category.idCategory= :idCategory AND v.isActive = 1")
    Page<Product> findWithIdCategoryPage (@Param("idCategory") Integer idCategory,Pageable pageable);

    @Query("SELECT v FROM Product v WHERE v.category.idCategory= :idCategory")
    List<Product> findByIdcategory(@Param("idCategory") Integer idCategory);

    @Query("SELECT v FROM Product v WHERE v.discount.idDiscount= :idDiscount")
    List<Product> findByIdDiscount(@Param("idDiscount") Integer idDiscount);

    @Query("SELECT v FROM Product v WHERE v.addDate> :date")
    List<Product> findByNewOneWeek(@Param("date") Date date);

    @Query("SELECT v FROM Product v WHERE v.addDate> :date AND v.isActive = 1")
    Page<Product> findByNewOneWeekPage(@Param("date") Date date,Pageable pageable);

    @Query("SELECT v FROM Product v WHERE v.discount.idDiscount > 0 AND v.discount.isActive = 1")
    List<Product> findByHaveDiscount();

    @Query("SELECT v FROM Product v WHERE v.discount.idDiscount > 0 AND v.discount.isActive = 1 AND v.isActive = 1")
    Page<Product> findByHaveDiscountPage(Pageable pageable);

    @Query("SELECT v FROM Product v WHERE v.nameProduct LIKE %:nameProduct% AND v.isActive = 1")
    Page<Product> findByNamePage(@Param("nameProduct") String nameProduct,Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Product v SET v.discount.idDiscount= :idDiscount WHERE v.idProduct= :idProduct")
    void UpdateDiscountByidDiscountAndId(@Param("idDiscount") int idDiscount,@Param("idProduct") int idProduct);

    @Transactional
    @Modifying
    @Query("UPDATE Product v SET v.discount.idDiscount= null WHERE v.idProduct= :idProduct")
    void RemoveDiscountById(@Param("idProduct") int idProduct);

}
