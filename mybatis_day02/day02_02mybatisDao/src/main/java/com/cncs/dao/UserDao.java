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
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findUserById(int id);

    /**
     * 根据名字模糊查询用户
     * @param name
     * @return
     */
    List<User> findUserByName(String name);

    /**
     * 使用聚合函数查询总数
     * @return
     */
    int findAllCount();

}
