package com.ss.bean;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Entity
@Table(name = "purchase_order")
@Component
@Getter
@Setter
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="purchase_order_id")
    private Long id;
    /*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=Suppliers.class)
    @JoinColumn(name="suppliers_id",nullable=false)*/
    private Long suppliersId;
    @Column(name="purchase_order_pay")
    private String pay;
    private Date inDate;
    @Column(name="purchase_order_total")
    private Double total;
    private String flag;
}
