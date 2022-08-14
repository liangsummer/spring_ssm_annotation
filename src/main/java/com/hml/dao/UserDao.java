package com.hml.dao;

import com.hml.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 数据层接口
 * @ClassName: UserDao
 * @Author: 25507
 * @Date: 2022-08-13 19:07
 * @Version: 1.0
 */




public interface UserDao {

    /*
        添加用户
     */
    @Insert("insert into user(userName,password,realName,gender,birthday)values(#{userName},#{password},#{realName},#{gender},#{birthday})")
    public boolean save(User user);

    /*
        修改用户
     */
    @Update("update user set userName=#{userName},password=#{password},realName=#{realName},gender=#{gender},birthday=#{birthday} where uuid=#{uuid}")
    public boolean update(User user);

    /*
        删除用户
     */
    @Delete("delete from user where uuid = #{uuid}")
    public boolean delete(Integer uuid);

     /*
        查询单个用户
     */
    @Select("select * from user where uuid= #{uuid}")
    public User getById(Integer uuid);

    /*
       查询全部用户
    */
    @Select("select * from user")
    public List<User> getAll();
    /*
       根据用户名和密码查询单个用户
       @Param用于dao层，是mybatis中的注解
        使得mapper.xml中的参数与后台的参数对应上，也增强了可读性
        如果两者参数名一致得话，spring会自动进行封装，不一致的时候就需要手动去使其对应上。
        即：用注解来简化xml配置的时候,@Param注解的作用是给参数命名,参数命名后就能根据名字得到参数值,正确的将参数传入sql语句中 。
        在方法只接受一个参数的情况下，可以不使用@Param。
        在方法接受多个参数的情况下，建议一定要使用@Param注解给参数命名。
        如果参数是 JavaBean ， 则不能使用@Param。
        不使用@Param注解时，参数只能有一个，并且不是JavaBean
    */
    @Select("select * from user where userName=#{userName} and password=#{password}")
    public User getByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
}

