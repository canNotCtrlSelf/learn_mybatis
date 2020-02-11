package com.cncs.dao;

import com.cncs.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface UserDao {
    /**
     * 查询所有
     * @return
     */
    List<User> findAll();


    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findUserById(int id);

    /**
     * 更新user
     * @param user
     */
    void updateUser(User user);


}
