package com.hfx.dao.impl;

import com.hfx.dao.IAccountDao;

public class IAccountDaoImpl implements IAccountDao {
    @Override
    public void saveAccount() {
        System.out.println("保存账户");
    }
}
