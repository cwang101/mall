package com.example.mall.repository;

import com.example.mall.entity.LogisticsRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface LogisticsRecordRepository extends JpaRepository<LogisticsRecord, Long> {
    LogisticsRecord findById(long id);

    LogisticsRecord findByIdAndOrderId(Long id,Long orderId);


    @Modifying
    @Transactional
    @Query("update LogisticsRecord l set l.logisticsStatus = 'shipping', l.outboundTime = ?3 where l.id = ?1 and l.orderId = ?2")
    int updateShippingStatus(Long id, Long orderId, String outboundTime);

    @Modifying
    @Transactional
    @Query("update LogisticsRecord l set l.logisticsStatus = 'signed', l.signedTime = ?3 where l.id = ?1 and l.orderId = ?2")
    int updateSignedStatus(Long id, Long orderId, String signedTime);

    LogisticsRecord save(LogisticsRecord logisticsRecord);


}
