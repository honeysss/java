package com.chinasofti.meeting.dao;

import java.util.ArrayList;

import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.User;

public class UserDao {
	//	增
	public void add(User user) {
		Init.userList.add(user);
	}
	
//	删 同时判断id是否存在
	public Boolean delete(int id) {
		Boolean flag = false;
		for (int i = 0; i < Init.userList.size(); i++) {
			if(id == Init.userList.get(i).getUid()) {
				flag = true;
				String name = Init.userList.get(i).getUname();
				Init.userList.remove(Init.userList.get(i));
				System.out.println("删除用户" + name + "成功");
				break;
			}
		}
		return flag;
	}
	
//	改
	public void update(int id, User user) {
		for (int i = 0; i < Init.userList.size(); i++) {
			if(id == Init.userList.get(i).getUid()) {
				Init.userList.set(i, user);
				System.out.println("修改用户" + user.getUname() + "成功\n");
				break;
			}
		}
	}

	
//	查
//	全部查询
	public ArrayList<User> selectAll() {
		return Init.userList;
	}
	
//	通过用户id查询
	public User selectById(int id) {
		User user = null;
		for (int i = 0; i < Init.userList.size(); i++) {
			if(id == Init.userList.get(i).getUid()) {
				user = Init.userList.get(i);
			}
		}
		return user;
	}
	
//	通过用户名查询
	public User selectByName(String username) {
		User user = null;
		for (int i = 0; i < Init.userList.size(); i++) {
			if(username.equals(Init.userList.get(i).getUname())) {
				user = Init.userList.get(i);
			}
		}
		return user;
	}
	

//	检查用户的身份 true是管理员 false是普通用户
	public Boolean checkRole(String username) {
		Boolean exist = false;
		for (int i = 0; i < Init.userList.size(); i++) {
			if(username.equals(Init.userList.get(i).getUname())) {
				if (Init.userList.get(i).getUid() == 1) {
					exist = true;
				}
			}
		}
		return exist;
	}

	
//	判断增加或者注册的用户是否已存在(是否存在相同的用户名)
	public Boolean ifExistUser(String username) {
		Boolean exist = false;
		for (int i = 0; i < Init.userList.size(); i++) {
			if(username.equals(Init.userList.get(i).getUname())) {
				exist = true;
			}
		}
		return exist;
	}
	
	

}
