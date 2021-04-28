package com.hfx.test;

import com.hfx.domain.Account;
import com.hfx.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    private IAccountService as;

    @Test
    public void testFindAll(){
        //执行方法
        List<Account> accounts = as.findAllAccount();
        for(Account account : accounts){
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne(){
        //执行方法
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave(){
        Account account = new Account();
        account.setName("hfx");
        account.setMoney(10000f);
        as.saveAccount(account);
    }

    @Test
    public void testUpdate(){
        Account account = as.findAccountById(4);
        account.setMoney(12345f);
        as.updateAccount(account);
    }

    @Test
    public void testDelete(){
        as.deleteAccount(4);
    }
}
