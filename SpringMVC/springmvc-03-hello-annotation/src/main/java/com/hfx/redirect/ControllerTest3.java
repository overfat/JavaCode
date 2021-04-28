package com.hfx.redirect;

// 本代码主要的的内容为结果跳转方式

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerTest3 implements Controller {
    //方式一：设置ModelAndView对象，根据view名称，和视图解析器跳到指定的页面

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","My name is Hanfeixiang");
        mv.setViewName("test");
        return mv;
    }

}
