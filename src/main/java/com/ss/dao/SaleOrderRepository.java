package com.ss.dao;

import com.ss.bean.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SaleOrderRepository extends JpaRepository<SaleOrder,Long> {
    @Query(value = "select flag from sale_order where sale_order_id=?1",nativeQuery = true)
    public String findFlagByOrderId(Long orderId);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update sale_order set flag = ?1 where sale_order_id = ?2",nativeQuery=true)
    public void updateFlagByOrderId(String string, Long orderId);

    @Query(value = "select sale_order_id from sale_order where DATE_FORMAT(sale_order_sale_date,'%Y-%m-%d') between ?1 and ?2",nativeQuery=true)
    public List<Long> findOrderIdByTime(String min, String max);
}
