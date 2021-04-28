package com.hfx.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 数据展示到前端
@Controller
public class DataShow {

    // 第一种，通过ModelAndView，请看ControllerTest1中的实际操作
    //第二种，通过ModelMap
    @RequestMapping("/show2")
    public String hello(@RequestParam("username") String name, ModelMap model){
        //封装要显示到视图中的数据
        model.addAttribute("msg",name);
        System.out.println(name);
        return "hello";
    }

    //第三种，通过model
    public String hello2(@RequestParam("username") String name, Model model){
        model.addAttribute("msg",name);
        System.out.println(name);
        return "test";

    }
}
