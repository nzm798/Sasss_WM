package com.ss.dao;

import com.ss.bean.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem,Long> {
    @Query(value = "from PurchaseItem where purchaseOrderId=?1")
    public List<PurchaseItem> findPurchaseItemByPurchaseId(Long orderId);
}
