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
import java.util.List;

public class SecondLevelCacheAnnoTest {

    /**
     * 测试查询一个用户
     */
    @Test
    public void testFindOne() throws IOException {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = factory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.findUserById(54);
        System.out.println(user);

        session.close();

        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
        User user1 = userDao.findUserById(54);
        System.out.println(user1);
        System.out.println(user == user1);
    }
}
