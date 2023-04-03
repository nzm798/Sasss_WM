package com.ss.bean;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * 部门
 */
@Entity
@Table(name = "dept")
@Setter
@Getter
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dept_id")
    private Long id;
    @Column(name = "dept_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Employee.class)
    private Set<Employee> emp;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Managers.class)
    private Set<Managers> man;
}
