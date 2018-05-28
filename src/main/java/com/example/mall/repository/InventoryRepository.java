package com.example.mall.repository;

import com.example.mall.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Modifying
    @Transactional
    @Query(value = "insert into Inventory(id,count,lockedCount) values(?1,0,0)",nativeQuery = true)
    int saveInventoryInfo(Long id);


    @Modifying
    @Transactional
    @Query("update Inventory i set i.lockedCount = i.lockedCount + ?2 where i.id = ?1")
    int updateLockedCount(Long productId, Integer lockedCount);

    @Modifying
    @Transactional
    @Query("update Inventory i set i.count =  ?2 where i.id = ?1")
    int updateCountById(Long productId, Integer count);
}
