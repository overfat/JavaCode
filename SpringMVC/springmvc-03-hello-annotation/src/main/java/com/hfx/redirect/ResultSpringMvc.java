package com.hfx.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResultSpringMvc {
    //通过SpringMvc来实现转发和重定向，不需要视图解析器，可以把其注释掉

    @RequestMapping("/rsm/t1")
    public String test1(){
        //转发
        return "index.jsp";
    }

    @RequestMapping("/rsm/t2")
    public String test2(){
        //转发二
        return "forward:/index.jsp";
    }

    @RequestMapping("/rsm/t3")
    public String test3(){
        //重定向
        return "redirect:/index.jsp";
    }
}
