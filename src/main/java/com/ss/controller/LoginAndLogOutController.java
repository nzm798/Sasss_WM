package com.ss.controller;

import com.ss.bean.Message;
import com.ss.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
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
}
