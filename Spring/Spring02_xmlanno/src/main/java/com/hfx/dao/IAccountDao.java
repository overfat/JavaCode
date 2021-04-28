package com.hfx.dao;

import com.hfx.domain.Account;

import java.util.List;

/**
 * 持久层接口
 */
public interface IAccountDao {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 根据账户查询一个
     * @param id
     * @return
     */
    Account findAccountById(Integer id);

    /**
     * 保存账户
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新账户
     * @param account
     */
    void updateAccount(Account account);
    /**
     * 删除账户
     */
    void deleteAccountById(Integer id);
}
