package com.jiangxiacollege.canteenwebsite.customer.controller;

import com.jiangxiacollege.canteenwebsite.customer.service.db.CommentService;
import com.jiangxiacollege.canteenwebsite.customer.service.db.NoticeService;
import com.jiangxiacollege.canteenwebsite.customer.service.db.ProductService;
import com.jiangxiacollege.canteenwebsite.customer.service.db.SellerUserService;
import com.jiangxiacollege.canteenwebsite.customer.table.Comment;
import com.jiangxiacollege.canteenwebsite.customer.table.Notice;
import com.jiangxiacollege.canteenwebsite.customer.table.Product;
import com.jiangxiacollege.canteenwebsite.customer.table.SellerUserInfo;
import com.jiangxiacollege.canteenwebsite.customer.vo.CommentVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import java.util.List;

@Controller
public class ProductController {

       @Resource
       private ProductService productService;

       @Resource
       private SellerUserService sellerUserService;

       @Resource
       private  NoticeService noticeService;

       @Resource
       private CommentService commentService;

    //首页展示
       @RequestMapping("/")
    public String productList(Model model){
        List<Product> list =   (List)productService.productList().getData();
        List<SellerUserInfo> lists=   (List)sellerUserService.sellerList().getData();
        List<Notice> ggl =   (List) noticeService.noticeList().getData();
        model.addAttribute("productList",list);
        model.addAttribute("sellerList",lists);
        model.addAttribute("noticeList",ggl);
        return "index";
    }

    @RequestMapping("/detailsp")
    public String detailsp(Model model,String productId){
        Product product = (Product) productService.productInfo(productId).getData();
        List<CommentVo> commentList=   (List)commentService.commentInfo(productId).getData();
        int commentCount = (int) commentService.commentCount(productId).getData();
        model.addAttribute("product",product);
        model.addAttribute("commentList",commentList);
        model.addAttribute("commentCount",commentCount);
        return "detailsp";
    }
}
