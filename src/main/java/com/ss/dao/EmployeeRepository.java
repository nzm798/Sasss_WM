package com.ss.dao;

import com.ss.bean.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query(value = "select * from Employee where employee_name=?1",nativeQuery = true)
    public Employee findEmployeeByName(String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update employee set employee_age=?1,employee_gender=?2,employee_phonenumber=?3 where employee_name=?4",nativeQuery = true)
    public int updateEmployeeByName(Long age, String gender, String phonenumber, String name);
}
