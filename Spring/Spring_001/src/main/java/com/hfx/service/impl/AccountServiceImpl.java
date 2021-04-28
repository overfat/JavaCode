package com.hfx.service.impl;

import com.hfx.dao.IAccountDao;
import com.hfx.service.IAccountService;

public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao;
    // 默认的构造函数
    public AccountServiceImpl(){
        System.out.println("对象创建了");
    }

    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
