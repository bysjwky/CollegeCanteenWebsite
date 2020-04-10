package com.jiangxiacollege.canteenwebsite.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Total2Controller")
public class Total2Controller {


    private String prefix = "admin/total2";// 页面的路径，注意admin前面不要有/


    @RequestMapping("view")
    public String view(Model model) {
//		String str="个人";
//		model用来回传数据给前端页面
//		setTitle(model, new TitleVo("列表", str+"管理", true,"欢迎进入"+str+"页面", true, false));
        return prefix + "/view";
    }
}