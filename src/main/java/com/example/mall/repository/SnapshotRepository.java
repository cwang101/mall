package com.example.mall.repository;

import com.example.mall.entity.Snapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SnapshotRepository extends JpaRepository<Snapshot, Long> {
    @Transactional
    Snapshot save(Snapshot productSnap);

    List<Snapshot> findByOrderId(Long orderId);
}
