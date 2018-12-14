package com.chinasofti.meeting.view;

import java.util.Scanner;

import com.chinasofti.meeting.dao.MeetingDao;
import com.chinasofti.meeting.dao.StaffDao;
import com.chinasofti.meeting.dao.UserDao;
import com.chinasofti.meeting.vo.User;

public class UserView {
	UserDao userDao = new UserDao();
	Scanner input = new Scanner(System.in);
	Login login = new Login();
	MeetingDao meetingDao = new MeetingDao();
	StaffDao staffDao = new StaffDao();
	
	public void userView(String username) {			
		int userId = userDao.selectByName(username).getUid();
//		得到用户这个User
		User user = userDao.selectById(userId);

		while(true) {
			System.out.println("1、修改用户名\t2、修改密码\t3、查看、删除会议或查看自己的员工信息\t4、返回上一层");
			System.out.print("请输入您要进行的操作:");
			int chioce2 = input.nextInt();
			switch(chioce2) {
				case 1:
					System.out.print("请输入用户名:");
					String userUpdateName = input.next();
//					修改
					user.setUname(userUpdateName);
					System.out.println("用户名修改成功");
					
					break;
				case 2:
					System.out.print("请输入旧密码:");
					String oldPwd = input.next();
					if (oldPwd.equals(user.getPwd())) {
						System.out.print("请输入密码:");
						String userUpdatePwd = input.next();
//						修改
						user.setPwd(userUpdatePwd);
						System.out.println("密码修改成功");
					} else {
						System.out.println("密码有误");
					}
					
					break;
				case 3:
					aboutMeeting();
					break;
				case 4:
					System.out.println();
					login.login();
					break;
				default:
					System.out.println("没有该选项，请重新选择");
					break;
				
			}
		}
	}
	
//	会议信息
	public void aboutMeeting() {
		System.out.print("请输入您的姓名:");
		String staffName = input.next();
//		判断这个名字是否存在
		if(staffDao.selectStaff(staffName) == null) {
			System.out.println("没有改该员工");
			return;
		}
		while(true) {
			System.out.println("1、查看会议\t2、删除会议\t3、查看信息\t4、返回上一层");
			System.out.print("请输入您要进行的操作:");
			int chioce = input.nextInt();
			switch(chioce) {
				case 1:
					selectMeeting(staffName);
					break;
				case 2:
					deleteMeeting(staffName);
					break;
				case 3:
					selectInfo(staffName);
					break;
				case 4:
					return;
				default:
					System.out.println("没有该选项，请重新输入");
			}
		}
		
	}
	
	
//	查看会议
	public void selectMeeting(String staffName) {
		
		if(meetingDao.selectMeetingByName(staffName).size() != 0) {
			System.out.println("你的会议信息如下:");
			for (int i = 0; i < meetingDao.selectMeetingByName(staffName).size(); i++) {
				System.out.println(meetingDao.selectMeetingByName(staffName).get(i));
			}
		}
		
	}
	
	
//	删除会议
	public void deleteMeeting(String staffName) {
		if(meetingDao.selectMeetingByName(staffName).size() != 0) {
			System.out.println("你目前的会议信息如下:");
			for (int i = 0; i < meetingDao.selectMeetingByName(staffName).size(); i++) {
				System.out.println(meetingDao.selectMeetingByName(staffName).get(i));
			}
			
			System.out.print("请输入您要删除的会议的id:");
			int deleteMeetingId = input.nextInt();
			
			meetingDao.deleteMeeting(deleteMeetingId, staffName);
			
		}
	}
	
	
//	查看信息
	public void selectInfo(String staffName) {
		if (staffDao.selectStaff(staffName) != null) {
			System.out.println("你的信息如下:");
			System.out.println(staffDao.selectStaff(staffName));
		}
		
		
	}
	
	
}
