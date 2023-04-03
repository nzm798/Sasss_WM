package com.ss.bean;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author XKS22
 * 采购退货
 * id：采购退货编号
 * suppliersId:供应商ID
 * pay:支付方式
 * outDate:退货时间
 * total:总金额
 */
@Entity
@Table(name = "return_order")
@Getter
@Setter
public class ReturnOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "return_order_id")
    private Long id;
    /*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=Suppliers.class)
    @JoinColumn(name="suppliers_id")*/
    private Long suppliersId;
    @Column(name = "return_order_pay")
    private String pay;
    @Column(name = "return_order_outDate")
    private Date outDate;
    @Column(name = "return_order_total")
    private Double total;
}
