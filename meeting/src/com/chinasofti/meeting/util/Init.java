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
//	用户
	public static ArrayList<User> userList;
	
//	会议室
	public static ArrayList<MeetingRoom> meetingRoomList;
	
//	会议
	public static ArrayList<Meeting> meetingList;
	
//	员工信息
	public static ArrayList<Staff> staffList;
	
//	部门信息
	public static ArrayList<Department> departmentList;
	
//	会议的所有信息
	public static ArrayList<MeetingAllInfo> allInfoList;
	
	public Init() {
//		用户
		userList = new ArrayList<User>();
		User user1 = new User(1, "admin", "admin", 1);
		User user2 = new User(2, "user", "user", 0);
		userList.add(user1);
		userList.add(user2);
		

//		会议室
		meetingRoomList = new ArrayList<MeetingRoom>();
		MeetingRoom room1 = new MeetingRoom(1, "多媒体实验室2", "二教506", 50, "中");
		MeetingRoom room2 = new MeetingRoom(2, "多媒体", "实验楼306", 20, "中");
		MeetingRoom room3 = new MeetingRoom(3, "多媒体实验室1", "三教506", 10, "小");
		meetingRoomList.add(room1);
		meetingRoomList.add(room2);
		meetingRoomList.add(room3);
		

//		会议
		meetingList = new ArrayList<Meeting>();
//		会议id 会议室id 用户id
//		一个会议可能有多个员工参加 会议id 会议主题 会议室id 开始时间结束时间都相同 只有员工id不同
		Meeting meeting1 = new Meeting(1, "开学典礼", 1, 1, "2018-10-10 10:10:10", "2018-10-10 10:12:10");
		Meeting meeting2 = new Meeting(2, "年会", 1, 2, "2018-10-10 10:10:10", "2018-10-10 10:12:10");
		Meeting meeting3 = new Meeting(1, "开学典礼", 1, 2, "2018-10-10 10:10:10", "2018-10-10 10:12:10");
		meetingList.add(meeting1);
		meetingList.add(meeting2);
		meetingList.add(meeting3);
		
		
//		员工信息
		staffList = new ArrayList<Staff>();
//		员工id 员工名  年龄 电话 部门id
		Staff staff1 = new Staff(1, "小明", 20, "12312312332", 1);
		Staff staff2 = new Staff(2, "小红", 20, "12312312332", 1);
		staffList.add(staff1);
		staffList.add(staff2);
		
		
//		部门信息
		departmentList = new ArrayList<Department>();
//		员工id 员工名  年龄 电话 部门id
		Department department1 = new Department(1, "新闻部");
		departmentList.add(department1);
		
		
//		会议的所有内容
		allInfoList = new ArrayList<MeetingAllInfo>();
		
	}
}
