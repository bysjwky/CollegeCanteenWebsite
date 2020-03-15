package com.jiangxiacollege.canteenwebsite.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class viewController {



    @RequestMapping( "/article_list")
    public String articleList(){
        return "article_list";
    }

    @RequestMapping( "/article_read")
    public String articleRead(){
        return "article_read";
    }
   /* @RequestMapping( "/comment")
    public String comment(){
        return "comment";
    }*/
//    @RequestMapping( "/cart")
//    public String cart(){
//        return "cart";
//    }

    @RequestMapping( "/category")
    public String category(){
        return "category";
    }

/*    @RequestMapping( "/confirm_order")
    public String confirmOrder(){
        return "confirm_order";
    }*/
/*
    @RequestMapping( "/detailsp")
    public String detailsp(){
        return "detailsp";
    }*/

    /*@RequestMapping( "/list")
    public String list(){
        return "list";
    }*/

    @RequestMapping( "/reserve")
    public String reserve(){
        return "reserve";
    }

    @RequestMapping( "/respond")
    public String respond(){
        return "respond";
    }

    @RequestMapping( "/search_p")
    public String search_p(){
        return "search_p";
    }

    @RequestMapping( "/search_s")
    public String search_s(){
        return "search_s";
    }

    /*@RequestMapping( "/shop")
    public String shop(){
        return "shop";
    }
*/
   /* @RequestMapping( "/user_account")
    public String userAccount(){
        return "user_account";
    }*/

  /*  @RequestMapping( "/user_address")
    public String userAddress(){
        return "user_address";
    }
*/
    @RequestMapping( "/user_center")
    public String userCenter(){
        return "user_center";
    }

   /* @RequestMapping( "/user_orderlist")
    public String userOderlist(){
        return "user_orderlist";
    }*/

    @RequestMapping( "/user_coupon")
    public String userCoupon(){
        return "user_coupon";
    }

    @RequestMapping( "/user_favorites")
    public String userFavorites(){
        return "user_favorites";
    }

    @RequestMapping( "/user_message")
    public String userMessage(){
        return "user_message";
    }

//    @RequestMapping( "/user_order")
//    public String userOrder(){
//        return "user_order";
//    }

}
