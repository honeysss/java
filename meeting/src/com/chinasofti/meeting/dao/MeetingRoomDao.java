package com.chinasofti.meeting.dao;

import java.util.ArrayList;

import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.MeetingRoom;

public class MeetingRoomDao {

//	查询所有会议
	public ArrayList<MeetingRoom> selectAll() {
		return Init.meetingRoomList;
	}
	
//	通过id查询会议室
	public MeetingRoom selectById(int id) {
		MeetingRoom meetingRoom = null;
		for (int i = 0; i < Init.meetingRoomList.size(); i++) {
			if (id == Init.meetingRoomList.get(i).getMid()) {
				meetingRoom = Init.meetingRoomList.get(i);
			}
		}
		return meetingRoom;
	}
	
//	通过类型查询会议室
	public ArrayList<MeetingRoom> selectByType(String type) {
		ArrayList<MeetingRoom> meetingRoom = new ArrayList<MeetingRoom>();
		for (int i = 0; i < Init.meetingRoomList.size(); i++) {
			if (type.equals(Init.meetingRoomList.get(i).getType())) {
				meetingRoom.add(Init.meetingRoomList.get(i));
			}
		}
		return meetingRoom;
	}
	
//	通过大小（大中小）查询会议室
	public ArrayList<MeetingRoom> selectBySize(String size) {
		ArrayList<MeetingRoom> meetingRoom = new ArrayList<MeetingRoom>();
		for (int i = 0; i < Init.meetingRoomList.size(); i++) {
			if (size.equals(Init.meetingRoomList.get(i).getSize())) {
				meetingRoom.add(Init.meetingRoomList.get(i));
			}
		}
		return meetingRoom;
	}
	
//	增加会议室
	public void add(MeetingRoom meetingRoom) {
		Init.meetingRoomList.add(meetingRoom);
		System.out.println("增加会议室" + meetingRoom.getType() + "成功");
	}
	
//	删除会议室
	public Boolean delete(int id) {
		Boolean flag = false;
		for (int i = 0; i < Init.meetingRoomList.size(); i++) {
			if (id == Init.meetingRoomList.get(i).getMid()) {
				flag = true;
				String meetingRoom = Init.meetingRoomList.get(i).getLocation();
				Init.meetingRoomList.remove(i);
				System.out.println("删除会议室" + meetingRoom + "成功");
			}
		}
		return flag;
	}
	
//	修改会议室
	public void update(int id, MeetingRoom meetingRoom) {
		for (int i = 0; i < Init.meetingRoomList.size(); i++) {
			if (id == Init.meetingRoomList.get(i).getMid()) {
				Init.meetingRoomList.set(i, meetingRoom);
				System.out.println("修改会议室" + meetingRoom.getMid() + "成功");
			}
		}
	}
	
}
