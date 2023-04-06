package com.ss.service.imp;

import com.ss.bean.Customer;
import com.ss.bean.Goods;
import com.ss.bean.Suppliers;
import com.ss.dao.*;
import com.ss.exception.ServiceException;
import com.ss.service.QueryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询服务接口实现类
 */
@Service
public class QueryServiceImp implements QueryService {
    @Resource
    private GoodsRepository goodsRepository;
    @Resource
    private CustomerRepository customerRepository;
    @Resource
    private SuppliersRepository suppliersRepository;
    @Resource
    private SaleItemRepository saleItemRepository;
    @Resource
    private SaleOrderRepository saleOrderRepository;
    @Override
    public Goods findGoodsByName(String name) throws ServiceException {
        return goodsRepository.findGoodsByName(name);
    }

    @Override
    public Customer findCustomerByName(String name) throws ServiceException {
        return customerRepository.findCustomerByName(name);
    }

    @Override
    public Suppliers findSuppliersByName(String name) throws ServiceException {
        return suppliersRepository.findSuppliersByName(name);
    }

    @Override
    public Long findGoodsOrderByTimeAndName(String min, String max, String orderName) throws ServiceException {
        List<Long> list=saleOrderRepository.fingOrderIdByTime(min,max);
        Long count=0L;
        if (list.size()>0){
            List<BigInteger> countlist=saleItemRepository.findCountByName(orderName,list);
            for (BigInteger l:countlist){
                count+=(l.longValue());
            }
        }
        return count;
    }

    @Override
    public Map<String, Long> findAllOrderCount(String min, String max) throws ServiceException {
        List<Long> list=saleOrderRepository.fingOrderIdByTime(min,max);
        List<String> namelist=saleItemRepository.findAllName();
        Map<String,Long> map=new HashMap<>();
        if (list.size()>0){
            for (String name:namelist){
                Long counts=0L;
                List<BigInteger> countlist=saleItemRepository.findCountByName(name,list);
                for (BigInteger num:countlist){
                    counts+=(num.longValue());
                }
                map.put(name,counts);
            }
        }
        return map;
    }
}
