package com.ss.dao;

import com.ss.bean.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface PurchaseRepository extends JpaRepository<PurchaseOrder,Long> {
    @Query(value = "select purchase_order_id from purchase_order where inDate=?1",nativeQuery = true)
    public Long findPurchaseOrderByTotal(Date indate);

    @Query(value = "select flag from purchase_order where purchase_order_id=?1",nativeQuery = true)
    public String findFlagByOrderId(Long orderId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update purchase_order set flag = ?1 where purchase_order_id = ?2",nativeQuery=true)
    public void updateFlagByOrderId(String string, Long orderId);
}
