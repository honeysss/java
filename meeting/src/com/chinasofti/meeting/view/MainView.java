package com.chinasofti.meeting.view;

import com.chinasofti.meeting.dao.UserDao;
import com.chinasofti.meeting.util.Init;

public class MainView {
	static UserDao userDao = new UserDao();
	public static void main(String[] args) {
		System.out.println("\t\t------��ӭ�����������ϵͳ------");
		new Init();
		Login login = new Login();
		login.login();
	}

}
