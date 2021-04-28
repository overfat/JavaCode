package com.hfx.Test;

import com.hfx.service.IAccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {
    public static void main(String[] args) {
        // 1 获取容器
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2 获取对象
        IAccountService as = (IAccountService) ac.getBean("accountService");

        as.saveAccount();
//        as.updateAccount(1);
//        as.deleteAccount();
    }
}
