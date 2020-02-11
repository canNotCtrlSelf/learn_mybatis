package com.cncs.test;

import com.cncs.dao.UserDao;
import com.cncs.domain.QueryVo;
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


    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.创建SqlSession对象
        session = factory.openSession();
        //4.获取dao代理对象
        userDao = session.getMapper(UserDao.class);
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
        //7.释放资源
    }


    /**
     * 保存用户
     *
     * @throws IOException
     */
    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setUserName("章三");
        user.setUserBirthday(new Date());
        user.setUserSex("男");
        user.setUserAddress("邯郸市邯山区毛遂路19号");
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
        user.setUserId(52);
        user.setUserName("王婆");
        user.setUserBirthday(new Date());
        user.setUserSex("女");
        user.setUserAddress("邯郸市丛台区毛遂路19号");
        userDao.updateUser(user);
    }

    /**
     * 删除用户
     *
     * @throws IOException
     */
    @Test
    public void testDelete() {
        userDao.deleteUser(51);
    }

    /**
     * 根据id查询用户
     *
     * @throws IOException
     */
    @Test
    public void testfindOne() {
        User user = userDao.findUserById(52);
        System.out.println(user);
    }

    /**
     * 根据名字模糊查询用户
     *
     * @throws IOException
     */
    @Test
    public void testFindByName() {
//        List<User> users = userDao.findUserByName("%王%");
        List<User> users = userDao.findUserByName("王");
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

    /**
     * 使用QueryVo条件查询用户
     *
     * @throws IOException
     */
    @Test
    public void testFindByVo() {
        QueryVo queryVo = new QueryVo();
        User user1 = new User();
        user1.setUserName("%王%");
        queryVo.setUser(user1);
        List<User> users = userDao.findUserByVo(queryVo);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
