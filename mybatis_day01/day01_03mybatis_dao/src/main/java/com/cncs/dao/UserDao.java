package com.cncs.dao;

import com.cncs.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface UserDao {

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> findAll();
}
