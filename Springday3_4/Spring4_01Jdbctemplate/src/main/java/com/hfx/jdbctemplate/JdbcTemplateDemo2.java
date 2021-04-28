package com.hfx.jdbctemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateDemo2 {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取对象
        JdbcTemplate jc = ac.getBean("jdbcTemplate",JdbcTemplate.class);
        // 执行操作
        jc.execute("insert into account(name,money) values('aaa',1234)");
    }
}
