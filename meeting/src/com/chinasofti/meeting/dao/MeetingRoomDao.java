package com.chinasofti.meeting.dao;

import java.util.ArrayList;

import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.MeetingRoom;

public class MeetingRoomDao {

//	��ѯ���л���
	public ArrayList<MeetingRoom> selectAll() {
		return Init.meetingRoomList;
	}
	
//	ͨ��id��ѯ������
	public MeetingRoom selectById(int id) {
		MeetingRoom meetingRoom = null;
		for (int i = 0; i < Init.meetingRoomList.size(); i++) {
			if (id == Init.meetingRoomList.get(i).getMid()) {
				meetingRoom = Init.meetingRoomList.get(i);
			}
		}
		return meetingRoom;
	}
	
//	ͨ�����Ͳ�ѯ������
	public ArrayList<MeetingRoom> selectByType(String type) {
		ArrayList<MeetingRoom> meetingRoom = new ArrayList<MeetingRoom>();
		for (int i = 0; i < Init.meetingRoomList.size(); i++) {
			if (type.equals(Init.meetingRoomList.get(i).getType())) {
				meetingRoom.add(Init.meetingRoomList.get(i));
			}
		}
		return meetingRoom;
	}
	
//	ͨ����С������С����ѯ������
	public ArrayList<MeetingRoom> selectBySize(String size) {
		ArrayList<MeetingRoom> meetingRoom = new ArrayList<MeetingRoom>();
		for (int i = 0; i < Init.meetingRoomList.size(); i++) {
			if (size.equals(Init.meetingRoomList.get(i).getSize())) {
				meetingRoom.add(Init.meetingRoomList.get(i));
			}
		}
		return meetingRoom;
	}
	
//	���ӻ�����
	public void add(MeetingRoom meetingRoom) {
		Init.meetingRoomList.add(meetingRoom);
		System.out.println("���ӻ�����" + meetingRoom.getType() + "�ɹ�");
	}
	
//	ɾ��������
	public Boolean delete(int id) {
		Boolean flag = false;
		for (int i = 0; i < Init.meetingRoomList.size(); i++) {
			if (id == Init.meetingRoomList.get(i).getMid()) {
				flag = true;
				String meetingRoom = Init.meetingRoomList.get(i).getLocation();
				Init.meetingRoomList.remove(i);
				System.out.println("ɾ��������" + meetingRoom + "�ɹ�");
			}
		}
		return flag;
	}
	
//	�޸Ļ�����
	public void update(int id, MeetingRoom meetingRoom) {
		for (int i = 0; i < Init.meetingRoomList.size(); i++) {
			if (id == Init.meetingRoomList.get(i).getMid()) {
				Init.meetingRoomList.set(i, meetingRoom);
				System.out.println("�޸Ļ�����" + meetingRoom.getMid() + "�ɹ�");
			}
		}
	}
	
}
