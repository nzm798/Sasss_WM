package com.ss.service.imp;

import com.ss.bean.Goods;
import com.ss.bean.SaleItem;
import com.ss.bean.SaleOrder;
import com.ss.dao.*;
import com.ss.exception.ServiceException;
import com.ss.service.SaleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 销售服务实现类
 */
@Service
public class SaleServiceImp implements SaleService {
    @Resource
    private SaleOrderRepository saleOrderRepository;
    @Resource
    private SaleItemRepository saleItemRepository;
    @Resource
    private GoodsRepository goodsRepository;
    @Resource
    private StockRepository stockRepository;
    @Override
    public List<SaleOrder> findSaleOrderByPage(int pageNum) throws ServiceException {
        Pageable pageable= PageRequest.of(pageNum,10);
        Page<SaleOrder> page=saleOrderRepository.findAll(pageable);
        List<SaleOrder> saleOrders=new ArrayList<>();
        for (SaleOrder order:page){
            saleOrders.add(order);
        }
        return saleOrders;
    }

    @Override
    public Long findSaleOrderCount() throws ServiceException {
        return saleOrderRepository.count();
    }

    @Override
    public Long getTotalPage() throws ServiceException {
        Long totalPage;
        Long count=findSaleOrderCount();
        if (count%10==0){
            totalPage=count/10;
        }else {
            totalPage=count/10+1;
        }
        return totalPage;
    }

    @Override
    public int addSaleOrder(SaleOrder saleOrder, List<SaleItem> itemList) throws ServiceException {
        Double total=0D;
        for (SaleItem item:itemList){
            Double price=item.getPrice();
            Long count=item.getCount();
            Double addprice=count*price;
            total+=addprice;
        }
        saleOrder.setTotal(total);
        saleOrder.setCustomerId(itemList.get(0).getCustomerId());
        SaleOrder saleOrder1=saleOrderRepository.save(saleOrder);
        for (SaleItem item:itemList){
            item.setSaleOrderId(saleOrder1.getId());
            saleItemRepository.save(item);
        }
        return 1;
    }

    @Override
    public List<SaleItem> delSaleItemByOrderId(Long orderId) throws ServiceException {
        List<SaleItem> list=saleItemRepository.findSaleItemByOrderId(orderId);
        saleItemRepository.deleteSaleItemByOrderId(orderId);
        return list;
    }

    @Override
    public SaleOrder delOrderBySaleOrderId(Long orderId) throws ServiceException {
        SaleOrder saleOrder=saleOrderRepository.findById(orderId).orElse(null);
        saleOrderRepository.deleteById(orderId);
        return saleOrder;
    }

    @Override
    public String findFlagByOrderId(Long orderId) throws ServiceException {
        return saleOrderRepository.findFlagByOrderId(orderId);
    }

    @Override
    public List<SaleItem> findSaleOrderByOrderId(Long orderId) throws ServiceException {
        return saleItemRepository.findSaleItemByOrderId(orderId);
    }

    @Override
    public int updateFlagByOrderId(String string, Long orderId) throws ServiceException {
        saleOrderRepository.updateFlagByOrderId(string,orderId);
        return 1;
    }

    @Override
    public int updateStockBySaleReturnOrder(List<SaleItem> saleItem) throws ServiceException {
        for (SaleItem item:saleItem){
            Long goodsId=goodsRepository.findGoodsIdByGoodsName(item.getName());
            Long count=stockRepository.findCountByGoodsId(goodsId);
            stockRepository.updateStockCountByGoodsId(count+item.getCount(),goodsId);
        }
        return 0;
    }
}
