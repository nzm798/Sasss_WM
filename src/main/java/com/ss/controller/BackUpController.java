package com.ss.controller;

import com.ss.util.DataBaseBackUp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据备份
 */
@Controller
@RequestMapping("/backups")
@SessionAttributes(value = {})
public class BackUpController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 用于备份数据
     *
     * @param model
     * @return
     */
    @RequestMapping("/backupes")
    public String dbBackUp(Model model) {
        logger.info("开始数据备份");
        DataBaseBackUp.backup();
        model.addAttribute("msg", "数据备份成功");
        logger.info("备份完成");
        return "backup";
    }

    /**
     * 数据恢复
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/restore")
    public String dbRestore(HttpServletRequest request, Model model) {
        logger.info("开始数据恢复");
        String fileName = request.getParameter("fileName");
        DataBaseBackUp.restore(fileName);
        model.addAttribute("msg", "数据恢复成功");
        logger.info("数据恢复完成");
        return "backup";
    }

    @RequestMapping("/gotoBackup")
    public String forwordBackUp() {
        return "backup";
    }
}
