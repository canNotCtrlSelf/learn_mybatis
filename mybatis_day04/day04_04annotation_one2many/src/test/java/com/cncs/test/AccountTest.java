package com.cncs.test;

import com.cncs.dao.AccountDao;
import com.cncs.dao.UserDao;
import com.cncs.domain.Account;
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

public class AccountTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private  SqlSession session;
    private AccountDao accountDao;


    @Before
    public void init() throws IOException {
        in= Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session =  factory.openSession();
        accountDao = session.getMapper(AccountDao.class);
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
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println("-------account信息---------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

    /**
     * 根据userId查询账户
     */
    @Test
    public void testFindAccountById(){
        List<Account> accounts = accountDao.findAccountById(46);
        for (Account account : accounts) {
            System.out.println("-------account信息---------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }
}
