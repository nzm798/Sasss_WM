package com.ss.service.imp;

import com.ss.bean.PurchaseItem;
import com.ss.bean.PurchaseOrder;
import com.ss.bean.ReturnItem;
import com.ss.bean.ReturnOrder;
import com.ss.dao.ReturnItemRepository;
import com.ss.dao.ReturnOrderRepository;
import com.ss.exception.ServiceException;
import com.ss.service.ReturnPurchaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReturnPurchaseServiceImp implements ReturnPurchaseService {
    private List<ReturnItem> list=new ArrayList<ReturnItem>();
    @Resource
    private ReturnOrderRepository returnOrderRepository;
    @Resource
    private ReturnItemRepository returnItemRepository;
    @Override
    public List<ReturnItem> addReturnOrder(PurchaseOrder purchaseOrder, List<PurchaseItem> purchaseItem) throws ServiceException {
        ReturnOrder returnOrder=new ReturnOrder();
        returnOrder.setOutDate(new Date());
        returnOrder.setSuppliersId(purchaseOrder.getSuppliersId());
        returnOrder.setTotal(purchaseOrder.getTotal());
        returnOrder.setPay(purchaseOrder.getPay());
        ReturnOrder order= returnOrderRepository.save(returnOrder);
        for (PurchaseItem item:purchaseItem){
            ReturnItem returnItem=new ReturnItem();
            returnItem.setCount(item.getCount());
            returnItem.setPrice(item.getPrice());
            returnItem.setGoodsName(item.getName());
            returnItem.setId(item.getId());
            returnItem.setReturnOrderId(order.getId());
            list.add(returnItem);
        }
        returnItemRepository.saveAll(list);
        return list;
    }

    @Override
    public List<ReturnOrder> findAllReturnOrder() throws ServiceException {
        return (List<ReturnOrder>) returnOrderRepository.findAll();
    }

    @Override
    public List<ReturnOrder> findReturnOrderByPage(int pageNum) throws ServiceException {
        Pageable pageable=PageRequest.of(pageNum,10);
        List<ReturnOrder> list1=new ArrayList<>();
        Page<ReturnOrder> page=returnOrderRepository.findAll(pageable);
        for (ReturnOrder order:page){
            list1.add(order);
        }
        return list1;
    }

    @Override
    public Long findReturnOrderCount() throws ServiceException {
        return returnOrderRepository.count();
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
