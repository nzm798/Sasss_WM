package com.ss.controller;

import com.ss.bean.Employee;
import com.ss.bean.Managers;
import com.ss.bean.Message;
import com.ss.exception.ServiceException;
import com.ss.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/log")
@SessionAttributes(value = {"messageList", "message", "cunt", "crrentPage", "ttalPage"})
public class LoginAndLogOutController {
    @Resource
    private ManagerService managerService;
    private Long cunt;
    private Long crrentPage;
    private Long ttalPage;
    private List<Message> messageList = new ArrayList<Message>();

    @RequestMapping("/in")
    public String forwordIn(){
        return "login";
    }
    @RequestMapping("/checkMessage")
    public String seeMessage(HttpServletRequest request, Model model){
        Long id=Long.parseLong(request.getParameter("id"));
        try {
            Message message= managerService.findMessageById(id);
            managerService.updateMessageById(id);
            model.addAttribute("message",message);
        }catch (ServiceException e){
            e.printStackTrace();
        }
        return "seemessage";
    }
    @RequestMapping("/out")
    public String forwordOut(HttpServletRequest request){
        ServletContext app=request.getSession().getServletContext();
        app.removeAttribute("user");
        return "login";
    }
    @RequestMapping("/gotoInformation")
    public String forwordInformation(){
        return "information";
    }

    /**
     * 获取所有消息
     * @param model
     * @return
     */
    @RequestMapping("/getMessage")
    public String getAllMessage(Model model){
        try {
            messageList=managerService.findAllMessage();
            cunt=managerService.findMessageCounts();
            crrentPage=1L;
            ttalPage=managerService.getMessageTotalPage();
            model.addAttribute("crrentPage", crrentPage);
            model.addAttribute("ttalPage", ttalPage);
            model.addAttribute("messageList", messageList);
            model.addAttribute("cunt", cunt);
            model.addAttribute("messageList", messageList);
        }catch (ServiceException e){
            e.printStackTrace();
        }
        return "message";
    }

    /**
     * 修改雇佣人员的个人信息
     * @param request
     * @return
     */
    @RequestMapping("/updatInformation")
    public String updateInformation(HttpServletRequest request){
        String gender=request.getParameter("gender");
        Long age=Long.parseLong(request.getParameter("age"));
        String phonenumber=request.getParameter("phonenumber");
        ServletContext app=request.getSession().getServletContext();
        Object attribute=app.getAttribute("user");
        HttpSession session= request.getSession();
        if (attribute instanceof Employee){
            ((Employee) attribute).setAge(age);
            ((Employee) attribute).setGender(gender);
            ((Employee) attribute).setPhonenumber(phonenumber);
            try {
                Employee employee=managerService.updateEmplyeeByName((Employee) attribute);
                app.setAttribute("user",employee);
                session.setAttribute("mess", "信息成功修改");
            }catch (ServiceException e){
                e.printStackTrace();
            }
            return "information";
        }else if (attribute instanceof Managers){
            ((Managers) attribute).setAge(age);
            ((Managers) attribute).setGender(gender);
            ((Managers) attribute).setPhonenumber(phonenumber);
            try {
                Managers managers= managerService.updateManagersByName((Managers) attribute);
                app.setAttribute("user",managers);
                session.setAttribute("mess", "信息成功修改");
            }catch (ServiceException e){
                e.printStackTrace();
            }
            return "information";
        }else{
            session.setAttribute("mess", "信息成功失败，请重新输入");
            return "information";
        }
    }

    /**
     * 翻页，获取首页和尾页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/multipage")
    public String getPurchase(HttpServletRequest request,Model model){
        Long pageNum=Long.parseLong(request.getParameter("curPage"));
        try {
            messageList=managerService.findMessageByPage((int) (pageNum-1));
        }catch (ServiceException e){
            e.printStackTrace();
        }
        crrentPage=pageNum;
        model.addAttribute("currentPage", crrentPage);
        model.addAttribute("messageList", messageList);
        return "message";
    }

    /**
     * 分页功能，上一页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/multipageDown")
    public String getPurchaseDown(HttpServletRequest request,Model model){
        Long pageNum=Long.parseLong(request.getParameter("curPage"));
        try {
            if (pageNum>1){
                messageList=managerService.findMessageByPage((int) (pageNum-2));
                crrentPage=pageNum-1;
            }else if (pageNum==1){
                messageList=managerService.findMessageByPage((int) (pageNum-1));
                crrentPage=pageNum;
            }
        }catch (ServiceException e){
            e.printStackTrace();
        }
        model.addAttribute("currentPage", crrentPage);
        model.addAttribute("messageList", messageList);
        return "message";
    }

    /**
     * 分页功能，下一页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/multipageUp")
    public String getPurchaseUp(HttpServletRequest request,Model model){
        Long pageNum=Long.parseLong(request.getParameter("curPage"));
        try {
            if (pageNum==ttalPage){
                messageList=managerService.findMessageByPage((int) (pageNum-1));
                crrentPage=pageNum;
            } else if (pageNum<=(ttalPage-1)) {
                messageList=managerService.findMessageByPage((int) (pageNum-0));
                crrentPage=pageNum+1;
            }
        }catch (ServiceException e){
            e.printStackTrace();
        }
        model.addAttribute("currentPage", crrentPage);
        model.addAttribute("messageList", messageList);
        return "message";
    }
}
