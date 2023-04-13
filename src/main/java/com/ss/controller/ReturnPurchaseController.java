package com.ss.controller;

import com.ss.bean.PurchaseItem;
import com.ss.bean.PurchaseOrder;
import com.ss.bean.ReturnItem;
import com.ss.bean.ReturnOrder;
import com.ss.exception.ServiceException;
import com.ss.service.ManagerService;
import com.ss.service.ReturnPurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/returnPurchase")
@SessionAttributes(value={"itemList","returnList","counts","tatPage","currPage"})
public class ReturnPurchaseController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private List<ReturnItem> itemList;
    private List<ReturnOrder> returnList;
    private Long counts;
    private Long currPage;
    private Long tatPage;
    @Resource
    private ReturnPurchaseService returnPurchaseService;
    @Resource
    private ManagerService managerService;

    /**
     * 采购退货
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/addOrder")
    public String addReturnOrder(HttpServletRequest request, Model model){
        logger.info("采购退货订单添加");
        Long orderId=Long.parseLong(request.getParameter("orderId"));
        try {
            List<PurchaseItem> purchaseItems=managerService.delPurchaseItemByPurchaseId(orderId);
            PurchaseOrder purchaseOrder=managerService.delOrderByPurchaseId(orderId);
            if (("已入库").equals(purchaseOrder.getFlag())){
                managerService.updateStockByReturnPurchaseItem(purchaseItems);
            }
            itemList=returnPurchaseService.addReturnOrder(purchaseOrder,purchaseItems);
            returnList=returnPurchaseService.findReturnOrderByPage(0);
            counts=returnPurchaseService.findReturnOrderCount();
            currPage=1L;
            tatPage= returnPurchaseService.getTotalPage();
            model.addAttribute("itemList", itemList);
            model.addAttribute("returnList", returnList);
            model.addAttribute("counts", counts);
            model.addAttribute("currPage", currPage);
            model.addAttribute("tatPage", tatPage);
            logger.info("采购退货订单添加完成");
            return "returnPurchase-list";
        }catch (ServiceException e){
            e.printStackTrace();
            return "returnPurchase-list";
        }
    }

    /**
     * 返回退货订单页面
     * @param model
     * @return
     */
    @RequestMapping("/turn")
    public String forWord(Model model){
        try {
            returnList = returnPurchaseService.findReturnOrderByPage(0);
            counts = returnPurchaseService.findReturnOrderCount();
            currPage = 1L;
            tatPage = returnPurchaseService.getTotalPage();
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute("returnList", returnList);
        model.addAttribute("counts", counts);
        model.addAttribute("currPage", currPage);
        model.addAttribute("tatPage", tatPage);
        return "returnPurchase-list";

    }

    /**
     * 分页功能中 首页 尾页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/multipage")
    public String getPurchase(HttpServletRequest request,Model model){
        Long pageNum =Long.parseLong(request.getParameter("curPage"));
        try {
            returnList = returnPurchaseService.findReturnOrderByPage((int)(pageNum-1));
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        currPage = pageNum;
        model.addAttribute("currPage", currPage);
        model.addAttribute("returnList", returnList);
        return "returnPurchase-list";

    }

    /**
     * 分页功能中上一页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/multipageDown")
    public String getPurchaseDown(HttpServletRequest request,Model model){
        Long pageNum =Long.parseLong(request.getParameter("curPage"));
        try {
            if(pageNum>1){
                returnList = returnPurchaseService.findReturnOrderByPage((int)(pageNum-2));
                currPage = pageNum-1;
            }else if(pageNum == 1){
                returnList = returnPurchaseService.findReturnOrderByPage((int)(pageNum-1));
                currPage = pageNum;
            }
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute("currPage", currPage);
        model.addAttribute("returnList", returnList);
        return "returnPurchase-list";

    }

    /**
     * 分页功能中下一页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/multipageUp")
    public String getPurchaseUp(HttpServletRequest request,Model model){
        Long pageNum =Long.parseLong(request.getParameter("curPage"));
        try {
            if(pageNum<=(tatPage-1)){
                returnList = returnPurchaseService.findReturnOrderByPage((int)(pageNum-0));
                currPage = pageNum+1;
            }else if(pageNum == tatPage){
                returnList = returnPurchaseService.findReturnOrderByPage((int)(pageNum-1));
                currPage = pageNum;
            }
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute("currPage", currPage);
        model.addAttribute("returnList", returnList);
        return "returnPurchase-list";

    }
}
