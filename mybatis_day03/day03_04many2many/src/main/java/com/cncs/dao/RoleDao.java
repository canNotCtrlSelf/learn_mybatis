package com.cncs.dao;

import com.cncs.domain.Role;
import com.cncs.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface RoleDao {
    /**
     * 查询所有角色,同时包括角色所有的用户
     * @return
     */
    List<Role> findAll();
}
