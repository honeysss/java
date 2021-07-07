package com.wcl.controller;


import com.wcl.pojo.User;
import com.wcl.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Controller
public class UserController {
    //封装Service
    @Resource(name="userService")
    private UserService us;
    public UserService getUs() {
        return us;
    }
    public void setUs(UserService us) {
        this.us = us;
    }

    //判断用户名是否存在
    @RequestMapping("ifExistUsername.action")
    public void ifExastUsername(HttpServletResponse response, String username) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        boolean flag = us.ifExistUsernameService(username);
        out.print(flag);
        out.close();
    }

    //判断是否存在该用户
    @RequestMapping("ifThePwdIsRight.action")
    public void ifThePwdIsRight(String username, String password, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        boolean flag = us.selectOneUserService(new User(0, username, password));
        out.print(flag);
        out.close();
    }

    //增加用户（注册成功）
    @RequestMapping("regist.action")
    public String regist(HttpServletRequest request) throws UnsupportedEncodingException {
        String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "utf-8");
        String password = request.getParameter("password");
        us.insertUserService(new User(0, username, password));
        return "forward:search.action?pageNum=1";
    }

}
