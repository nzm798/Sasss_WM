package com.ss.dao;

import com.ss.bean.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Transactional
    @Modifying(clearAutomatically = true) //刷新一级缓存、防止再次查找的时候不是最新更新的数据
    @Query(value = "update Stock set stock_count= ?1 where goods_Id=?2", nativeQuery = true)
    //nativeQuery=true代表这个是sql的原生语句，可以直接放到sql用
    public void updateStockCountByGoodsId(Long count, Long goodsId);

    @Query(value = "select counts from Stock where goods_id=?1", nativeQuery = true)
    public Long findCountByGoodsId(Long goodsId);

    @Query(value = "select * from Stock where goods_id=?1", nativeQuery = true)
    public Stock findStockByGoodsId(Long goodsId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Stock set stock_area = ?1 where goods_id = ?2", nativeQuery = true)
    public void updateStockAreaByGoodsId(String area, Long goodsId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Stock set stock_count = ?1 where goods_id = ?2", nativeQuery = true)
    public void updateStockCount(Long count,Long goodsId);

    @Query("from Stock")
    public List<Stock> findAllGoodsCount();
}
