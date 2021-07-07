package com.wcl.mapper;

import com.wcl.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
    //判断用户名是否已经存在
    public User ifExistUsername(String username);
    //判断是否存在该用户
    public User selectOneUser(User user);
    //增加用户（注册成功）
//    public void insertUser(User user);
    public void insertUser(String username, String password);
}
