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
import java.util.Date;
import java.util.List;

public class UserTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private  SqlSession session;
    private UserDao userDao;


    @Before
    public void init() throws IOException {
        in= Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session =  factory.openSession();
        userDao = session.getMapper(UserDao.class);
    }

    @After
    public void destory() throws IOException {
        //提交事务
        session.commit();
        //释放资源
        session.close();
        in.close();
    }

    /**
     * 无条件查询所有
     */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        //6.遍历结果
        for (User user : users) {
            System.out.println("-------user信息---------");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    /**
     * 测试查询一个用户
     */
    @Test
    public void testFindOne(){
        User user = userDao.findUserById(54);
        System.out.println(user);
    }

    /**
     * 测试查询一个用户
     */
    @Test
    public void testFindUserByName(){
        List<User> users = userDao.findUserByName("%王%");
//        List<User> users = userDao.findUserByName("王");
        for (User user : users) {
            System.out.println(user);
        }
    }
}
