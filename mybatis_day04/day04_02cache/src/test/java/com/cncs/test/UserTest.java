package com.cncs.test;

import com.cncs.dao.UserDao;
import com.cncs.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 测试mybatis
 */
public class UserTest {

    private InputStream in;
    private SqlSession session;
    private UserDao userDao;
    private SqlSessionFactory factory;


    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory对象
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.创建SqlSession对象
        session = factory.openSession();
        //4.获取dao代理对象
        userDao = session.getMapper(UserDao.class);
    }

    @After
    public void destory() throws Exception {
        session.commit();
        session.close();
        in.close();
    }


    /**
     * 测试一级缓存
     */
    @Test
    public void testCache() throws Exception {
        User user = userDao.findUserById(46);
        System.out.println(user);

        session.close();
//        session.clearCache();
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);

        User user1 = userDao.findUserById(46);
        System.out.println(user1);
        System.out.println(user == user1);
    }

    /**
     * 测试更新时,一级缓存的状态
     */
    @Test
    public void testUpdate(){
        User user = userDao.findUserById(46);
        System.out.println(user);
        user.setUsername("姥姥王");
        user.setAddress("山西");
        userDao.updateUser(user);

        User user1 = userDao.findUserById(46);
        System.out.println(user1);
        System.out.println(user == user1);
    }
}
