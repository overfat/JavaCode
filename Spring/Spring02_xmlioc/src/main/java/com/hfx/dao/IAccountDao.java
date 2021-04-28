package com.hfx.dao;

import com.hfx.domain.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * 账户持久层的接口
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
