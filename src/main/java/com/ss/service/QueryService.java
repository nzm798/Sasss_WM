package com.ss.service;

import com.ss.bean.Customer;
import com.ss.bean.Goods;
import com.ss.bean.Suppliers;
import com.ss.exception.ServiceException;

import java.util.Map;

/**
 * 查询服务接口
 */
public interface QueryService {
    public Goods findGoodsByName(String name)throws ServiceException;

    public Customer findCustomerByName(String name)throws ServiceException;

    public Suppliers findSuppliersByName(String name)throws ServiceException;

    public Long findGoodsOrderByTimeAndName(String min, String max, String orderName)throws ServiceException;

    public Map<String, Long> findAllOrderCount(String min, String max)throws ServiceException;
}
