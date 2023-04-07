package com.ss.service.imp;

import com.ss.bean.Customer;
import com.ss.bean.Employee;
import com.ss.bean.Suppliers;
import com.ss.dao.CustomerRepository;
import com.ss.dao.EmployeeRepository;
import com.ss.dao.SuppliersRepository;
import com.ss.exception.ServiceException;
import com.ss.service.SuppliersAndCustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 供应商、消费者、雇佣者的服务实现
 */
@Service
public class SuppliersAndCustomerServiceImp implements SuppliersAndCustomerService {
    @Resource
    private SuppliersRepository suppliersRepository;
    @Resource
    private CustomerRepository customerRepository;
    @Resource
    private EmployeeRepository employeeRepository;
    @Override
    public int saveSuppliers(Suppliers suppliers) throws ServiceException {
        suppliersRepository.save(suppliers);
        return 1;
    }

    @Override
    public List<Suppliers> findSuppliersByPage(int i) throws ServiceException {
        Pageable pageable= PageRequest.of(i,10);
        Page<Suppliers> page=suppliersRepository.findAll(pageable);
        List<Suppliers> list=new ArrayList<>();
        for (Suppliers suppliers:page){
            list.add(suppliers);
        }
        return list;
    }

    @Override
    public Long findSuppliersCount() throws ServiceException {
        return suppliersRepository.count();
    }

    @Override
    public Long getTotalPage() throws ServiceException {
        Long count = findSuppliersCount();
        Long totalPage;
        if(count%10==0){
            totalPage = count/10;
        }else{
            totalPage = count/10 +1;
        }
        return totalPage;
    }

    @Override
    public int delSuppliersById(Long id) throws ServiceException {
        suppliersRepository.deleteById(id);
        return 1;
    }

    @Override
    public List<Customer> findCustomerByPage(int i) throws ServiceException {
        Pageable pageable=PageRequest.of(i,10);
        Page<Customer> page=customerRepository.findAll(pageable);
        List<Customer> list=new ArrayList<>();
        for (Customer customer:page){
            list.add(customer);
        }
        return list;
    }

    @Override
    public Long findCustomerCount() throws ServiceException {
        return customerRepository.count();
    }

    @Override
    public Long getCustomerTotalPage() throws ServiceException {
        Long totalPage;
        Long count=findCustomerCount();
        if (count%10==0){
            totalPage=count/10;
        }else {
            totalPage=count/10+1;
        }
        return totalPage;
    }

    @Override
    public int saveCustomer(Customer customer) throws ServiceException {
        customerRepository.save(customer);
        return 1;
    }

    @Override
    public int delCustomerById(Long id) throws ServiceException {
        customerRepository.deleteById(id);
        return 0;
    }

    @Override
    public List<Employee> findEmplyeeByPage(int i) throws ServiceException {
        Pageable pageable=PageRequest.of(i,10);
        Page<Employee> page=employeeRepository.findAll(pageable);
        List<Employee> list=new ArrayList<>();
        for (Employee employee:page){
            list.add(employee);
        }
        return list;
    }

    @Override
    public Long findEmployeeCount() throws ServiceException {
        return employeeRepository.count();
    }

    @Override
    public Long getEmployeeTotalPage() throws ServiceException {
        Long totalPage;
        Long count=findEmployeeCount();
        if (count%10==0){
            totalPage=count/10;
        }else {
            totalPage=count/10+1;
        }
        return totalPage;
    }

    @Override
    public int delEmployeeById(Long id) throws ServiceException {
        employeeRepository.deleteById(id);
        return 1;
    }
}
