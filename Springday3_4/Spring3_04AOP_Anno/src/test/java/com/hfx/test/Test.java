package com.hfx.test;

import com.hfx.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        // 获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 获取对象
        IAccountService as = (IAccountService) ac.getBean("accountService");
        as.saveAccount();
        as.updateAccount(1);
        as.deleteAccount();
    }
}
