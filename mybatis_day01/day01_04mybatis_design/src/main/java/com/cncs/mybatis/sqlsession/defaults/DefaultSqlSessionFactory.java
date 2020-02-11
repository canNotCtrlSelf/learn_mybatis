package com.cncs.mybatis.sqlsession.defaults;

import com.cncs.mybatis.cfg.Configuration;
import com.cncs.mybatis.sqlsession.SqlSession;
import com.cncs.mybatis.sqlsession.SqlSessionFactory;

/**
 * SqlSessionFactory接口的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 用于创建一个新的数据库操作核心对象
     *
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
