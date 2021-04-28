package com.hfx.dao.impl;

import com.hfx.dao.IAccountDao;
import com.hfx.domain.Account;
import com.hfx.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class AccountDaoImpl implements IAccountDao {
    private QueryRunner runner;
    private ConnectionUtils connectionUtils;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    @Override
    public List<Account> findAllAccount() {
        try{
            return runner.query(connectionUtils.getThreadConnection(),"select * from account",new BeanListHandler<>(Account.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try{
            return runner.query(connectionUtils.getThreadConnection(),"select * from account where id = ?",new BeanHandler<>(Account.class),accountId);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try{
            runner.update(connectionUtils.getThreadConnection(),"insert into account(name,money) values(?,?)",account.getName(),account.getMoney());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try{
            runner.update(connectionUtils.getThreadConnection(),"update account set name = ?, money=? where id = ?",account.getName(),account.getMoney(),account.getId());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAccount(Integer acccountId) {
        try {
            runner.update(connectionUtils.getThreadConnection(), "delete from account where id = ?", acccountId);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountByName(String accountName) {
        try{
            List<Account> accountList = runner.query(connectionUtils.getThreadConnection(),"select * from account where name = ?",new BeanListHandler<>(Account.class),accountName);
            if(accountList.isEmpty()){
                return null;
            }
            if(accountList.size() > 1){
                throw new RuntimeException("结果不唯一");
            }
            return accountList.get(0);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
