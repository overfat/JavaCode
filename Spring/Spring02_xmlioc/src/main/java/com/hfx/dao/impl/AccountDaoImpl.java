package com.hfx.dao.impl;

import com.hfx.dao.IAccountDao;
import com.hfx.domain.Account;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 * 账户持久层实现类
 */
public class AccountDaoImpl implements IAccountDao {

    private QueryRunner runner;
    public void setRunner(QueryRunner runner){
        this.runner = runner;
    }

    @Override
    public List<Account> findAllAccount(){
        try{
            return runner.query("select * from account", new BeanListHandler<Account>(Account.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountById(Integer id) {

        try{
            return runner.query("select * from account where id = ?",new BeanHandler<Account>(Account.class),id);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try{
            runner.update("insert into account(name, money) values(?,?)", account.getName(),account.getMoney());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try{
            runner.update("update account set name=?, money=? where id=?",account.getName(),account.getMoney(),account.getId());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAccountById(Integer id) {
        try{
            runner.update("delete from account where id=?",id);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
