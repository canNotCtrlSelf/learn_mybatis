package com.cncs.dao;

import com.cncs.domain.QueryVo;
import com.cncs.domain.User;

import javax.management.Query;
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
     * 根据名字模糊查询用户
     * @param name
     * @return
     */
    List<User> findUserByName(String name);


    /**
     * 通过QueryVo条件查询
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 通过条件查询
     * @param user
     * @return
     */
    List<User> findUserByCondition(User user);

    List<User> findUserInIds(QueryVo vo);

}
