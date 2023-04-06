package com.ss.service;

import com.ss.bean.PurchaseItem;
import com.ss.bean.PurchaseOrder;
import com.ss.bean.ReturnItem;
import com.ss.bean.ReturnOrder;
import com.ss.exception.ServiceException;

import java.util.List;

/**
 * 采购退款退货服务接口
 */
public interface ReturnPurchaseService {
    public List<ReturnItem> addReturnOrder(PurchaseOrder purchaseOrder, List<PurchaseItem> purchaseItem) throws ServiceException;

    public List<ReturnOrder> findAllReturnOrder() throws ServiceException;

    public List<ReturnOrder> findReturnOrderByPage(int pageNum) throws ServiceException;

    public Long findReturnOrderCount() throws ServiceException;

    public Long getTotalPage() throws ServiceException;
}
