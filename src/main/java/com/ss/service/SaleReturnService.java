package com.ss.service;

import com.ss.bean.SaleItem;
import com.ss.bean.SaleOrder;
import com.ss.bean.SaleReturnItem;
import com.ss.bean.SaleReturnOrder;
import com.ss.exception.ServiceException;

import java.util.List;

/**
 * 销售退款退货服务接口
 */
public interface SaleReturnService {
    public List<SaleReturnItem> addSaleReturnOrder(SaleOrder saleOrder, List<SaleItem> saleItem)throws ServiceException;

    public List<SaleReturnOrder> findReturnOrderByPage(int i)throws ServiceException;

    public Long findReturnOrderCount()throws ServiceException;

    public Long getTotalPage()throws ServiceException;
}
