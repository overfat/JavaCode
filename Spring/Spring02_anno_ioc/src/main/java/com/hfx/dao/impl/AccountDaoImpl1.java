package com.hfx.dao.impl;

import com.hfx.dao.IAccountDao;
import org.springframework.stereotype.Repository;

@Repository("accountDao1")
public class AccountDaoImpl1 implements IAccountDao {
    public void saveAccount(){
        System.out.println("保存了账户11111");
    }
}
