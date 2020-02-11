package com.cncs.mybatis.sqlsession;

/**
 * 自定义mybatis和数据库交互的核心类
 * 里面可以创建dao代理对象
 */
public interface SqlSession {

    /**
     * 根据参数创建一个代理对象
     * @param daoInterfaceClass dao接口的字节码
     * @param <T>
     * @return
     */
  <T>  T getMapper(Class<T> daoInterfaceClass);

    /**
     * 释放资源
     */
  void close();
}
