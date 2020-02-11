package com.cncs.dao;

import com.cncs.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AccountDao {

    /**
     * 查询所有
     */
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "money", column = "money"),
            @Result(property = "user", column = "uid", one = @One(select = "com.cncs.dao.UserDao.findUserById", fetchType = FetchType.EAGER))
    })
    List<Account> findAll();

    /**
     * 根据userId查询账户
     * @param userId
     * @return
     */
    @Select(value = {"select * from account where uid=#{userId}"})
    List<Account> findAccountById(Integer userId);
}
