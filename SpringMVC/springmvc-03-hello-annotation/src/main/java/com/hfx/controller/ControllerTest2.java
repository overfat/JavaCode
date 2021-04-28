package com.hfx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //controller注解类型用于声明Spring类的实例是一个控制器（在讲IOC时还提到了另外三个注解）
public class ControllerTest2 {

    //RequestMapping注解用于映射url到控制器类或一个特定的处理程序方法。既可以用在类上，也可以用在方法上
    //用于类上，表示类中所有响应请求的方法都是以该地址做为父路径
    @RequestMapping("/t2")
    public String index(Model model){
        //SpringMvc会实例化一个Model对象用于向视图中传值
        model.addAttribute("msg","ControllerTest2");

        return "test";
    }

}
