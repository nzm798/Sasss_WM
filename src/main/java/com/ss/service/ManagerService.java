package com.ss.service;

import com.ss.bean.*;
import com.ss.exception.ServiceException;

import java.util.List;

/**
 * 管理者服务类接口
 */
public interface ManagerService {
    public int register(Managers manager)throws ServiceException;
    public Managers login(String name,String password)throws ServiceException;
    public int addPurchaseOrder(PurchaseOrder purchase, List<PurchaseItem> purchaseItem)throws ServiceException;
    public List<PurchaseOrder> findAllPurchaseOrder()throws ServiceException;
    public List<PurchaseOrder> findPurchaseOrderByPage(int currentPage)throws ServiceException;
    public Long findPurchaseOrderCount()throws ServiceException;
    public Long getTotalPage()throws ServiceException;
    public PurchaseOrder delOrderByPurchaseId(Long orderId)throws ServiceException;
    public List<PurchaseItem> delPurchaseItemByPurchaseId(Long orderId)throws ServiceException;
    public List<PurchaseItem> findPurchaseItemByPurchaseOrderId(Long orderId)throws ServiceException;
    public String findFlagByOrderId(Long orderId)throws ServiceException;
    public int updateFlagByOrderId(String string, Long orderId)throws ServiceException;
    public Employee employeelogin(String name, String password)throws ServiceException;
    public int employeeregister(Employee employee)throws ServiceException;
    public int  updateStockByReturnPurchaseItem(List<PurchaseItem> purchaseItem)throws ServiceException;
    public Employee updateEmplyeeByName(Employee employee)throws ServiceException;
    public Managers updateManagersByName(Managers managers)throws ServiceException;
    public Long findMessageCount()throws ServiceException;
    public List<Message> findAllMessage()throws ServiceException;
    public Message findMessageById(Long id)throws ServiceException;
    public int updateMessageById(Long id)throws ServiceException;
    public List<Message> findMessageByPage(int i)throws ServiceException;
    public Long getMessageTotalPage()throws ServiceException;
    public Long findMessageCounts()throws ServiceException;
}
