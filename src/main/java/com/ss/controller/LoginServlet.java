package com.ss.controller;

import com.ss.bean.Employee;
import com.ss.bean.Managers;
import com.ss.exception.ServiceException;
import com.ss.service.ManagerService;
import com.ss.util.ServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@WebServlet(urlPatterns = "/managerlogin")
public class LoginServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final long serialVersionUID = 1L;
    @Resource
    private ManagerService managerService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String user = req.getParameter("user");
        ServerInfo serverInfo = new ServerInfo();
        if ("manager".equals(user)) {
            Managers manager = null;
            try {
                manager = managerService.login(name, password);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            if (manager != null) {
                logger.info(name + "登录成功！");
                ServletContext servletContext = req.getSession().getServletContext();
                Long count = 0L;
                try {
                    count = managerService.findMessageCount();//获得未读文件
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
                servletContext.setAttribute("messageCount", count);
                servletContext.setAttribute("user", manager);
                servletContext.setAttribute("serverInfo", serverInfo);
                servletContext.setAttribute("httpSession", req.getSession().getId());
                req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
            } else {
                logger.info("登录失败，用户名或者密码错误！");
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("msg", "用户名或者密码错误");
                req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
            }
        } else if ("employee".equals(user)) {
            Employee employee = null;
            try {
                employee = managerService.employeelogin(name, password);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            if (employee != null) {
                logger.info(name + "登录成功！");
                Long count = 0L;
                ServletContext servletContext = req.getSession().getServletContext();
                try {
                    managerService.findMessageCount();
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
                servletContext.setAttribute("messageCount", count);
                servletContext.setAttribute("user", employee);
                req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
            } else {
                logger.info("登录失败，用户名或者密码错误！");
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("msg", "用户名或者密码错误");
                req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
            }
        } else {
            logger.info("登录失败，没有选择用户类型");
            HttpSession session = req.getSession();
            session.setAttribute("msg", "请选择用户类型");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
