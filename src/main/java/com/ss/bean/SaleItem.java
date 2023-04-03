package com.ss.bean;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author XKS22
 * 销售订单明细
 * id:销售订单明细ID
 * saleOrderId:关联到销售订单ID
 * name:商品名称
 * price:商品单价
 * count:商品数量
 */
@Entity
@Table(name = "sale_item")
@Setter
@Getter
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sale_item_id")
    private Long id;
    /*@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=SaleOrder.class)
    @JoinColumn(name="sale_order_id")*/
    private Long saleOrderId;
    private Long customerId;
    @Column(name = "sale_item_name")
    private String name;
    @Column(name = "sale_item_price")
    private Double price;
    @Column(name = "sale_item_count")
    private Long count;
}
