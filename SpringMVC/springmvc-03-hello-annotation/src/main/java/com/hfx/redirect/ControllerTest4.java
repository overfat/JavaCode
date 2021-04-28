package com.hfx.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ControllerTest4 {
    //通过设置ServletAPI, 不需要视图解析器，可以把视图解析器注释掉

    //通过HttpServletResponse进行输出
    @RequestMapping("/result/t1")
    public void test1(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.getWriter().println("Hello, HFX");
    }

    // 通过HttpServletResponse实现重定向
    @RequestMapping("/result/t2")
    public void test2(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.sendRedirect("/index.jsp");
    }

    //通过HttpServletRequest实现转发
    @RequestMapping("/result/t3")
    public void test3(HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setAttribute("msg","/result/t3");
        request.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(request,response);
    }

}
