package com.hfx.controller;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

// 注意，这里我们先导入Controller接口
public class HelloController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = new ModelAndView();

        //封装对象
        mv.addObject("msg","HelloSpringMvc");
        // 封装要跳转的视图
        mv.setViewName("hello");
        return mv;
    }

}
