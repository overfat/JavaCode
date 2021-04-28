package com.hfx.dao;

import com.hfx.domain.Account;

/**
 * 持久层接口
 */
public interface IAccountDao {

    Account findAccountById(Integer accountId);

    // 根据名称查询
    Account findAccountByName(String accountName);

    // 更新账户
    void updateAccount(Account account);
}
