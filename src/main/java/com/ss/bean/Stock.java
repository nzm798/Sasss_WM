package com.ss.bean;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author XKS22
 * 库存
 * id:库存ID
 * goodsId:商品ID
 * count:商品库存数量
 * area:存放地方
 */
@Entity
@Table(name = "stock")
@Component
@Getter
@Setter
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stock_id")
    private Long id;
    /*@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=Goods.class)*/
    @JoinColumn(name = "goods_id")
    private Long goodsId;
    @Column(name = "stock_count")
    private Long counts;
    @Column(name = "stock_area")
    private String area;
}
