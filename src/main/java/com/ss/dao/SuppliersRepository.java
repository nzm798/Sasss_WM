package com.ss.dao;

import com.ss.bean.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SuppliersRepository extends JpaRepository<Suppliers, Long> {
    @Query(value = "select * from suppliers where supplier_name = ?1", nativeQuery = true)
    public Suppliers findSuppliersByName(String name);
}
