package com.hfx.jdbctemplate;

import com.hfx.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class KdbcTemplateDemo3 {
    public static void main(String[] args) {
        //
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        JdbcTemplate jt = ac.getBean("jdbcTemplate",JdbcTemplate.class);

        //保存操作
        jt.update("insert into account(name,money) values(?,?)","eee",3333f);
        //更新操作
        jt.update("update account set name=?, money=? where id = ?","test",1212,7);
        // 删除操作
        jt.update("delete from account where id = ?",6);
        // 查询所有
        List<Account> accounts = jt.query("select * from account where money > ?",new AccountRowMapper(),1000f);
        List<Account> account2 = jt.query("select * from account where money > ?",new BeanPropertyRowMapper<Account>(Account.class),1000f);
        for(Account account:accounts){
            System.out.println(account);
        }

        //查询一个
        List<Account> account3 = jt.query("select * from account where id = ?",new BeanPropertyRowMapper<Account>(Account.class), 1);
        System.out.println(account3.isEmpty()?"没有内容":account3.get(0));
    }
}


class AccountRowMapper implements RowMapper<Account>{

    public Account mapRow(ResultSet re, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(re.getInt("id"));
        account.setName(re.getString("name"));
        account.setMoney(re.getFloat("money"));
        return account;
    }
}