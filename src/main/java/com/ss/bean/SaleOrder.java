package com.ss.bean;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author XKS22
 * 销售订单
 * id:销售订单ID
 * customerId:用户ID
 * pay:结算方式
 * saleDate:销售时间
 * total:总金额
 */
@Entity
@Table(name = "sale_order")
@Setter
@Getter
public class SaleOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sale_order_id")
    private Long id;
    /*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=Customer.class)
    @JoinColumn(name="customer_id")*/
    private Long customerId;
    @Column(name = "sale_order_pay")
    private String pay;
    @Column(name = "sale_order_sale_date")
    private Date saleDate;
    @Column(name = "sale_order_total")
    private Double total;
    private String flag;
}
