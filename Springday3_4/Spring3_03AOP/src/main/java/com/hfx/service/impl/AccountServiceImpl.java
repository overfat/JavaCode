package com.hfx.service.impl;

import com.hfx.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

    @Override
    public void saveAccount() {
        System.out.println("执行了保存方法");
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("执行了更新方法 " + i);
    }

    @Override
    public int deleteAccount() {
        System.out.println("执行了删除账户的方法");
        return 1;
    }
}
