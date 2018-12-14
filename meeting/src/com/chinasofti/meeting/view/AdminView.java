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
			System.out.println("1���û�����\t2���������\t3�������ҹ���\t4��Ա������\t5�����Ź���\t6��������һ��");
			System.out.print("��������Ҫ���еĲ���:");
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
//					Ա������
					staffManage.staffManage();
					break;
				case 5:
//					���Ź���
					departmentManage.departmentManage();
					break;
				case 6:
					System.out.println();
					login.login();
					break;
				default:
					System.out.println("û�и�ѡ�������ѡ��");
					break;
			}
		}
	}
}
