package com.cncs.test;

import com.cncs.dao.RoleDao;
import com.cncs.domain.Role;
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

/**
 * 测试mybatis
 */
public class RoleTest {

    private InputStream in;
    private SqlSession session;
    private RoleDao roleDao;


    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.创建SqlSession对象
        session = factory.openSession();
        //4.获取dao代理对象
        roleDao = session.getMapper(RoleDao.class);
    }

    @After
    public void destory() throws Exception {
        session.commit();
        session.close();
        in.close();
    }


    /**
     * 测试查询所有账户信息,同时包含所属的用户信息
     */
    @Test
    public void testFindAll() throws Exception {
        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println("---------role信息----------");
            System.out.println(role);
        }
    }
}
