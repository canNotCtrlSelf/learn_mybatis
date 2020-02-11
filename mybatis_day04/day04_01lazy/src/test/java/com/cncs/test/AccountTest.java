package com.cncs.test;

import com.cncs.dao.AccountDao;
import com.cncs.domain.Account;
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
public class AccountTest {

    private InputStream in;
    private SqlSession session;
    private AccountDao accountDao;


    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.创建SqlSession对象
        session = factory.openSession();
        //4.获取dao代理对象
        accountDao = session.getMapper(AccountDao.class);
    }

    @After
    public void destory() throws Exception {
        session.commit();
        session.close();
        in.close();
    }


    /**
     * 测试查询账户信息,同时查询所属用户信息,一对一
     */
    @Test
    public void testFindAll() throws Exception {
        List<Account> accounts = accountDao.findAll();
        /*for (Account account : accounts) {
            System.out.println("---------Account----------");
            System.out.println(account);
            System.out.println(account.getUser());
        }*/
    }

}
