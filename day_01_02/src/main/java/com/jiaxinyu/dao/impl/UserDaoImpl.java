package com.jiaxinyu.dao.impl;

import com.jiaxinyu.dao.UserDao;
import com.jiaxinyu.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author jiaxinyu
 * @date 2020-03-30 12:08
 */
public class UserDaoImpl implements UserDao {
    private SqlSessionFactory f;

    //    private UserDao dao;
    public UserDaoImpl(SqlSessionFactory f) {
        this.f = f;
    }

    @Override
    public List<User> findAll() {
        SqlSession session = f.openSession();
        List<User> users = session.selectList("com.jiaxinyu.dao.UserDao.findAll");
        session.close();
        return users;
    }
}
