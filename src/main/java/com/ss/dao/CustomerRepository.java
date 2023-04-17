package com.ss.dao;

import com.ss.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query(value = "from customer where customer_name=?1",nativeQuery = true)
    public Customer findCustomerByName(String name);
}
