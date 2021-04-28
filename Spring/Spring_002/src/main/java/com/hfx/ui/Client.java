package com.hfx.ui;

import com.hfx.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService = (IAccountService) ac.getBean("accountService");
        accountService.saveAccount();

        //关闭容器,使用关闭容器的方法时，必须用ClassPathXmlApplicationContext方法
        ac.close();
    }
}
