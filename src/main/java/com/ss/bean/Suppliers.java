package com.ss.bean;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author XKS22
 * 供应商
 * id:
 * name:供应商名字
 * address:地址
 * zip:邮编
 * telPhone:电话
 * linkMan:联系人
 * linkTel:联系人电话
 * bank:开户银行
 * bankAccount:银行账号
 * email:邮箱
 */
@Entity
@Table(name = "suppliers")
@Component
@Getter
@Setter
public class Suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "suppliers_id")
    private Long id;
    @Column(name = "supplier_name")
    private String name;
    private String address;
    private String zip;
    private String telPhone;
    private String linkMan;
    private String linkTel;
    private String bank;
    private Long bankAccount;
    private String email;
}
