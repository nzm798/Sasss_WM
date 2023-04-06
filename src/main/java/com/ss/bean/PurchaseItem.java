package com.ss.bean;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author XKS22
 * 采购单明细
 * id:
 * name:商品名称
 * purchaseOrderId:关联到采购单ID
 * price:单价
 * count:数量
 */
@Entity
@Table(name = "purchase_item")
@Component
@Getter
@Setter
public class PurchaseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "purchase_item_id")
    private Long id;
    /*@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=Goods.class)
    @JoinColumn(name="goods_name")*/
    private String name;
    /*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=Suppliers.class)
    @JoinColumn(name="suppliers_id",nullable=false)*/
    private Long supplierId;
    /*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=PurchaseOrder.class)
    @JoinColumn(name="purchase_order_id")*/
    private Long purchaseOrderId;
    @Column(name = "purchase_price")
    private Double price;
    @Column(name = "purchase_count")
    private Long count;
}
