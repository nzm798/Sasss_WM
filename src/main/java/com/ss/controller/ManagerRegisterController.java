package com.ss.controller;

import com.ss.bean.Employee;
import com.ss.bean.Managers;
import com.ss.exception.ServiceException;
import com.ss.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/manager")
public class ManagerRegisterController {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    private ManagerService managerRegisterService;
    private Managers manager;
    private Employee employee;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody //直接返回响应体
    public int data(HttpServletRequest request, HttpServletResponse response){
        String user=request.getParameter("user");
        if ("manager".equals(user)){
            logger.info("开始注册");
            manager=new Managers();
            manager.setName(request.getParameter("name"));
            manager.setPassword(request.getParameter("password"));
            manager.setGender(request.getParameter("gender"));
            manager.setAge(Long.parseLong(request.getParameter("age")));
            manager.setPhonenumber(request.getParameter("phonenumber"));
            try {
                logger.info("注册成功！");
                return managerRegisterService.register(manager);
            }catch (ServiceException e){
                e.printStackTrace();
                return 1;
            }
        } else if ("employee".equals(user)) {
            logger.info("开始注册");
            employee = new Employee();
            employee.setName(request.getParameter("name"));
            employee.setPassword(request.getParameter("password"));
            employee.setGender(request.getParameter("gender"));
            employee.setAge(Long.parseLong(request.getParameter("age")));
            employee.setPhonenumber(request.getParameter("phonenumber"));
            try {
                logger.info("注册成功");
                return managerRegisterService.employeeregister(employee);

            } catch (ServiceException e) {
                e.printStackTrace();
                return 1;
            }
        }
        return 1;
    }
    @RequestMapping(value = "/pur")
    public String turn(){
        return "article-list";
    }
}
