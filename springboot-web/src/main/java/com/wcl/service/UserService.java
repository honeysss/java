package com.wcl.service;

import com.wcl.mapper.UserMapper;
import com.wcl.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    //封装DAO
    @Resource
    private UserMapper userMapper;

    //判断用户名是否已经存在
    public boolean ifExistUsernameService(String username) {
        User user = userMapper.ifExistUsername(username);
        boolean flag = true;
        if ("null".equals(user) || null == user) {
            flag = false;
        }
        return flag;
    }

    //判断是否存在该用户
    public boolean selectOneUserService(User user) {
        boolean flag = true;
        User userF = userMapper.selectOneUser(user);
        if ("null".equals(userF) || null == userF) {
            flag = false;
        }
        return flag;
    }

    //增加用户
    public void insertUserService(User user) {
        userMapper.insertUser(user.getUsername(),user.getPassword());
    }

}
