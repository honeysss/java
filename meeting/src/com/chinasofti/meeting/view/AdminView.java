package com.chinasofti.meeting.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.chinasofti.meeting.dao.UserDao;
import com.chinasofti.meeting.vo.User;

public class AdminView {
	Scanner input = new Scanner(System.in);
	UserDao userDao = new UserDao();
	UserManage userManage = new UserManage();
	MeetingManage meetingManage = new MeetingManage();
	MeetingRoomManage meetingRoomManage = new MeetingRoomManage();
	Login login = new Login();
	StaffManage staffManage = new StaffManage();
	DepartmentManage departmentManage = new DepartmentManage();
	
	public void adminView(String username) {
		while(true) {
			System.out.println("1、用户管理\t2、会议管理\t3、会议室管理\t4、员工管理\t5、部门管理\t6、返回上一层");
			System.out.print("请输入您要进行的操作:");
			int chioce2 = input.nextInt();
			switch(chioce2) {
				case 1:
					userManage.userManage(username);
					break;
				case 2:
					meetingManage.meetingManage();
					break;
				case 3:
					meetingRoomManage.meetingRoomManage();
					break;
				case 4:
//					员工管理
					staffManage.staffManage();
					break;
				case 5:
//					部门管理
					departmentManage.departmentManage();
					break;
				case 6:
					System.out.println();
					login.login();
					break;
				default:
					System.out.println("没有该选项，请重新选择");
					break;
			}
		}
	}
}
