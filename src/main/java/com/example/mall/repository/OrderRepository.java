package com.example.mall.repository;

import com.example.mall.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderInfo, Long> {
    @Transactional
    OrderInfo save(OrderInfo order);

    @Modifying
    @Transactional
    @Query("update OrderInfo o set o.totalPrice = ?2 where o.id = ?1")
    int updateTotalPriceById(Long id,Double totalPrice);


    @Modifying
    @Transactional
    @Query("update OrderInfo o set o.status = ?2, o.paidTime = ?3 where o.id = ?1")
    int updatePaidStatusById(Long id, String status, String paidTime);

    @Modifying
    @Transactional
    @Query("update OrderInfo o set o.status = ?2, o.withdrawnTime = ?3 where o.id = ?1")
    int updateWithdrawnStatusById(Long id, String status, String withdrawnTime);

   OrderInfo findById(long id);

    List<OrderInfo> findByUserId(Long userId);
}
