package com.hfx.redirect;

//解决乱码问题

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Encoding {
    // 乱码问题是我们经常遇到的问题，SpringMvc给我们提供了一个过滤器，可以在Web.xml中配置
    @RequestMapping("/e/t")
    public String test(Model model, String name){
        model.addAttribute("msg",name);
        return "test";
    }
}
