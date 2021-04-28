package com.hfx.service.impl;

import com.hfx.dao.IAccountDao;
import com.hfx.domain.Account;
import com.hfx.service.IAccountService;

import java.util.List;

public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao;
    public AccountServiceImpl() {
        super();
    }

    public void setAccountDao(IAccountDao accountDao){
        this.accountDao = accountDao;
    }
    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        accountDao.deleteAccountById(accountId);
    }
}
