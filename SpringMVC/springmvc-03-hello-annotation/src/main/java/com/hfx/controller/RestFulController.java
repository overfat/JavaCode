package com.hfx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestFulController {

    // SpringMvc可以使用PathVariable注解，让方法参数的值对应绑定到一个URI模板变量上
    @RequestMapping("/commit/{p1}/{p2}")
    public String index(@PathVariable int p1, @PathVariable int p2, Model model){

        int result = p1 + p2;
        model.addAttribute("msg","结果: " + result);
        return "test";
    }

    //使用method属性指定请求类型，用于约束请求的类型，可以收窄请求范围
    //这样设置，请求的时候会报错。因为所有的地址栏请求默认都是HTTP GET类型的
    @RequestMapping(value = "/hello", method = {RequestMethod.POST})
    public String index2(Model model){
        model.addAttribute("msg","Hello My Name is Hanfeixiang");
        return "test";
    }


}
