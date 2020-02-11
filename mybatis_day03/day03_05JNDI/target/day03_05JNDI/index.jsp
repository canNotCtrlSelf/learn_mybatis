<%@ page import="com.cncs.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="com.cncs.dao.UserDao" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="com.cncs.dao.impl.UserDaoImpl" %>
<html>
<body>
<h2>Hello World!</h2>
<%
     InputStream in;
     SqlSession sqlSession;
     UserDao userDao;
     SqlSessionFactory factory;
    //1.读取配置文件
    in = Resources.getResourceAsStream("SqlMapConfig.xml");
    //2.创建SqlSessionFactory对象
    factory = new SqlSessionFactoryBuilder().build(in);
    //3.创建SqlSession对象
    sqlSession = factory.openSession();
    //4.获取dao代理对象
    userDao = new UserDaoImpl(factory);
    //5.执行查询所有的方法
    List<User> users = userDao.findAll();
    //6.遍历list
    for (User user : users) {
        System.out.println(user);
    }
    //事务提交
    sqlSession.commit();
    //释放资源
    sqlSession.close();
    in.close();
%>
</body>
</html>
