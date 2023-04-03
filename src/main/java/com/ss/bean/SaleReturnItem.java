package com.ss.bean;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author XKS22
 *	销售退货明细
 */
@Entity
@Table(name = "sale_return_item")
@Getter
@Setter
public class SaleReturnItem {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="sale_return_item_id")
    private Long id;
    /*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=SaleReturnOrder.class)
    @JoinColumn(name="sale_return_order_id")*/
    private Long saleReturnOrderId;
    /*@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=Goods.class)
    @JoinColumn(name="goods_name")*/
    private String goodsName;
    @Column(name="sale_return_item_price")
    private Double price;
    @Column(name="sale_return_item_count")
    private Long count;
}
