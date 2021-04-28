package com.hfx.redirect;

import com.hfx.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DataPrecess {
    // 提交的域名称和处理方法的参数名称一致：localhost:8080/han?name=hanfeixiang
    @RequestMapping("/han")
    public String hello(String name){
        System.out.println(name);
        return "hello";
    }
    // 提交的域名称和处理方法的参数名称不一致：localhost:8080/han2?username=hanfeixiang
    @RequestMapping("/han2")
    public String hello2(@RequestParam("username") String name){
        System.out.println(name);
        return "hello";
    }

    // 提交的是一个对象,要求提交的表单域和对象属性名称一致，参数使用对象即可
    @RequestMapping("/user")
    public String user(User user){
        System.out.println(user);
        return "hello";
    }


}
