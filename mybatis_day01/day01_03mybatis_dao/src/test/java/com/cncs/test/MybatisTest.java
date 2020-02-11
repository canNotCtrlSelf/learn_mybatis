package com.cncs.test;

import com.cncs.dao.UserDao;
import com.cncs.dao.impl.UserDaoImpl;
import com.cncs.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws Exception{
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.通过factory对象创建SqlSession
        UserDao userDao = new UserDaoImpl(factory);
        //5.执行dao方法
        List<User> users = userDao.findAll();
        //遍历列表集合
        for (User user : users) {
            System.out.println(user);
        }
        in.close();
    }
}
