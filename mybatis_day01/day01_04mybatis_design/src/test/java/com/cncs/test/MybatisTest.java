package com.cncs.test;

import com.cncs.dao.UserDao;
import com.cncs.domain.User;
import com.cncs.mybatis.io.Resources;
import com.cncs.mybatis.sqlsession.SqlSession;
import com.cncs.mybatis.sqlsession.SqlSessionFactory;
import com.cncs.mybatis.sqlsession.SqlSessionFactoryBuilder;


import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws Exception {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.通过factory对象创建SqlSession
        SqlSession sqlSession = factory.openSession();
        //4.通过session对象创建代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        //5.执行dao方法
        List<User> users = userDao.findAll();
        //遍历列表集合
        for (User user : users) {
            System.out.println(user);
        }
        //6.释放资源
        sqlSession.close();
        in.close();
    }
}
