package com.cncs.dao.impl;

import com.cncs.dao.UserDao;
import com.cncs.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findAll(){
        //1.使用工厂创建session对象
        SqlSession session = factory.openSession();
        //2.使用session查询结果
        List<User> users = session.selectList("com.cncs.dao.UserDao.findAll");
        //3.释放资源
        session.close();
        //返回结果
        return users;
    }
}
