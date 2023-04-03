package com.ss.bean;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author XKS22
 * 管理员
 * id:管理员编号
 * name:管理员名字
 * password:登录密码
 * gender:性别
 * age:年龄
 * phone number:手机号码
 * deptId:部门id
 */
@Entity
@Table(name = "managers")
@Component
@Getter
@Setter
public class Managers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "managers_id")
    private Long id;
    @Column(name = "managers_name")
    private String name;
    @Column(name = "managers_password")
    private String password;
    @Column(name = "managers_gender")
    private String gender;
    @Column(name = "managers_age")
    private Long age;
    @Column(name = "managers_phonenumber")
    private String phonenumber;
	/*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=Dept.class)
	@JoinColumn(name="dept_name")
	private String deptName;*/
}
