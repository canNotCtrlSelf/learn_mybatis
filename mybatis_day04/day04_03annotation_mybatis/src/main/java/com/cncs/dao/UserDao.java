package com.cncs.dao;

import com.cncs.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {

    /**
     * 查找所有用户
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 添加用户
     * @param user
     */
    @Insert("insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void insertUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("update user set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    void deleteUser(int id);

    /**
     * 通过id查询用户
     * @return
     */
    @Select("select * from user where id =#{id}")
    User findUserById(int id);

    /**
     * 根据名称模糊查询
     * @param name
     * @return
     */
//    @Select("select * from user where username like #{name}")
    @Select("select * from user where username like '%${value}%'")
    List<User> findUserByName(String name);

    /**
     * 查询总数
     * @return
     */
    @Select("select count(*) from user")
    int findTotal();


}
