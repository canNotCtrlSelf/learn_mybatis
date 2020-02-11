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
public class UserSecondLevelCacheTest {

    private InputStream in;

    private SqlSessionFactory factory;


    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory对象
        factory = new SqlSessionFactoryBuilder().build(in);

    }

    @After
    public void destory() throws Exception {
        in.close();
    }


    /**
     * 测试二级缓存
     */
    @Test
    public void testSecondLevelCache() throws Exception {
        //3.创建SqlSession对象
        SqlSession session1 = factory.openSession();
        //4.获取dao代理对象
        UserDao userDao1 = session1.getMapper(UserDao.class);
        User user1 = userDao1.findUserById(46);
        System.out.println(user1);

        session1.close();

        //3.创建SqlSession对象
        SqlSession session2 = factory.openSession();
        //4.获取dao代理对象
        UserDao userDao2 = session2.getMapper(UserDao.class);
        User user2 = userDao2.findUserById(46);
        System.out.println(user2);
        session2.close();

        System.out.println(user1 == user2);
    }
}
