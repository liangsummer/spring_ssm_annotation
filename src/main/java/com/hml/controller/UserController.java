package com.hml.controller;

import com.github.pagehelper.PageInfo;
import com.hml.controller.result.Code;
import com.hml.controller.result.Result;
import com.hml.domain.User;
import com.hml.service.UserService;
import com.hml.system.exception.BuinessException;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @ClassName: UserController
 * @Author: 25507
 * @Date: 2022-08-13 19:18
 * @Version: 1.0
 */

@RestController //设置Restful风格和开启Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public Result save(User user){
        System.out.println("save...."+user);
        boolean flag = userService.save(user);
        return new Result(flag? Code.SAVE_OK:Code.SAVE_FALSE);
    }

    @PutMapping
    public Result update(User user){
        System.out.println("update...."+user);
        boolean flag = userService.update(user);
        return new Result(flag? Code.UPDATE_OK:Code.UPDATE_FALSE);
    }

    @DeleteMapping("/{uuid}")
    public Result delete(@PathVariable Integer uuid){
        System.out.println("delete...."+uuid);
        boolean flag = userService.delete(uuid);
        return new Result(flag? Code.DELETE_OK:Code.DELETE_FALSE);
    }


    @GetMapping("/{uuid}")
    public Result getById(@PathVariable Integer uuid){
        System.out.println("getById ...."+uuid);
        User user = userService.getById(uuid);

        //异常处理
        if (uuid == 10) {
            throw new BuinessException("查询出错！",Code.GET_OK);
        }

        return new Result(null != user?Code.GET_OK:Code.GET_FALSE,user);
    }

    @GetMapping("/{page}/{size}")
    public Result getAll(@PathVariable Integer page, @PathVariable Integer size){
        System.out.println("getAll ...."+page+" "+size);
        PageInfo<User> all = userService.getAll(page, size);
        return new Result(null != all?Code.GET_OK:Code.GET_FALSE,all);

    }


    @PostMapping("/login")
    public Result login(String username,String password){
        System.out.println("username: "+username +" "+password);
        User login = userService.login(username, password);
        return new Result(null != login?Code.GET_OK:Code.GET_FALSE,login);

    }


}
