package com.jiangxiacollege.canteenwebsite.admin.controller;


import com.jiangxiacollege.canteenwebsite.admin.model.Order;
import com.jiangxiacollege.canteenwebsite.admin.model.Product;
import com.jiangxiacollege.canteenwebsite.admin.model.SellerUserInfo;
import com.jiangxiacollege.canteenwebsite.admin.model.User;
import com.jiangxiacollege.canteenwebsite.admin.service.OrderService;
import com.jiangxiacollege.canteenwebsite.admin.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/OrderController")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "admin/order";// 页面的路径，注意admin前面不要有/


    @Autowired
    private OrderService orderService;// 注入业务层的service

    // 未加入@ResponseBody用来返回数据给页面
    @RequestMapping("view")
    public String view(Model model) {
//		String str="个人";
//		model用来回传数据给前端页面
//		setTitle(model, new TitleVo("列表", str+"管理", true,"欢迎进入"+str+"页面", true, false));
        return prefix + "/view";
    }

    // @ResponseBody，直接通过js异步返回数据给页面
    @RequestMapping("list")
    @ResponseBody
    public DataTableResult list(HttpServletRequest request, OrderVO orderVO) {
        // DataTableResult返回给datatables控件的数据格式

        //登录指定
        int roleId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("roleId")));
        String userId = ((User) request.getSession().getAttribute("user")).getId();
        String sellerId = ((User)request.getSession().getAttribute("user")).getSeller_id();
        if (roleId == 1) {
            orderVO.setUserId("");
        } else if (roleId == 3) {
            orderVO.setUserId(sellerId);
        }

        DataTableResult result = new DataTableResult();
        // 获取分页参数
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        // 获取排序字段
        String orderIdx = request.getParameter("order[0][column]");
        // 获取排序字段名
        String orderField = request.getParameter("columns[" + orderIdx + "][name]");
        // 获取排序方式，降序desc或者升序asc
        String orderDir = request.getParameter("order[0][dir]");
        // 调用分页查询方法
        result = orderService.selectOrderListPage(orderVO, start, length, orderField, orderDir);
//		result.setDraw(noticeVO.getDraw());
        return result;
    }

    // @ResponseBody，直接通过js异步返回数据给页面
    @RequestMapping("insert")
    @ResponseBody
    public Json insert(Order order) {
        Json j = new Json();
        if (orderService.insert(order) > 0) {
            j.setSuccess(true);
            j.setMsg("添加成功！");
        } else {
            j.setSuccess(false);
            j.setMsg("添加失败！");
        }
        return j;
    }

    // @ResponseBody，直接通过js异步返回数据给页面
    @RequestMapping("update")
    @ResponseBody
    public Json updateById(Order order) {
        Json j = new Json();
        if (orderService.updateById(order) > 0) {
            j.setSuccess(true);
            j.setMsg("修改成功！");
        } else {
            j.setSuccess(false);
            j.setMsg("修改失败！");
        }
        return j;
    }

    // @ResponseBody，直接通过js异步返回数据给页面
    @RequestMapping("select")
    @ResponseBody
    public Json selectById(Order order) {
        Json j = new Json();
        j.setSuccess(true);
//        j.setObj(orderService.selectById(order.getId()));
        j.setObj(orderService.selectById(String.valueOf(order.getId())));
        return j;
    }

    // @ResponseBody，直接通过js异步返回数据给页面
    @RequestMapping("delete")
    @ResponseBody
    public Json delete(HttpServletRequest request) {
        Json j = new Json();
        String ids = request.getParameter("ids");
        if (!StringUtils.isEmpty(ids)) {
            j.setSuccess(true);
            j.setObj("成功删除" + orderService.delete(ids) + "条记录");
        } else {
            j.setSuccess(false);
            j.setMsg("没有需要删除的记录！");
        }
        return j;
    }


    /**
     * 注销
     */
    // 未加入@ResponseBody用来返回数据给页面
    @RequestMapping("logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.removeAttribute("order");
        return "admin/login";
    }

    @RequestMapping("/orderDetail")
    @ResponseBody
    public Json orderDetail(HttpServletRequest request) {
        Json j = new Json();
        String orderId = request.getParameter("orderId");
        List<OrderDetailVo> list = (List<OrderDetailVo>) orderService.orderDetail(orderId).getData();
        if (!StringUtils.isEmpty(orderId)) {
            j.setSuccess(true);
            j.setObj(list);
        } else {
            j.setSuccess(false);
            j.setMsg("失败");
        }
        return j;

    }

    //发货
    @RequestMapping("send")
    @ResponseBody
    public Json sendById(Order order) {
        return orderService.sendById(order);
    }

    @RequestMapping("/countCustomerBySellerId")
    @ResponseBody
    public List<OrderCountVO> countCustomerBySellerId(HttpServletRequest request) {
        String sellerId = ((User) request.getSession().getAttribute("user")).getSeller_id();
        return orderService.countCustomerBySellerId(sellerId);
    }

    @RequestMapping("/countSchoolBySellerId")
    @ResponseBody
    public List<OrderCountVO> countSchoolBySellerId(HttpServletRequest request) {
        String sellerId = ((User) request.getSession().getAttribute("user")).getSeller_id();
        return orderService.countSchoolBySellerId(sellerId);
    }


}