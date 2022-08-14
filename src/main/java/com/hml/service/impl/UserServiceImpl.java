package com.hml.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hml.dao.UserDao;
import com.hml.domain.User;
import com.hml.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 业务层接口实现类
 * @ClassName: UserService
 * @Author: 25507
 * @Date: 2022-08-13 19:15
 * @Version: 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    //注入数据层接口
    @Autowired
    private UserDao userDao;

    @Override
    public boolean save(User user) {
        return userDao.save(user);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(Integer uuid) {
        return userDao.delete(uuid);
    }

    @Override
    public User getById(Integer uuid) {
        return userDao.getById(uuid);
    }

    @Override
    public PageInfo<User> getAll(int page,int pageSize) {
        //分页组件
        PageHelper.startPage(page,pageSize);
        List<User> all = userDao.getAll();
        return new PageInfo<User>(all);
    }

    @Override
    public User login(String userName, String password) {
        return userDao.getByUserNameAndPassword(userName,password);
    }
}
