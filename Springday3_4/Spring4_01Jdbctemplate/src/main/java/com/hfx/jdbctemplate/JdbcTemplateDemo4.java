package com.hfx.jdbctemplate;

import com.hfx.dao.IAccountDao;
import com.hfx.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcTemplateDemo4 {
    public static void main(String[] args) {
        // 获取容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //
        IAccountDao accountDao = ac.getBean("accountDao",IAccountDao.class);
        Account account = accountDao.findAccountById(1);
        System.out.println(account);
    }
}
