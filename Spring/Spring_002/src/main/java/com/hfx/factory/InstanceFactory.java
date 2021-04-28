package com.hfx.factory;

import com.hfx.service.IAccountService;
import com.hfx.service.impl.AccountServiceImpl;

public class InstanceFactory {
    public IAccountService getAccountService(){
        return new AccountServiceImpl();
    }
}
