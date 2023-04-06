package com.ss.bean;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * @author XKS22
 *	销售退货
 *id:销售退货ID
 *customer：关联到顾客ID
 *pay:支付方式
 *returnDate:退货时间
 *total:总金额
 */
@Entity
@Table(name = "sale_return_order")
@Setter
@Getter
public class SaleReturnOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="sale_return_order_id")
    private Long id;
    /*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=Customer.class)
    @JoinColumn(name="customer_id")*/
    private Long customerId;
    @Column(name="sale_return_order_pay")
    private String pay;
    @Column(name="sale_return_order_returnDate")
    private Date returnDate;
    @Column(name="sale_return_order_total")
    private Double total;
}
