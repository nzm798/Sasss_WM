package com.ss.service;

import com.ss.bean.Customer;
import com.ss.bean.Employee;
import com.ss.bean.Suppliers;
import com.ss.exception.ServiceException;

import java.util.List;

/**
 * 供应商、消费者、雇佣者的服务接口
 */
public interface SuppliersAndCustomerService {
    public int saveSuppliers(Suppliers suppliers) throws ServiceException;

    public List<Suppliers> findSuppliersByPage(int i) throws ServiceException;

    public Long findSuppliersCount() throws ServiceException;

    public Long getTotalPage() throws ServiceException;

    public int delSuppliersById(Long id) throws ServiceException;

    public List<Customer> findCustomerByPage(int i) throws ServiceException;

    public Long findCustomerCount() throws ServiceException;

    public Long getCustomerTotalPage() throws ServiceException;

    public int saveCustomer(Customer customer) throws ServiceException;

    public int delCustomerById(Long id) throws ServiceException;

    public List<Employee> findEmplyeeByPage(int i) throws ServiceException;

    public Long findEmployeeCount() throws ServiceException;

    public Long getEmployeeTotalPage() throws ServiceException;

    public int delEmployeeById(Long id) throws ServiceException;
}
