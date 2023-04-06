package com.ss.bean;


import javax.persistence.*;
import lombok.*;

/**
 * @author XKS22
 * 顾客
 * id:
 * name:顾客名字
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
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long id;
    @Column(name = "customer_name")
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
