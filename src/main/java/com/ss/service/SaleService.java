package com.ss.service;

import com.ss.bean.SaleItem;
import com.ss.bean.SaleOrder;
import com.ss.exception.ServiceException;

import java.util.List;

/**
 * 售货服务接口
 */
public interface SaleService {
    public List<SaleOrder> findSaleOrderByPage(int pageNum) throws ServiceException;

    public Long findSaleOrderCount() throws ServiceException;

    public Long getTotalPage() throws ServiceException;

    public int addSaleOrder(SaleOrder saleOrder, List<SaleItem> itemList) throws ServiceException;

    public List<SaleItem> delSaleItemByOrderId(Long orderId) throws ServiceException;

    public SaleOrder delOrderBySaleOrderId(Long orderId) throws ServiceException;

    public String findFlagByOrderId(Long orderId) throws ServiceException;

    public List<SaleItem> findSaleOrderByOrderId(Long orderId) throws ServiceException;

    public int updateFlagByOrderId(String string, Long orderId) throws ServiceException;

    public int updateStockBySaleReturnOrder(List<SaleItem> saleItem) throws ServiceException;
}
