package com.ss.service.imp;

import com.ss.bean.*;
import com.ss.dao.*;
import com.ss.exception.ServiceException;
import com.ss.service.ManagerService;
import com.ss.util.EncoderByMd5;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员服务类实现
 */
@Service
public class ManagerServiceImp implements ManagerService {
    @Resource
    private ManagerRepository managerRepository;
    @Resource
    private PurchaseRepository purchaseRepository;
    @Resource
    private PurchaseItemRepository purchaseItemRepository;
    @Resource
    private EmployeeRepository employeeRepository;
    @Resource
    private StockRepository stockRepository;
    @Resource
    private GoodsRepository goodsRepository;
    @Resource
    private MessageRepository messageRepository;

    @Override
    public int register(Managers manager) throws ServiceException {
        managerRepository.save(manager);
        return 0;
    }

    @Override
    public Managers login(String name, String password) throws ServiceException {
        Managers manager = managerRepository.findManagersByName(name);
        if (manager!=null){
            String md5= EncoderByMd5.Md5(password);
            if (md5.equals(manager.getPassword())){
                return manager;
            }else {
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public int addPurchaseOrder(PurchaseOrder purchase, List<PurchaseItem> purchaseItem) throws ServiceException {
        Double total=0D;
        for (PurchaseItem item:purchaseItem){
            Double price= item.getPrice();
            Long count=item.getCount();
            total+=(Double) price*count;
        }
        purchase.setTotal(total);
        purchase.setSuppliersId(purchaseItem.get(0).getSupplierId());
        PurchaseOrder order=purchaseRepository.save(purchase);
        for (PurchaseItem item:purchaseItem){
            item.setPurchaseOrderId(order.getId());
            purchaseItemRepository.save(item);
        }
        return 1;
    }

    @Override
    public List<PurchaseOrder> findAllPurchaseOrder() throws ServiceException {
        List<PurchaseOrder> list=purchaseRepository.findAll();
        return list;
    }

    @Override
    public List<PurchaseOrder> findPurchaseOrderByPage(int currentPage) throws ServiceException {
        Pageable pageable=PageRequest.of(currentPage,10);
        Page<PurchaseOrder> orderPage=purchaseRepository.findAll(pageable);
        List<PurchaseOrder> list=new ArrayList<>();
        for (PurchaseOrder order:orderPage){
            list.add(order);
        }
        return list;
    }

    @Override
    public Long findPurchaseOrderCount() throws ServiceException {
        return purchaseRepository.count();
    }

    @Override
    public Long getTotalPage() throws ServiceException {
        long count=findPurchaseOrderCount();
        Long totalPage;
        if (count%10==0){
            totalPage=count/10;
        }else {
            totalPage=count/10+1;
        }
        return totalPage;
    }

    @Override
    public PurchaseOrder delOrderByPurchaseId(Long orderId) throws ServiceException {
        PurchaseOrder purchaseOrder=purchaseRepository.findById(orderId).orElse(null);
        purchaseRepository.deleteById(orderId);
        return purchaseOrder;
    }

    @Override
    public List<PurchaseItem> delPurchaseItemByPurchaseId(Long orderId) throws ServiceException {
        List<PurchaseItem> list=purchaseItemRepository.findPurchaseItemByPurchaseId(orderId);
        for (PurchaseItem item:list){
            purchaseItemRepository.deleteById(item.getId());
        }
        return list;
    }

    @Override
    public List<PurchaseItem> findPurchaseItemByPurchaseOrderId(Long orderId) throws ServiceException {
        return purchaseItemRepository.findPurchaseItemByPurchaseId(orderId);
    }

    @Override
    public String findFlagByOrderId(Long orderId) throws ServiceException {
        return purchaseRepository.findFlagByOrderId(orderId);
    }

    @Override
    public int updateFlagByOrderId(String string, Long orderId) throws ServiceException {
        purchaseRepository.updateFlagByOrderId(string,orderId);
        return 1;
    }

    @Override
    public Employee employeelogin(String name, String password) throws ServiceException {
        Employee employee=employeeRepository.findEmployeeByName(name);
        if (employee!=null){
            String md5=EncoderByMd5.Md5(password);
            if (md5.equals(employee.getPassword())){
                return employee;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    @Override
    public int employeeregister(Employee employee) throws ServiceException {
        employeeRepository.save(employee);
        return 0;
    }

    @Override
    public int updateStockByReturnPurchaseItem(List<PurchaseItem> purchaseItem) throws ServiceException {
        for (PurchaseItem item:purchaseItem){
            Long goodsId= goodsRepository.findGoodsIdByGoodsName(item.getName());
            Long count=stockRepository.findCountByGoodsId(goodsId);
            stockRepository.updateStockCountByGoodsId(count-item.getCount(),goodsId);
        }
        return 0;
    }

    @Override
    public Employee updateEmplyeeByName(Employee employee) throws ServiceException {
        employeeRepository.updateEmployeeByName(employee.getAge(),employee.getGender(),employee.getPhonenumber(),employee.getName());
        return employeeRepository.findEmployeeByName(employee.getName());
    }

    @Override
    public Managers updateManagersByName(Managers managers) throws ServiceException {
        managerRepository.updateManagersByName(managers.getAge(),managers.getGender(),managers.getPhonenumber(),managers.getName());
        return managerRepository.findManagersByName(managers.getName());
    }

    @Override
    public Long findMessageCount() throws ServiceException {
        return messageRepository.findCounts();
    }

    @Override
    public List<Message> findAllMessage() throws ServiceException {
        return messageRepository.findAll();
    }

    @Override
    public Message findMessageById(Long id) throws ServiceException {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public int updateMessageById(Long id) throws ServiceException {
        messageRepository.updateMessageById(id);
        return 1;
    }

    @Override
    public List<Message> findMessageByPage(int i) throws ServiceException {
        List<Message> messages=new ArrayList<>();
        Pageable pageable=PageRequest.of(i,10);
        Page<Message> page=messageRepository.findAll(pageable);
        for (Message message:page){
            messages.add(message);
        }
        return messages;
    }

    @Override
    public Long getMessageTotalPage() throws ServiceException {
        Long count=findMessageCounts();
        Long totalPage;
        if (count%10==0){
            totalPage=count/10;
        }else {
            totalPage=count/10+1;
        }
        return totalPage;
    }

    @Override
    public Long findMessageCounts() throws ServiceException {
        return messageRepository.count();
    }
}
