package com.jiangxiacollege.canteenwebsite.customer.controller;

import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.service.db.*;
import com.jiangxiacollege.canteenwebsite.customer.table.Address;
import com.jiangxiacollege.canteenwebsite.customer.table.Comment;
import com.jiangxiacollege.canteenwebsite.customer.table.Product;
import com.jiangxiacollege.canteenwebsite.customer.table.SellerUserInfo;
import com.jiangxiacollege.canteenwebsite.customer.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {
    @Resource
    private OrderService orderService;
    @Resource
    private ProductService productService;
    @Resource
    private CommentService commentService;
    @Resource
    private OrderDetailService orderDetailService;

    @RequestMapping("/plOrder")
    @ResponseBody
    public ResponseBase plOrder( @RequestBody Comment comment){
        return commentService.plOrder(comment);
    }



}
