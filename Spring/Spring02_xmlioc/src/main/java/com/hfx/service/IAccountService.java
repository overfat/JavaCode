package com.hfx.service;

import com.hfx.domain.Account;

import java.util.List;

/**
 * 业务层接口
 */
public interface IAccountService {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 查询一个
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);
    /**
     * 保存
     */
    void saveAccount(Account account);
    /**
     * 更新
     */
    void updateAccount(Account account);
    /**
     * 删除
     */
    void deleteAccount(Integer accountId);
}
