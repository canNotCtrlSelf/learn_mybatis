package com.cncs.test;

import com.cncs.dao.UserDao;
import com.cncs.dao.impl.UserDaoImpl;
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

/**
 * 测试mybatis
 */
public class MybatisTest {

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
        userDao = new UserDaoImpl(factory);
    }

    @After
    public void destory() throws Exception {
        session.commit();
        session.close();
        in.close();
    }


    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() throws Exception {
        //5.执行查询所有的方法
        List<User> users = userDao.findAll();
        //6.遍历list
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 保存用户
     *
     * @throws IOException
     */
    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setUsername("老六");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("德阳市高新区步行路02号");
        System.out.println("保存前:" + user);
        userDao.saveUser(user);
        System.out.println("保存后:" + user);
    }

    /**
     * 更新用户
     *
     * @throws IOException
     */
    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setId(53);
        user.setUsername("赵老头");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("邯郸市丛台区毛遂路19号");
        userDao.updateUser(user);
    }

    /**
     * 删除用户
     *
     * @throws IOException
     */
    @Test
    public void testDelete() {
        userDao.deleteUser(52);
    }

    /**
     * 根据id查询用户
     *
     * @throws IOException
     */
    @Test
    public void testfindOne() {
        User user = userDao.findUserById(53);
        System.out.println(user);
    }

    /**
     * 根据名字模糊查询用户
     *
     * @throws IOException
     */
    @Test
    public void testFindByName() {
        List<User> users = userDao.findUserByName("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }


    /**
     * 使用聚合函数查询用户总数
     *
     * @throws IOException
     */
    @Test
    public void testFindAllCount() {
        int count = userDao.findAllCount();
        System.out.println(count);
    }

}
