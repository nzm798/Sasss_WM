package com.ss.controller;

import com.ss.bean.Customer;
import com.ss.bean.Goods;
import com.ss.bean.Suppliers;
import com.ss.exception.ServiceException;
import com.ss.service.QueryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 查询服务
 */
@Controller
@RequestMapping("/query")
@SessionAttributes(value = {"goods", "name", "count", "map"})
public class QueryController {
    @Resource
    private QueryService queryService;

    /**
     * 跳转到商品查询的首页
     *
     * @return
     */
    @RequestMapping("/goodes")
    public String gotoQueryGoods() {
        return "queryGoods";
    }

    /**
     * 跳转到客户查询的首页
     *
     * @return
     */
    @RequestMapping("/customers")
    public String gotoQueryCustomer() {

        return "queryCustomer";
    }

    /**
     * 跳转到供应商查询的首页
     *
     * @return
     */
    @RequestMapping("/supplierss")
    public String gotoQuerySuppliers() {

        return "querySuppliers";
    }

    /**
     * 跳转到商品订货单查询的首页
     *
     * @return
     */
    @RequestMapping("/goodsOrder")
    public String gotoQueryGoodsOrder() {

        return "querySaleOrder";
    }

    /**
     * 跳转到商品订单查询首页
     *
     * @return
     */
    @RequestMapping("/allgoodsOrder")
    public String gotoQueryAllGoodsOrder() {

        return "queryAllSaleOrder";
    }

    /**
     * 销售统计
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/queryAllOrder")
    public String queryAllOrders(HttpServletRequest request, Model model) {
        String min = request.getParameter("logmin");
        String max = request.getParameter("logmax");
        try {
            Map<String, Long> map = queryService.findAllOrderCount(min, max);
            model.addAttribute("map", map);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "queryAllSaleOrder";
    }

    /**
     * 查询某个商品在某个时段的销量
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/queryOrder")
    public String queryGoodsOrders(HttpServletRequest request, Model model) {
        String min = request.getParameter("logmin");
        String max = request.getParameter("logmax");
        String name = request.getParameter("orderName");
        try {
            Long count = queryService.findGoodsOrderByTimeAndName(min, max, name);
            model.addAttribute("name", name);
            model.addAttribute("count", count);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "querySaleOrder";
    }

    /**
     * 查询商品
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/queryGoods")
    public String querygoods(HttpServletRequest request, Model model) {
        String goodName = request.getParameter("goodName");
        try {
            Goods good = queryService.findGoodsByName(goodName);
            model.addAttribute("goods", good);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "queryGoods";
    }

    /**
     * 查询客户
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/queryEmployee")
    public String querycustomer(HttpServletRequest request, Model model) {
        String name = request.getParameter("customerName");
        try {
            Customer customer = queryService.findCustomerByName(name);
            model.addAttribute("customer", customer);
        } catch (ServiceException e) {

            e.printStackTrace();
        }
        return "queryCustomer";
    }

    /**
     * 查询供应商
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/querySuppliers")
    public String querysuppliers(HttpServletRequest request, Model model) {
        String name = request.getParameter("suppliersName");
        try {
            Suppliers suppliers = queryService.findSuppliersByName(name);
            model.addAttribute("suppliers", suppliers);
        } catch (ServiceException e) {

            e.printStackTrace();
        }
        return "querySuppliers";
    }
}
