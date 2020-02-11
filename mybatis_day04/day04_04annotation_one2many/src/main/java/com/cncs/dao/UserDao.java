package com.cncs.dao;

import com.cncs.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@CacheNamespace(blocking = true) //打开二级缓存
public interface UserDao {

    /**
     * 查找所有用户
     *
     * @return
     */
    @Select("select * from user")
    @Results(id = "userMap", value = {
            @Result(property = "userId", column = "id", id = true),
            @Result(property = "userName", column = "username"),
            @Result(property = "userAddress", column = "address"),
            @Result(property = "userSex", column = "sex"),
            @Result(property = "userBirthday", column = "birthday"),
            @Result(property = "accounts", column = "id",
                    many = @Many(select = "com.cncs.dao.AccountDao.findAccountById",
                            fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    /**
     * 查找所有用户和账户
     * @return
     */
    List<User> findAllUserAndAccount();


    /**
     * 通过id查询用户
     *
     * @return
     */
    @Select("select * from user where id =#{userId}")
    @ResultMap(value = "userMap")
    User findUserById(int userId);

    /**
     * 根据名称模糊查询
     *
     * @param name
     * @return
     */
    @Select("select * from user where username like #{name}")
//    @Select("select * from user where username like '%${value}%'")
    @ResultMap(value = "userMap")
    List<User> findUserByName(String name);
}
