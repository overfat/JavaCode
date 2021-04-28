package com.hfx.ui;

import com.hfx.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client2 {
    public static void main(String[] args){
        // 获取Spring核心ioc容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 根据id获取bean对象
        IAccountService service = (IAccountService) ac.getBean("accountService");
        service.saveAccount();
    }
}
