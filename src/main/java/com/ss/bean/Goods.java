package com.ss.bean;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author XKS22
 *   商品
 * id:
 * name:商品名称
 * unit:单位
 * space:商品产地
 * supplierId:供应商编号
 * approvrId:批准文号
 * batchId:生产批号
 */
@Entity
@Table(name = "goods")
@Getter
@Setter
public class Goods {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="goods_id",nullable=false,unique=true)
    private Long id;
    @Column(name="goods_name",nullable=false,unique=true)
    private String name;
    private String unit;
    private String space;
    private Long supplierId;
    private Double price;
    private String approveId;
    private String batchId;
}
