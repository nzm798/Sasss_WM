package com.ss.service.imp;

import com.ss.bean.*;
import com.ss.dao.SaleReturnItemRepository;
import com.ss.dao.SaleReturnOrderRepository;
import com.ss.exception.ServiceException;
import com.ss.service.SaleReturnService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 销售退货服务实现类
 */
@Service
public class SaleReturnServiceImp implements SaleReturnService {
    private List<SaleReturnItem> list=new ArrayList<>();
    @Resource
    private SaleReturnOrderRepository saleReturnOrderRepository;
    @Resource
    private SaleReturnItemRepository saleReturnItemRepository;
    @Override
    public List<SaleReturnItem> addSaleReturnOrder(SaleOrder saleOrder, List<SaleItem> saleItem) throws ServiceException {
        SaleReturnOrder saleReturnOrder=new SaleReturnOrder();
        saleReturnOrder.setReturnDate(new Date());
        saleReturnOrder.setTotal(saleOrder.getTotal());
        saleReturnOrder.setPay(saleOrder.getPay());
        saleReturnOrder.setCustomerId(saleOrder.getCustomerId());
        SaleReturnOrder order= saleReturnOrderRepository.save(saleReturnOrder);
        for (SaleItem item:saleItem){
            SaleReturnItem saleReturnItem=new SaleReturnItem();
            saleReturnItem.setSaleReturnOrderId(order.getId());
            saleReturnItem.setPrice(item.getPrice());
            saleReturnItem.setCount(item.getCount());
            saleReturnItem.setGoodsName(item.getName());
            list.add(saleReturnItem);
        }
        return saleReturnItemRepository.saveAll(list);
    }

    @Override
    public List<SaleReturnOrder> findReturnOrderByPage(int i) throws ServiceException {
        Pageable pageable= PageRequest.of(i,10);
        Page<SaleReturnOrder> page=saleReturnOrderRepository.findAll(pageable);
        List<SaleReturnOrder> list1=new ArrayList<>();
        for (SaleReturnOrder order:page){
            list1.add(order);
        }
        return list1;
    }

    @Override
    public Long findReturnOrderCount() throws ServiceException {
        return saleReturnOrderRepository.count();
    }

    @Override
    public Long getTotalPage() throws ServiceException {
        Long totalPage;
        Long count=findReturnOrderCount();
        if (count%10==0){
            totalPage=count/10;
        }else {
            totalPage=count/10+1;
        }
        return totalPage;
    }
}
