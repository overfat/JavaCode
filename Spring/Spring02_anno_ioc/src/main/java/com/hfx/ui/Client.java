package com.hfx.ui;

import com.hfx.service.IAccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟表现层用于调用业务层
 */
public class Client {
    public static void main(String[] args){
        // 获取核心容器对象
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 根据id获取Bean对象
        IAccountService as = (IAccountService) ac.getBean("accountService");

        as.saveAccount();
        ac.close();
    }
}
