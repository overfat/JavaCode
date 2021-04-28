package com.hfx.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/HelloController")
public class HelloController{

    @RequestMapping("/hello")
    public String sayHello(Model model){
        //向模型中添加属性msg与值
        model.addAttribute("msg","Hello,SpringMvc");
        return "hello";
    }
}
