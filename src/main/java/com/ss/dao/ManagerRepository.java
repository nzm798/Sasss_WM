package com.ss.dao;

import com.ss.bean.Managers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ManagerRepository extends JpaRepository<Managers,Long> {
    @Query(value = "from Managers where managers_name=?1",nativeQuery = true)
    public Managers findManagersByName(String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value ="update managers set managers_age=?1,managers_gender=?2,managers_phonenumber=?3 where managers_name=?4",nativeQuery=true)
    public int updateManagersByName(Long age, String gender, String phonenumber, String name);
}
