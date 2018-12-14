package com.chinasofti.meeting.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.chinasofti.meeting.dao.MeetingRoomDao;
import com.chinasofti.meeting.dao.UserDao;
import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.MeetingRoom;
import com.chinasofti.meeting.vo.User;

public class MeetingRoomManage {
	Scanner input = new Scanner(System.in);
	UserDao userDao = new UserDao();
	MeetingRoomDao meetingRoomDao = new MeetingRoomDao();
	
	public void meetingRoomManage() {
		while(true) {
			System.out.println("1�����ӻ�����\t2��ɾ��������\t3���޸Ļ�����\t4�����һ�����\t5���鿴���л�����\t6��������һ��");
			System.out.print("��������Ҫ���еĲ���:");
			int chioce = input.nextInt();
			switch(chioce) {
				case 1:
					addMeeting();
					System.out.println();
					break;
				case 2:
					deleteMeeting();
					System.out.println();
					break;
				case 3:
					updateMeeting();
					System.out.println();
					break;
				case 4:
					selectMeeting();
					System.out.println();
					break;
				case 5:
					selectAll();
					System.out.println();
					break;
				case 6:
					System.out.println();
					return;
				default:
					System.out.println("û�и�ѡ�������ѡ��");
					break;
			}
		}
	}
	
	
//	����
	public void addMeeting() {
		int newId = Init.meetingRoomList.get(Init.meetingRoomList.size() - 1).getMid() + 1;
		System.out.print("����������ҵ�����:");
		String newType = input.next();
		System.out.print("����������ҵĵص�:");
		String newLocation = input.next();
		System.out.print("����������ҵ�����:");
		int newVolume = input.nextInt();
//		System.out.print("����������ҵĴ�С:");
		String newSize = "";
		if (newVolume > 0 && newVolume <= 10) {
			newSize = "С";
		} else if (newVolume > 10 && newVolume <= 50) {
			newSize = "��";
		}else if (newVolume > 50) {
			newSize = "��";
		}
		
		
		meetingRoomDao.add(new MeetingRoom(newId, newType, newLocation, newVolume, newSize));
		
	}
	
//	ɾ��
	public void deleteMeeting() {
		selectAll();
		System.out.print("��������Ҫɾ���Ļ����ҵ�id:");
		int deleteId = input.nextInt();
		
		if (!meetingRoomDao.delete(deleteId)) {
			System.out.println("��id������");
		}
	}
	
//	�޸�
	public void updateMeeting() {
		selectAll();
		System.out.print("��������Ҫ�޸ĵĻ����ҵ�id:");
		int updateId = input.nextInt();
		
		if (meetingRoomDao.selectById(updateId) != null) {
			System.out.print("�������޸ĺ�Ļ����ҵ�ַ:");
			String updateLocation = input.next();
			System.out.print("�������޸ĺ�Ļ���������:");
			String updateType = input.next();
			System.out.print("�������޸ĺ�Ļ���������:");
			int updateVolume = input.nextInt();
			String updateSize = "";
			if (updateVolume > 0 && updateVolume <= 10) {
				updateSize = "С";
			} else if (updateVolume > 10 && updateVolume <= 50) {
				updateSize = "��";
			}else if (updateVolume > 50) {
				updateSize = "��";
			}
			meetingRoomDao.update(updateId, new MeetingRoom(updateId, updateType, updateLocation, updateVolume, updateSize));
		} else {
			System.out.println("��id������");
		}
	}
	
//	�鿴ĳ��
	public void selectMeeting() {
		System.out.println("1��ͨ��������id��ѯ\t2��ͨ�����������Ͳ�ѯ\t3��ͨ�������Ҵ�С��ѯ(�� �� С)");
		System.out.print("�����������ѯ�ķ���:");
		int selectMethod = input.nextInt();
		switch(selectMethod) {
			case 1:
				System.out.print("�����������id:");
				int selectId = input.nextInt();
				
				if (meetingRoomDao.selectById(selectId) != null) {
					System.out.println(meetingRoomDao.selectById(selectId));
				} else {
					System.out.println("û�и�id�Ļ�����");
				}
				
				break;
			case 2:
				System.out.print("���������������:");
				String selectType = input.next();
				
				ArrayList<MeetingRoom> meetingRoom = meetingRoomDao.selectByType(selectType);
				if (meetingRoom.size() != 0) {
					for (int i = 0; i < meetingRoom.size(); i++) {
						System.out.println(meetingRoom.get(i));
					}
				} else {
					System.out.println("��û��" + selectType + "���͵Ļ�����");
				}
				
				break;
			case 3:
				System.out.print("����������Ҵ�С:");
				String selectSize = input.next();
				if (selectSize.equals("��") || selectSize.equals("��") || selectSize.equals("С")) {
					ArrayList<MeetingRoom> meetingRoom2 = meetingRoomDao.selectBySize(selectSize);
					if (meetingRoom2.size() != 0) {
						for (int i = 0; i < meetingRoom2.size(); i++) {
							System.out.println("������id:" + meetingRoom2.get(i).getMid() 
									+ "\t����������:" + meetingRoom2.get(i).getType()
									+ "\t�����ҵ�ַ:" + meetingRoom2.get(i).getLocation()
									+ "\t����������:" + meetingRoom2.get(i).getVolume()
									+ "\t�����Ҵ�С:" + meetingRoom2.get(i).getSize());
						}
					} else {
						System.out.println("��û��" + selectSize + "���͵Ļ�����");
					}
				} else {
					System.out.println("������Ĵ�С��������,����������");
				}

				break;
			default:
				break;
		}
	}
	
//	�鿴����
	public void selectAll() {
		ArrayList<MeetingRoom> meetings = new ArrayList<MeetingRoom>();
		meetings = meetingRoomDao.selectAll();
		if (meetings.size() != 0) {
			for (int i = 0; i < meetings.size(); i++) {
				System.out.println(meetings.get(i));
			}
		} else {
			System.out.println("��û�л����ң���ȥ��ӻ����Ұ�");
		}
	}
	
	
}
