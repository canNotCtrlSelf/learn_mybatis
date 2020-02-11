package com.cncs.test;

import com.cncs.dao.AccountDao;
import com.cncs.dao.UserDao;
import com.cncs.domain.Account;
import com.cncs.domain.AccountUser;
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
import java.util.ArrayList;
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
     * 测试查询所有账户信息,同时包含所属的用户信息
     */
    @Test
    public void testFindAll() throws Exception {
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println("---------Account----------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }


    /**
     * 根据id查询用户
     *
     * @throws IOException
     */
    @Test
    public void testfindOne() {
    }

    /**
     * 测试查询所有账户,同时包含用户名称和地址
     */
    @Test
    public void testFindAllAccount() throws Exception {
        List<AccountUser> accounts = accountDao.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

}
