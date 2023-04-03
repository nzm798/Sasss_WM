package com.ss.bean;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author XKS22
 * 采购退货明细
 * id:采购退货明细ID
 * returnOrderId:关联采购退货单
 * name:商品名称
 * price:商品单价
 * count:商品总数
 */
@Entity
@Table(name = "return_item")
@Getter
@Setter
public class ReturnItem {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="return_item_id")
    private Long id;
    /*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=ReturnOrder.class)
    @JoinColumn(name="return_order_id")*/
    private Long returnOrderId;
    /*@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=Goods.class)
    @JoinColumn(name="goods_name")*/
    private String goodsName;
    @Column(name="return_item_price")
    private Double price;
    @Column(name="return_item_count")
    private Long count;
}
