package com.hfx.service.impl;

import com.hfx.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

    public AccountServiceImpl(){
        System.out.println("对象创建了");
    }

    @Override
    public void saveAccount() {
        System.out.println("saveAccount方法执行了");
    }
    public void init(){
        System.out.println("对象初始化了");
    }
    public void destroy(){
        System.out.println("对象销毁了");
    }

}
