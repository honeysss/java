package com.chinasofti.meeting.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.chinasofti.meeting.dao.UserDao;
import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.User;

public class Login {
	UserDao userDao = new UserDao();
	Scanner input = new Scanner(System.in);
	public void login() {
		System.out.println("1、登陆\t2、注册\t3、退出");
		System.out.print("请选择你要进行的操作:");
		int chioce3 = 0;
		if (input.hasNextInt()) {
			chioce3 = input.nextInt();
			choose(chioce3);
		} else {
			System.out.println("数据类型有误，请重新输入");
		}
	}
	
//	选择
	public void choose(int chioce3) {
		while(true) {
			switch(chioce3) {
				case 1:
					register();
					break;
				case 2:
					sign();
					
					break;
				case 3:
					System.out.println("再见，欢迎下次光临");
					System.exit(0);
					return;
				default:
					System.out.println("没有该选项，请重新选择");
					break;
			}
		}
	}
	
//	登陆
	public void register() {
		while(true) {
			System.out.print("请输入你的用户名:");
			String username = input.next();

			System.out.print("请输入你的密码:");
			String pwd = input.next();
			
//			如果存在该用户并且密码正确
			if (userDao.selectByName(username) != null) {
				if (userDao.selectByName(username).getPwd().equals(pwd)) {
//					管理员
					if (userDao.checkRole(username)) {
						System.out.println("管理员" + username +"登陆成功!\n");
						AdminView adminView = new AdminView();
						adminView.adminView(username);
//						普通用户
					} else {
						System.out.println("普通用户" + username +"登陆成功!\n");
						UserView userView = new UserView();
						userView.userView(username);
					}
					
				} else {
					System.out.println("密码不正确");
				}
			} else {
				System.out.println("该用户不存在");
			}
		}
	}

//	注册
	public void sign() {
		System.out.println();
		while (true) {
			System.out.print("请输入用户名:");
			String signName = input.next();

//			判断是否存在相同的用户
			if (userDao.ifExistUser(signName)) {
				System.out.println("该用户已存在,请输入一个别的用户名");
			} else {
				System.out.print("请输入密码:");
				String signPwd = input.next();
				System.out.print("请再次输入您的密码:");
				String signPwdAgain = input.next();


				int signId = Init.userList.get(Init.userList.size() - 1).getUid() + 1;
				
				if (signPwd.equals(signPwdAgain)) {
					userDao.add(new User(signId, signName, signPwd, 0));
					System.out.println("添加用户" + signName + "成功\n");
					login();
				} else {
					System.out.println("两次密码不同, 请重新输入");
				}
			}
			
		}
		
	}
}
