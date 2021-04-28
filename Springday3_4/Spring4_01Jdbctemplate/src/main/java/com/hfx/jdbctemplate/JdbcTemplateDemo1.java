package com.hfx.jdbctemplate;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        // 基本用法
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/Spring");
        ds.setUsername("root");
        ds.setPassword("123456");

        // 创建对象
        JdbcTemplate jt = new JdbcTemplate();
        // 设置数据源
        jt.setDataSource(ds);
        // 执行操作
        jt.execute("insert into account(name,money) values('ccc', 1000)");
    }
}
