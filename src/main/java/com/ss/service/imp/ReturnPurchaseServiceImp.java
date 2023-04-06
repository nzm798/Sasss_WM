package com.ss.service.imp;

import com.ss.bean.PurchaseItem;
import com.ss.bean.PurchaseOrder;
import com.ss.bean.ReturnItem;
import com.ss.bean.ReturnOrder;
import com.ss.exception.ServiceException;
import com.ss.service.ReturnPurchaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnPurchaseServiceImp implements ReturnPurchaseService {
    @Override
    public List<ReturnItem> addReturnOrder(PurchaseOrder purchaseOrder, List<PurchaseItem> purchaseItem) throws ServiceException {
        return null;
    }

    @Override
    public List<ReturnOrder> findAllReturnOrder() throws ServiceException {
        return null;
    }

    @Override
    public List<ReturnOrder> findReturnOrderByPage(int pageNum) throws ServiceException {
        return null;
    }

    @Override
    public Long findReturnOrderCount() throws ServiceException {
        return null;
    }

    @Override
    public Long getTotalPage() throws ServiceException {
        return null;
    }
}
