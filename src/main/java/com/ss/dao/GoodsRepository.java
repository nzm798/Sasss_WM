package com.ss.dao;

import com.ss.bean.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GoodsRepository extends JpaRepository<Goods,Long> {
    @Query(value = "select * from goods where goods_name=?1",nativeQuery = true)
    public Goods findGoodsByName(String name);

    @Query(value = "select goods_id from Goods where goods_name=?1",nativeQuery = true)
    public Long findGoodsIdByGoodsName(String name);

    @Query(value = "select goods_name from Goods where goods_id = ?1",nativeQuery = true)
    public String findGoodsNameById(Long key);
}
