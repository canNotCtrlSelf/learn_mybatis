package com.cncs.dao;

import com.cncs.domain.Account;
import com.cncs.domain.AccountUser;
import com.cncs.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface AccountDao {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAll();


    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    Account findAccountById(int id);


    /**
     * 查询所有账户,同时包含用户名称和地址
     * @return
     */
    List<AccountUser> findAllAccount();

}
