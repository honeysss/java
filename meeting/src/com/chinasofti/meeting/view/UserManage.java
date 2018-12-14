package com.chinasofti.meeting.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.chinasofti.meeting.dao.UserDao;
import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.User;

public class UserManage {
	Scanner input = new Scanner(System.in);
	UserDao userDao = new UserDao();
	Login login = new Login();

	
	
	public void userManage(String username) {
		int userId = userDao.selectByName(username).getUid();
		while(true) {
			System.out.println("1、增加用户\t2、删除用户\t3、修改用户\t4、查找用户\t5、查询所有用户\t6、返回上一层");
			System.out.print("请输入您要进行的操作:");
			int chioce = input.nextInt();
			switch(chioce) {
				case 1:
					addUser();
					break;
				case 2:
					deleteUser(userId);
					break;
				case 3:
					updateUser();
					
					break;
				case 4:
					selectUser();
					break;
				case 5:
					selectAllUser();
					break;
				case 6:
					System.out.println();
					return;
				default:
					System.out.println("没有该选项，请重新选择");
					break;
			}
		}
	}


	
	
//	添加用户
	public void addUser() {
		System.out.print("请输入用户的姓名:");
		String newName = input.next();
		
//		判断是否存在相同的用户
		if (userDao.ifExistUser(newName)) {
			System.out.println("该用户已存在,请输入一个别的用户名");
			addUser();
			return;
		} else {
			int newId = Init.userList.get(Init.userList.size() - 1).getUid() + 1;
			System.out.print("请输入用户的密码:");
			String newPwd = input.next();
			
			System.out.println("1、管理员\t0、普通用户");
			System.out.print("请输入用户的身份:");
			int newRole = input.nextInt();
			
			User newUser = new User(newId, newName, newPwd, newRole);
			System.out.println("添加用户" + newName + "成功\n");
			userDao.add(newUser);
		}
	}
	
//	修改用户
	public void updateUser() {

		selectAllUser();
		System.out.println("1、yes\t0、no");
		System.out.print("您想修改用户的身份吗:");
		String chioce = input.next();
		
		System.out.print("请输入你要修改的用户的id:");
		int updateId = input.nextInt();
//			判断用户是否存在
		if (userDao.selectById(updateId) != null) {
			

			System.out.print("请输入修改后的用户的姓名:");
			String updateNewName = input.next();

			
			System.out.print("请输入修改后的用户的密码:");
			String updateNewPwd = input.next();
			System.out.print("请再次输入修改后的用户的密码:");
			String updateNewPwdAgain = input.next();
			
			if (chioce.equals("yes")) {
				if (updateNewPwd.equals(updateNewPwdAgain)) {
					System.out.print("请输入修改后的用户的身份:");
					int updateNewRole = input.nextInt();
					
					User updateNewUser = new User(updateId, updateNewName, updateNewPwd, updateNewRole);
					
					userDao.update(updateId, updateNewUser);
				} else {
					System.out.println("密码不正确,请重新输入");
					updateUser();
				}
			} else {
				if (updateNewPwd.equals(updateNewPwdAgain)) {
					
					int updateNewRole = userDao.selectById(updateId).getRole();
					
					User updateNewUser = new User(updateId, updateNewName, updateNewPwd, updateNewRole);
					
					userDao.update(updateId, updateNewUser);
				} else {
					System.out.println("密码不正确,请重新输入");
					updateUser();
				}
			}
			
			
			
			
		} else {
			System.out.print("该用户不存在\n");
		}
	}
	
	
//	删除用户
	public void deleteUser(int userId) {
		selectAllUser();
		
		System.out.print("请输入你要删除的用户的id:");
		int deleteId = input.nextInt();
		

//			如果当前只有一个用户  或者删除的是自己 提示 你把自己删除了
//		通过用户姓名找到用户id 两个相等的话
		
		if (Init.userList.size() == 1 || userId == deleteId) {
			userDao.delete(deleteId);
			System.out.println("你把自己删除了，回到了首页");
			login.login();
		}
		
		
		if (!userDao.delete(deleteId)) {
			System.out.println("用户id不存在");
		}
		
	}
	
//	查看用户 单个
	public void selectUser() {
		System.out.println("1、通过用户名查询\t2、通过用户id查询");
		System.out.print("请输入您想查询的方法:");
		int selectMethod = input.nextInt();
		switch(selectMethod) {
			case 1:
				System.out.print("请输入用户名:");
				String selectUsername = input.next();
				if (userDao.selectByName(selectUsername) != null) {
					System.out.println(userDao.selectByName(selectUsername));
				} else {
					System.out.println("不存在该用户");
				}
				break;
			case 2:
				System.out.print("请输入用户id:");
				int selectId = input.nextInt();
				if (userDao.selectById(selectId) != null) {
					System.out.println(userDao.selectById(selectId));
				} else {
					System.out.println("不存在该用户");
				}
				break;
			default:
				break;
		}
	}
	
//	查询所有用户
	public void selectAllUser() {
		ArrayList<User> users = new ArrayList<User>();
		users = userDao.selectAll();
		if (users.size() != 0) {
			for (int i = 0; i < users.size(); i++) {
				System.out.println(users.get(i));
			}
		} else {
			System.out.println("还没有用户信息，快去添加用户吧");
		}
	}


}
