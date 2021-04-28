package com.hfx.ui;

import com.hfx.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args){
        // 获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService = ac.getBean("accountService", IAccountService.class);
        accountService.saveAccount();

        ApplicationContext ac2 = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService2 = ac2.getBean("accountService2", IAccountService.class);
        accountService2.saveAccount();

        ApplicationContext ac3 = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService3 = ac3.getBean("accountService3", IAccountService.class);
        accountService3.saveAccount();
    }
}
