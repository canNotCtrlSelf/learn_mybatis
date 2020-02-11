package com.cncs.dao;

import com.cncs.domain.Account;

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
    List<Account> findAccountByUid(int id);

}
