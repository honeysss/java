package com.chinasofti.meeting.util;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.chinasofti.meeting.vo.Department;
import com.chinasofti.meeting.vo.MeetingAllInfo;
import com.chinasofti.meeting.vo.Meeting;
import com.chinasofti.meeting.vo.MeetingRoom;
import com.chinasofti.meeting.vo.Staff;
import com.chinasofti.meeting.vo.User;

public class Init {
//	�û�
	public static ArrayList<User> userList;
	
//	������
	public static ArrayList<MeetingRoom> meetingRoomList;
	
//	����
	public static ArrayList<Meeting> meetingList;
	
//	Ա����Ϣ
	public static ArrayList<Staff> staffList;
	
//	������Ϣ
	public static ArrayList<Department> departmentList;
	
//	�����������Ϣ
	public static ArrayList<MeetingAllInfo> allInfoList;
	
	public Init() {
//		�û�
		userList = new ArrayList<User>();
		User user1 = new User(1, "admin", "admin", 1);
		User user2 = new User(2, "user", "user", 0);
		userList.add(user1);
		userList.add(user2);
		

//		������
		meetingRoomList = new ArrayList<MeetingRoom>();
		MeetingRoom room1 = new MeetingRoom(1, "��ý��ʵ����2", "����506", 50, "��");
		MeetingRoom room2 = new MeetingRoom(2, "��ý��", "ʵ��¥306", 20, "��");
		MeetingRoom room3 = new MeetingRoom(3, "��ý��ʵ����1", "����506", 10, "С");
		meetingRoomList.add(room1);
		meetingRoomList.add(room2);
		meetingRoomList.add(room3);
		

//		����
		meetingList = new ArrayList<Meeting>();
//		����id ������id �û�id
//		һ����������ж��Ա���μ� ����id �������� ������id ��ʼʱ�����ʱ�䶼��ͬ ֻ��Ա��id��ͬ
		Meeting meeting1 = new Meeting(1, "��ѧ����", 1, 1, "2018-10-10 10:10:10", "2018-10-10 10:12:10");
		Meeting meeting2 = new Meeting(2, "���", 1, 2, "2018-10-10 10:10:10", "2018-10-10 10:12:10");
		Meeting meeting3 = new Meeting(1, "��ѧ����", 1, 2, "2018-10-10 10:10:10", "2018-10-10 10:12:10");
		meetingList.add(meeting1);
		meetingList.add(meeting2);
		meetingList.add(meeting3);
		
		
//		Ա����Ϣ
		staffList = new ArrayList<Staff>();
//		Ա��id Ա����  ���� �绰 ����id
		Staff staff1 = new Staff(1, "С��", 20, "12312312332", 1);
		Staff staff2 = new Staff(2, "С��", 20, "12312312332", 1);
		staffList.add(staff1);
		staffList.add(staff2);
		
		
//		������Ϣ
		departmentList = new ArrayList<Department>();
//		Ա��id Ա����  ���� �绰 ����id
		Department department1 = new Department(1, "���Ų�");
		departmentList.add(department1);
		
		
//		�������������
		allInfoList = new ArrayList<MeetingAllInfo>();
		
	}
}
