package com.hfx.dao.impl;

import com.hfx.dao.IAccountDao;
import com.hfx.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class AccountDaoImpl2 implements IAccountDao {

//    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account findAccountById(Integer accountId) {
        List<Account> accountList = jdbcTemplate.query("select * from account where id = ?",new BeanPropertyRowMapper<Account>(Account.class),accountId);
        return accountList.isEmpty()?null:accountList.get(0);
    }

    @Override
    public Account findAccountByName(String accountName) {
        List<Account> accountList = jdbcTemplate.query("select * from account where name = ?",new BeanPropertyRowMapper<Account>(Account.class),accountName);
        if(accountList.isEmpty()){
            return null;
        }
        if(accountList.size() > 1){
            throw new RuntimeException("结果不唯一");
        }
        return accountList.get(0);

    }

    @Override
    public void updateAccount(Account account) {
        jdbcTemplate.update("update account set name = ?, money=? where id = ?",account.getName(),account.getMoney(),account.getId());
    }



    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
