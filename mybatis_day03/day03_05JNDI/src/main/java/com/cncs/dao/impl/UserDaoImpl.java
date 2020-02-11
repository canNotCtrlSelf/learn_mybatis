package com.cncs.dao.impl;

import com.cncs.dao.UserDao;
import com.cncs.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * UserDao接口的实现类
 */
public class UserDaoImpl implements UserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {
        //1.根据factory对象获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中方法实现查询所有
        List<User> users = session.selectList("com.cncs.dao.UserDao.findAll"); //参数是能获取配置文件的key
        //3.释放资源
        session.close();
        return users;
    }

    @Override
    public void saveUser(User user) {
        //1.根据factory对象获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中方法实现查询所有
        session.insert("com.cncs.dao.UserDao.saveUser", user); //参数是能获取配置文件的key
        session.commit();
        //3.释放资源
        session.close();
    }

    @Override
    public void updateUser(User user) {
        //1.根据factory对象获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中方法实现查询所有
        session.update("com.cncs.dao.UserDao.updateUser", user); //参数是能获取配置文件的key
        session.commit();
        //3.释放资源
        session.close();
    }

    @Override
    public void deleteUser(Integer id) {
        //1.根据factory对象获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中方法实现查询所有
        session.update("com.cncs.dao.UserDao.deleteUser", id); //参数是能获取配置文件的key
        session.commit();
        //3.释放资源
        session.close();
    }

    @Override
    public User findUserById(int id) {
        //1.根据factory对象获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中方法实现查询所有
        User user = session.selectOne("com.cncs.dao.UserDao.findUserById", id); //参数是能获取配置文件的key
        session.commit();
        //3.释放资源
        session.close();
        return user;
    }

    @Override
    public List<User> findUserByName(String name) {
        //1.根据factory对象获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中方法实现查询所有
        List<User> users = session.selectList("com.cncs.dao.UserDao.findUserByName", name); //参数是能获取配置文件的key
        session.commit();
        //3.释放资源
        session.close();
        return users;
    }

    @Override
    public int findAllCount() {
        //1.根据factory对象获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中方法实现查询所有
        Integer count = session.selectOne("com.cncs.dao.UserDao.findAllCount"); //参数是能获取配置文件的key
        session.commit();
        //3.释放资源
        session.close();
        return count;
    }
}
