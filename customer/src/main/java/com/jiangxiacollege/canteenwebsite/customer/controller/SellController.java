package com.jiangxiacollege.canteenwebsite.customer.controller;

import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.service.db.SellerUserService;
import com.jiangxiacollege.canteenwebsite.customer.table.SellerUserInfo;
import com.jiangxiacollege.canteenwebsite.customer.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sell")
public class SellController {

    @Resource
    SellerUserService sellerUserService;


    @RequestMapping("/toSellView")
    public String getListByKeyWord(){
        return "search_s" ;
    }


    @RequestMapping("/listByKeyWord")
    @ResponseBody
    public ResponseBase getListByKeyWord(@RequestBody String fkeyword){
        Map<String,Object> map = JsonUtils.jsonToMap(fkeyword);

        String keyword = String.valueOf(map.get("keyword"));
        try {
            keyword   = java.net.URLDecoder.decode(keyword,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ResponseBase responseBase = new ResponseBase();
        List<SellerUserInfo> list = (List<SellerUserInfo>) sellerUserService.sellerList(keyword).getData();
        if (list!=null){
            responseBase.setCode(0);
            responseBase.setData(list);
        }

        /*   model.addAttribute("sellList",list);*/
        return responseBase ;
    }

}
