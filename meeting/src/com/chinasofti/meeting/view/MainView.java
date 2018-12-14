package com.chinasofti.meeting.view;

import com.chinasofti.meeting.dao.UserDao;
import com.chinasofti.meeting.util.Init;

public class MainView {
	static UserDao userDao = new UserDao();
	public static void main(String[] args) {
		System.out.println("\t\t------欢迎来到会议管理系统------");
		new Init();
		Login login = new Login();
		login.login();
	}

}
