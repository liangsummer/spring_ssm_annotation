package com.hml.service;

import com.github.pagehelper.PageInfo;
import com.hml.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 业务层接口
 * @ClassName: UserService
 * @Author: 25507
 * @Date: 2022-08-13 19:16
 * @Version: 1.0
 */

//开启事务层接口
@Transactional(
        readOnly = true //只读事务,业务层查询多
)

public interface UserService {
    /*
        添加用户
     */
    @Transactional(readOnly = false)
    public boolean save(User user);

    /*
        修改用户
     */
    @Transactional(readOnly = false)
    public boolean update(User user);

    /*
        删除用户
     */
    @Transactional(readOnly = false)
    public boolean delete(Integer uuid);
     /*
        查询单个用户
     */

    public User getById(Integer uuid);

    /*
       查询全部用户
    */
    public PageInfo<User> getAll(int page, int pageSize);

    /*
       根据用户名和密码进行登录
    */
    public User login(String userName, String password);
}
