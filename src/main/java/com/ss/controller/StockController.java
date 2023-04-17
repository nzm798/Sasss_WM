package com.ss.controller;

import com.ss.bean.Goods;
import com.ss.bean.Stock;
import com.ss.exception.ServiceException;
import com.ss.service.StockService;
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
@RequestMapping("/stock")
@SessionAttributes(value = {"outList", "coun", "totPage", "curPage", "sock"})
public class StockController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Long coun;
    private Long totPage;
    private Long curPage;
    private Stock stock;
    private Goods goods;
    private List<Stock> outList;
    @Resource
    private StockService stockService;

    /**
     * 添加商品信息和库存
     * @param request
     * @return
     */
    @RequestMapping("/add")
    public String addStock(HttpServletRequest request){
        logger.info("开始添加库存");
        String name = request.getParameter("name");
        String unit = request.getParameter("unit");
        String space = request.getParameter("space");
        Long supplierId = Long.parseLong(request.getParameter("supplierId"));
        String approveId = request.getParameter("approveId");
        String batchId = request.getParameter("batchId");
        Long count = Long.parseLong(request.getParameter("count"));
        Double price = Double.parseDouble(request.getParameter("price"));
        String area = request.getParameter("area");
        goods=new Goods();
        stock=new Stock();
        goods.setName(name);
        goods.setSpace(space);
        goods.setPrice(price);
        goods.setUnit(unit);
        goods.setBatchId(batchId);
        goods.setSupplierId(supplierId);
        goods.setApproveId(approveId);
        stock.setCounts(count);
        stock.setArea(area);
        try {
            stockService.stockAdd(stock,goods);
            logger.info("库存添加成功");
        }catch (ServiceException e){
            e.printStackTrace();
        }
        return "index";
    }

    /**
     * 更新库存信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/update")
    public String UpdateStock(HttpServletRequest request, Model model){
        logger.info("库存信息更新开始");
        Long goodsId = Long.parseLong(request.getParameter("goodsId"));
        Long count = Long.parseLong(request.getParameter("count"));
        try {
            stockService.updateStock(goodsId,count);
            outList = stockService.findStockByPage(0);
            model.addAttribute("outList", outList);
            logger.info("库存信息更新完成");
        }catch (ServiceException e){
            e.printStackTrace();
        }
        return "outStock";
    }

    /**
     * 跳转到商品出库界面
     * @param model
     * @return
     */
    @RequestMapping("/out")
    public String out(Model model) {
        try {

            outList = stockService.findStockByPage(0);
            coun = stockService.findStockCount();
            curPage = 1L;
            totPage = stockService.getTotalPage();
            model.addAttribute("coun", coun);
            model.addAttribute("curPage", curPage);
            model.addAttribute("totPage", totPage);
            model.addAttribute("outList", outList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "outStock";
    }

    /**
     * 跳转到库存查询界面
     * @return
     */
    @RequestMapping("/stockquery")
    public String forwordStockQuery(){


        return "queryStock";

    }

    /**
     * 库存查询
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/query")
    public String stockQuery(HttpServletRequest request,Model model){
        logger.info("库存查询开始");
        Long goodsId = Long.parseLong(request.getParameter("goodsId"));
        try {
            Stock stock = stockService.findStockByGoodsId(goodsId);
            model.addAttribute("sock", stock);
            logger.info("库存查询完成");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "queryStock";

    }

    /**
     * 跳转到转移库存界面
     * @param model
     * @return
     */
    @RequestMapping("/area")
    public String forwordStockArea(Model model){
        try {

            outList = stockService.findStockByPage(0);
            coun = stockService.findStockCount();
            curPage = 1L;
            totPage = stockService.getTotalPage();
            model.addAttribute("coun", coun);
            model.addAttribute("curPage", curPage);
            model.addAttribute("totPage", totPage);
            model.addAttribute("outList", outList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "updateArea";
    }

    /**
     * 转移商品存储位置
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/updateArea")
    public String upDateArea(HttpServletRequest request,Model model){
        logger.info("开始转移库存位置");
        Long goodsId=Long.parseLong(request.getParameter("goodsId"));
        String area=request.getParameter("area");
        try {
            stockService.updateStockAreaByGoodsId(area,goodsId);
            List<Stock> list=stockService.findAllStock();
            model.addAttribute("outList", list);
            logger.info("存放仓库信息更新完成");
        }catch (ServiceException e){
            e.printStackTrace();
        }
        return "updateArea";
    }
    /**
     * 所有库存
     * @param model
     * @return
     */
    @RequestMapping("/allStock")
    public String getAllStock(Model model){
        try {
            outList = stockService.findStockByPage(0);
            coun = stockService.findStockCount();
            curPage = 1L;
            totPage = stockService.getTotalPage();
            model.addAttribute("coun", coun);
            model.addAttribute("curPage", curPage);
            model.addAttribute("totPage", totPage);
            model.addAttribute("outList", outList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "stock";
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
            outList = stockService.findStockByPage((int)(pageNum-1));
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        curPage = pageNum;
        model.addAttribute("curPage", curPage);
        model.addAttribute("outList", outList);
        return "outStock";

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
                outList = stockService.findStockByPage((int)(pageNum-2));
                curPage = pageNum-1;
            }else if(pageNum == 1){
                outList = stockService.findStockByPage((int)(pageNum-1));
                curPage = pageNum;
            }
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute("curPage", curPage);
        model.addAttribute("outList", outList);
        return "outStock";

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
            if(pageNum<=(totPage-1)){
                outList = stockService.findStockByPage((int)(pageNum-0));
                curPage = pageNum+1;
            }else if(pageNum == totPage){
                outList = stockService.findStockByPage((int)(pageNum-1));
                curPage = pageNum;
            }
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute("curPage", curPage);
        model.addAttribute("outList", outList);
        return "outStock";

    }

    /**
     * 分页功能中 首页 尾页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/multipage1")
    public String getPurchase1(HttpServletRequest request,Model model){
        Long pageNum =Long.parseLong(request.getParameter("curPage"));
        try {
            outList = stockService.findStockByPage((int)(pageNum-1));
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        curPage = pageNum;
        model.addAttribute("curPage", curPage);
        model.addAttribute("outList", outList);
        return "stock";

    }

    /**
     * 分页功能中上一页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/multipageDown1")
    public String getPurchaseDown1(HttpServletRequest request,Model model){
        Long pageNum =Long.parseLong(request.getParameter("curPage"));
        try {
            if(pageNum>1){
                outList = stockService.findStockByPage((int)(pageNum-2));
                curPage = pageNum-1;
            }else if(pageNum == 1){
                outList = stockService.findStockByPage((int)(pageNum-1));
                curPage = pageNum;
            }
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute("curPage", curPage);
        model.addAttribute("outList", outList);
        return "stock";

    }

    /**
     * 分页功能中下一页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/multipageUp1")
    public String getPurchaseUp1(HttpServletRequest request,Model model){
        Long pageNum =Long.parseLong(request.getParameter("curPage"));
        try {
            if(pageNum<=(totPage-1)){
                outList = stockService.findStockByPage((int)(pageNum-0));
                curPage = pageNum+1;
            }else if(pageNum == totPage){
                outList = stockService.findStockByPage((int)(pageNum-1));
                curPage = pageNum;
            }
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute("curPage", curPage);
        model.addAttribute("outList", outList);
        return "stock";

    }
}
