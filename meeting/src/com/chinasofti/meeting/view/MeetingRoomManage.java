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
			System.out.println("1、增加会议室\t2、删除会议室\t3、修改会议室\t4、查找会议室\t5、查看所有会议室\t6、返回上一层");
			System.out.print("请输入您要进行的操作:");
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
					System.out.println("没有该选项，请重新选择");
					break;
			}
		}
	}
	
	
//	增加
	public void addMeeting() {
		int newId = Init.meetingRoomList.get(Init.meetingRoomList.size() - 1).getMid() + 1;
		System.out.print("请输入会议室的类型:");
		String newType = input.next();
		System.out.print("请输入会议室的地点:");
		String newLocation = input.next();
		System.out.print("请输入会议室的容量:");
		int newVolume = input.nextInt();
//		System.out.print("请输入会议室的大小:");
		String newSize = "";
		if (newVolume > 0 && newVolume <= 10) {
			newSize = "小";
		} else if (newVolume > 10 && newVolume <= 50) {
			newSize = "中";
		}else if (newVolume > 50) {
			newSize = "大";
		}
		
		
		meetingRoomDao.add(new MeetingRoom(newId, newType, newLocation, newVolume, newSize));
		
	}
	
//	删除
	public void deleteMeeting() {
		selectAll();
		System.out.print("请输入您要删除的会议室的id:");
		int deleteId = input.nextInt();
		
		if (!meetingRoomDao.delete(deleteId)) {
			System.out.println("该id不存在");
		}
	}
	
//	修改
	public void updateMeeting() {
		selectAll();
		System.out.print("请输入您要修改的会议室的id:");
		int updateId = input.nextInt();
		
		if (meetingRoomDao.selectById(updateId) != null) {
			System.out.print("请输入修改后的会议室地址:");
			String updateLocation = input.next();
			System.out.print("请输入修改后的会议室类型:");
			String updateType = input.next();
			System.out.print("请输入修改后的会议室容量:");
			int updateVolume = input.nextInt();
			String updateSize = "";
			if (updateVolume > 0 && updateVolume <= 10) {
				updateSize = "小";
			} else if (updateVolume > 10 && updateVolume <= 50) {
				updateSize = "中";
			}else if (updateVolume > 50) {
				updateSize = "大";
			}
			meetingRoomDao.update(updateId, new MeetingRoom(updateId, updateType, updateLocation, updateVolume, updateSize));
		} else {
			System.out.println("该id不存在");
		}
	}
	
//	查看某个
	public void selectMeeting() {
		System.out.println("1、通过会议室id查询\t2、通过会议室类型查询\t3、通过会议室大小查询(大 中 小)");
		System.out.print("请输入您想查询的方法:");
		int selectMethod = input.nextInt();
		switch(selectMethod) {
			case 1:
				System.out.print("请输入会议室id:");
				int selectId = input.nextInt();
				
				if (meetingRoomDao.selectById(selectId) != null) {
					System.out.println(meetingRoomDao.selectById(selectId));
				} else {
					System.out.println("没有该id的会议室");
				}
				
				break;
			case 2:
				System.out.print("请输入会议室类型:");
				String selectType = input.next();
				
				ArrayList<MeetingRoom> meetingRoom = meetingRoomDao.selectByType(selectType);
				if (meetingRoom.size() != 0) {
					for (int i = 0; i < meetingRoom.size(); i++) {
						System.out.println(meetingRoom.get(i));
					}
				} else {
					System.out.println("还没有" + selectType + "类型的会议室");
				}
				
				break;
			case 3:
				System.out.print("请输入会议室大小:");
				String selectSize = input.next();
				if (selectSize.equals("大") || selectSize.equals("中") || selectSize.equals("小")) {
					ArrayList<MeetingRoom> meetingRoom2 = meetingRoomDao.selectBySize(selectSize);
					if (meetingRoom2.size() != 0) {
						for (int i = 0; i < meetingRoom2.size(); i++) {
							System.out.println("会议室id:" + meetingRoom2.get(i).getMid() 
									+ "\t会议室类型:" + meetingRoom2.get(i).getType()
									+ "\t会议室地址:" + meetingRoom2.get(i).getLocation()
									+ "\t会议室容量:" + meetingRoom2.get(i).getVolume()
									+ "\t会议室大小:" + meetingRoom2.get(i).getSize());
						}
					} else {
						System.out.println("还没有" + selectSize + "类型的会议室");
					}
				} else {
					System.out.println("您输入的大小类型有误,请重新输入");
				}

				break;
			default:
				break;
		}
	}
	
//	查看所有
	public void selectAll() {
		ArrayList<MeetingRoom> meetings = new ArrayList<MeetingRoom>();
		meetings = meetingRoomDao.selectAll();
		if (meetings.size() != 0) {
			for (int i = 0; i < meetings.size(); i++) {
				System.out.println(meetings.get(i));
			}
		} else {
			System.out.println("还没有会议室，快去添加会议室吧");
		}
	}
	
	
}
