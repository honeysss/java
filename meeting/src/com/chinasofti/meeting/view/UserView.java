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
//		�õ��û����User
		User user = userDao.selectById(userId);

		while(true) {
			System.out.println("1���޸��û���\t2���޸�����\t3���鿴��ɾ�������鿴�Լ���Ա����Ϣ\t4��������һ��");
			System.out.print("��������Ҫ���еĲ���:");
			int chioce2 = input.nextInt();
			switch(chioce2) {
				case 1:
					System.out.print("�������û���:");
					String userUpdateName = input.next();
//					�޸�
					user.setUname(userUpdateName);
					System.out.println("�û����޸ĳɹ�");
					
					break;
				case 2:
					System.out.print("�����������:");
					String oldPwd = input.next();
					if (oldPwd.equals(user.getPwd())) {
						System.out.print("����������:");
						String userUpdatePwd = input.next();
//						�޸�
						user.setPwd(userUpdatePwd);
						System.out.println("�����޸ĳɹ�");
					} else {
						System.out.println("��������");
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
					System.out.println("û�и�ѡ�������ѡ��");
					break;
				
			}
		}
	}
	
//	������Ϣ
	public void aboutMeeting() {
		System.out.print("��������������:");
		String staffName = input.next();
//		�ж���������Ƿ����
		if(staffDao.selectStaff(staffName) == null) {
			System.out.println("û�иĸ�Ա��");
			return;
		}
		while(true) {
			System.out.println("1���鿴����\t2��ɾ������\t3���鿴��Ϣ\t4��������һ��");
			System.out.print("��������Ҫ���еĲ���:");
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
					System.out.println("û�и�ѡ�����������");
			}
		}
		
	}
	
	
//	�鿴����
	public void selectMeeting(String staffName) {
		
		if(meetingDao.selectMeetingByName(staffName).size() != 0) {
			System.out.println("��Ļ�����Ϣ����:");
			for (int i = 0; i < meetingDao.selectMeetingByName(staffName).size(); i++) {
				System.out.println(meetingDao.selectMeetingByName(staffName).get(i));
			}
		}
		
	}
	
	
//	ɾ������
	public void deleteMeeting(String staffName) {
		if(meetingDao.selectMeetingByName(staffName).size() != 0) {
			System.out.println("��Ŀǰ�Ļ�����Ϣ����:");
			for (int i = 0; i < meetingDao.selectMeetingByName(staffName).size(); i++) {
				System.out.println(meetingDao.selectMeetingByName(staffName).get(i));
			}
			
			System.out.print("��������Ҫɾ���Ļ����id:");
			int deleteMeetingId = input.nextInt();
			
			meetingDao.deleteMeeting(deleteMeetingId, staffName);
			
		}
	}
	
	
//	�鿴��Ϣ
	public void selectInfo(String staffName) {
		if (staffDao.selectStaff(staffName) != null) {
			System.out.println("�����Ϣ����:");
			System.out.println(staffDao.selectStaff(staffName));
		}
		
		
	}
	
	
}
