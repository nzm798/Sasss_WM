package com.ss.bean;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author XKS22
 *  员工
 *id：
 *name:姓名
 *password:密码
 *age:年龄
 *phone number:手机号码
 *deptId:部门ID
 *gender:性别
 */
@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="employee_id")
    private Long id;
    @Column(name="employee_name")
    private String name;
    @Column(name="employee_password")
    private String password;
    @Column(name="employee_gender")
    private String gender;
    @Column(name="employee_age")
    private Long age;
    @Column(name="employee_phonenumber")
    private String phonenumber;
	/*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=Dept.class)
	@JoinColumn(name="dept_id")
	private Long deptId;*/
}
