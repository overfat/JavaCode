package com.hfx.factory;

import com.hfx.service.IAccountService;
import com.hfx.service.impl.AccountServiceImpl;

/**
 * 静态工厂类
 */

public class StaticFactory {
    public static IAccountService getAccountService(){
        return new AccountServiceImpl();
    }
}
