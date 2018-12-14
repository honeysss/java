package com.chinasofti.meeting.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.chinasofti.meeting.dao.MeetingDao;
import com.chinasofti.meeting.dao.MeetingRoomDao;
import com.chinasofti.meeting.dao.StaffDao;
import com.chinasofti.meeting.dao.UserDao;
import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.Meeting;
import com.chinasofti.meeting.vo.MeetingAllInfo;
import com.chinasofti.meeting.vo.MeetingRoom;
import com.chinasofti.meeting.vo.User;

public class MeetingManage {
	Scanner input = new Scanner(System.in);
	UserDao userDao = new UserDao();
	MeetingDao meetingDao = new MeetingDao();
	MeetingRoomDao meetingRoomDao = new MeetingRoomDao();
	StaffDao staffDao = new StaffDao();

	
	public void meetingManage() {
		while(true) {
			System.out.println("1、增加会议\t2、删除会议\t3、修改会议或会议人员\t4、查看会议\t5、返回上一层");
			System.out.print("请输入您要进行的操作:");
			int chioce = input.nextInt();
			switch(chioce) {
				case 1:
					addMeeting();
					break;
				case 2:
					deleteMeeting();
					
					break;
				case 3:
					System.out.println("1、修改会议信息\t2、对会议人员进行增删操作\t3、返回上一层");
					System.out.print("请输入您要进行的操作:");
					int chioce2 = input.nextInt();
					switch(chioce2) {
						case 1:
							updateMeeting();
							break;
						case 2:
							updateMeetingStaff();
							break;
						case 3:
							return;
						default:
							System.out.println("没有该选项，请重新输入");
							break;
					}
				case 4:
					selectMeeting();
					break;
				case 5:
					System.out.println();
					return;
			default:
					System.out.println("没有该选项，请重新选择");
					break;
			}
		}
	}
	
//	查看会议
	public void selectMeeting() {
		if (meetingDao.selectAll().size() != 0) {
			ArrayList<MeetingAllInfo> allInfoList = meetingDao.selectAll();
			for (int i = 0; i < allInfoList.size(); i++) {
				System.out.println(allInfoList.get(i));
			}
			System.err.println();
		} else {
			System.out.println("还没有会议,快去添加会议吧");
		}
	}

	
//	增加会议
	public void addMeeting() {
		

		int newId = meetingDao.selectMaxId();
		
//		
		
		System.out.print("\n请输入会议名称:");
		String newMeetingName = input.next();
		System.out.print("请输入会议开始时间:");
		String newMeetingstartTime = input.next();		
		System.out.print("请输入会议结束时间:");
		String newMeetingEndTime = input.next();
		
//		增加会议的时候要把 会议室信息 员工信息都列出来
		
//		输出会议室信息
		ArrayList<MeetingRoom> meetings = new ArrayList<MeetingRoom>();
		meetings = meetingRoomDao.selectAll();
		if (meetings.size() != 0) {
			System.out.println("\n现有的会议室如下:");
			for (int i = 0; i < meetings.size(); i++) {
				System.out.println(meetings.get(i));
			}
		} else {
			System.out.println("还没有会议室，快去添加会议室吧");
			meetingManage();
		}
		
		System.out.print("请输入会议室id:");
		int newMeetingRoomId = input.nextInt();
//		查询一下是否存在该会议室
		if (meetingRoomDao.selectById(newMeetingRoomId) != null) {
//			如果存在该会议室
//			把所有的员工列出来
			if (staffDao.selectAll().size() != 0) {
				System.out.println("\n现有的员工信息如下:");
				for (int i = 0; i < staffDao.selectAll().size(); i++) {
					System.out.println(staffDao.selectAll().get(i));
				}

			} else {
				System.out.println("还没有员工信息，快去添加员工吧");
				meetingManage();
			}
			
			System.out.print("请输入要参加会议的员工的id,并以为‘;’分隔:");
			String newStaffId = input.next();
			
//			得到输入的id数组
			String[] newStaffIdList = newStaffId.split(";");
			
//			判断每个id是否存在
			for (int i = 0; i < newStaffIdList.length; i++) {
//				如果不存在某个id
				if (!staffDao.ifExistById(Integer.parseInt(newStaffIdList[i]))) {
					System.out.println("不存在员工id" + Integer.parseInt(newStaffIdList[i]) + ",请重新输入");
					addMeeting();
				}
			}
//			如果员工id都存在 就会往下执行 把每个员工的id都添加到会议中
			for (int i = 0; i < newStaffIdList.length; i++) {
//				增加会议
				meetingDao.addMeeting(new Meeting(newId, newMeetingName, newMeetingRoomId, Integer.parseInt(newStaffIdList[i]), newMeetingstartTime, newMeetingEndTime));

			}
			
			System.out.println("增加会议" + newMeetingName + "成功，会议信息如下:");
			
			for (int i = 0; i < meetingDao.selectById(newId).size(); i++) {
				System.out.println(meetingDao.selectById(newId).get(i));
			}
			System.out.println();
			meetingManage();
			
			

//			先问一次
//			System.out.print("请输入要参加会议的员工的id:");
//			int newStaffId = input.nextInt();
//			
////			如果存在该员工id
//			if (staffDao.ifExistById(newStaffId)) {
//				
////				并且该员工还没参加这个会议
//				if (meetingDao.ifStaffExistById(newStaffId, newId)) {
//					System.out.println("该员工已经参加这个会议了,请重新输入");
//					addMeeting();
//				} else {
////					增加会议
//					meetingDao.addMeeting(new Meeting(newId, newMeetingName, newMeetingRoomId, newStaffId, newMeetingstartTime, newMeetingEndTime));
//
//					
//					while (true) {
//		//				循环询问是否还要继续添加员工
//						System.out.print("1、是  2、否\n是否还要继续添加员工：");
//						int chioce = input.nextInt();
//						if (chioce == 1) {
//							System.out.print("请输入要参加会议的员工的id:");
//							int newStaffId2 = input.nextInt();
//							
////							如果存在这个员工id
//							if (staffDao.ifExistById(newStaffId2)) {
////								并且这个员工还没有参加这个会议
//								if (meetingDao.ifStaffExistById(newStaffId2, newId)) {
//									System.out.println("该员工已经参加这个会议了,请重新输入");
//									addMeeting();
//								} else {
//	//								增加会议
//									meetingDao.addMeeting(new Meeting(newId, newMeetingName, newMeetingRoomId, newStaffId2, newMeetingstartTime, newMeetingEndTime));
//								}
//							} else {
//								System.out.println("不存在该员工");
//								break;
//							}
//						} else {
//							System.out.println("增加会议" + newMeetingName + "成功，会议信息如下:");
//		//						增加完之后把该会议信息通过会议id列出来
//							for (int i = 0; i < meetingDao.selectById(newId).size(); i++) {
//								System.out.println(meetingDao.selectById(newId).get(i));
//							}
//							System.out.println();
//							meetingManage();
//						}
//					}
//					
//				}
//
//				
//				
//				
//			} else {
//				System.out.println("不存在该员工，请重新输入");
//				addMeeting();
//			}
			
			
		} else {
			System.out.println("不存在该会议室，请重新输入");
			addMeeting();
		}
		

		
		
	}
	
//	删除
	public void deleteMeeting() {
//		先把所有会议查询出来
		if (meetingDao.selectAll() != null) {
			ArrayList<MeetingAllInfo> allInfoList = meetingDao.selectAll();
			for (int i = 0; i < allInfoList.size(); i++) {
				System.out.println(allInfoList.get(i));
			}
		} else {
			System.out.println("还没有会议,快去添加会议吧");
			meetingManage();
		}
		
		System.out.print("请输入您想要删除的会议id:");
		int deleteId = input.nextInt();
		
		if (!meetingDao.delete(deleteId)) {
			System.out.println("该id不存在，请重新输入会议id:");
			deleteMeeting();
		} else {
			System.out.println("删除会议" + deleteId + "成功");
		}
	}

//	修改
	public void updateMeeting() {
//		先把所有会议查询出来
		if (meetingDao.selectAllMeeting() != null) {
			for (int i = 0; i < meetingDao.selectAllMeeting().size(); i++) {
				System.out.println(meetingDao.selectAllMeeting().get(i));
			}
		} else {
			System.out.println("还没有会议,快去添加会议吧");
			meetingManage();
		}
		
		System.out.print("请输入您想要修改的会议的id:");
		int updateId = input.nextInt();
		
		if (!meetingDao.ifExistById(updateId)) {
			System.out.println("该id不存在，请重新输入会议id\n");
			updateMeeting();
		} else {
			
//			输出会议室信息
			ArrayList<MeetingRoom> meetings = new ArrayList<MeetingRoom>();
			meetings = meetingRoomDao.selectAll();
			if (meetings.size() != 0) {
				System.out.println("\n现有的会议室如下:");
				for (int i = 0; i < meetings.size(); i++) {
					System.out.println(meetings.get(i));
				}
			} else {
				System.out.println("还没有会议室，快去添加会议室吧");
				meetingManage();
			}

			System.out.print("请输入修改后的会议室id:");
			int updateMeetingRoomId = input.nextInt();
			
//			如果id存在
//			查询一下是否存在该会议室
			if (meetingRoomDao.selectById(updateMeetingRoomId) != null) {
//				如果存在该会议室	直接修改会议 
					System.out.print("请输入修改后的会议名称:");
					String updateMeetingName = input.next();
					

					System.out.print("请输入修改后的会议的开始时间:");
					String updateMeetingStartTime = input.next();
					
					System.out.print("请输入修改后的会议的结束时间:");
					String updateMeetingEndTime = input.next();
					
//					修改会议是循环所有的会议 如果会议id和传过去的会议id相同 把会议重新赋值
//					修改
					meetingDao.update(updateId, updateMeetingName, updateMeetingRoomId, updateMeetingStartTime, updateMeetingEndTime);
//					输出修改后的会议
					System.out.println("修改会议成功，修改后的会议信息如下:");

//						增加完之后把该会议信息通过会议id列出来
					for (int i = 0; i < meetingDao.selectById(updateId).size(); i++) {
						System.out.println(meetingDao.selectById(updateId).get(i));
					}
					System.out.println();
					meetingManage();
				
			} else {
				System.out.println("不存在该会议室，请重新输入\n");
				updateMeeting();
			}
				
			
		}
		
		
	}
	
	

	
//	修改会议人员
	public void updateMeetingStaff() {
//		先把所有会议查询出来
		if (meetingDao.selectAllMeeting().size() != 0) {
			System.out.println("目前的会议信息如下:");
			for (int i = 0; i < meetingDao.selectAllMeeting().size(); i++) {
				System.out.println(meetingDao.selectAllMeeting().get(i));
			}
		} else {
			System.out.println("还没有会议,快去添加会议吧");
			meetingManage();
		}
		
		System.out.print("请输入您想要增删员工的会议的id:");
		int updateId = input.nextInt();
		
		if (!meetingDao.ifExistById(updateId)) {
			System.out.println("该id不存在，请重新输入会议id\n");
			updateMeeting();
		} else {
			
//			询问是要增加员工还是删除员工
			System.out.println("1、为会议增加员工\t2、为会议删除员工");
			System.out.print("请输入您要进行的操作:");
			int chioce = input.nextInt();
			if (chioce == 1) {
//				把所有的员工列出来
				if (staffDao.selectAll().size() != 0) {
					System.out.println("\n现有的员工信息如下:");
					for (int i = 0; i < staffDao.selectAll().size(); i++) {
						System.out.println(staffDao.selectAll().get(i));
					}

				} else {
					System.out.println("还没有员工信息，快去添加员工吧");
					meetingManage();
				}
				
				System.out.print("请输入您想要为本次会议添加员工的id:");
				int addStaffId = input.nextInt();
				
				
//				如果员工存在
				if (staffDao.ifExistById(addStaffId)) {
//					如果该会议还没有该员工
					if (!meetingDao.ifStaffExistById(addStaffId, updateId) ) {

//						添加员工 
						 Meeting meeting = meetingDao.addStaff(updateId, addStaffId);
						 meetingDao.addMeeting(new Meeting(meeting.getMeetingId(), meeting.getMeetingName(), meeting.getMeetingRoomId(), addStaffId, meeting.getStartTime(), meeting.getEndTime()));
						System.out.println("为会议" + updateId + "添加员工" + addStaffId + "成功");
						
						System.out.println();
						meetingManage();
						
						
////						添加之后询问是否还要继续添加
//						while (true) {
//			//				循环询问是否还要继续添加员工
//							System.out.print("1、是  2、否\n是否还要继续添加员工：");
//							int chioce21 = input.nextInt();
//							if (chioce21 == 1) {
//								System.out.print("请输入您想要为本次会议添加员工的id:");
//								int addStaffId2 = input.nextInt();
//								
//								if (staffDao.ifExistById(addStaffId2)) {
////																	添加员工
//									meetingDao.addStaff(updateId, addStaffId2);
//								} else {
//									System.out.println("不存在该员工");
//									break;
//								}
//							} else {
//								System.out.println("添加员工成功，目前会议" + updateId + "信息如下:");
//			//						增加完之后把该会议信息通过会议id列出来
//								for (int i = 0; i < meetingDao.selectById(updateId).size(); i++) {
//									System.out.println(meetingDao.selectById(updateId).get(i));
//								}
//								System.out.println();
//								return;
//							}
//						}
						
						System.out.println();
					} else {
						System.out.println("该员工已参加该会议\n");
						updateMeetingStaff();
					}

					
				} else {
					System.out.println("不存在该员工，请重新输入\n");
					updateMeetingStaff();
				}
				
			} else if (chioce == 2) {
				
//				选择删除的时候就判断一下这个会议是否还有员工可删除
				if (!meetingDao.ifCanBeDelete(updateId)) {
					System.out.println("该会议已经没有员工了,请进行别的操作吧");
					meetingManage();
				}
				
				System.out.print("请输入您要删除的员工的id:");
				int deleteStaffId = input.nextInt();
		
	//			要通过员工id和会议id判断是否存在这个员工
				if (meetingDao.ifStaffExistById(deleteStaffId, updateId)) {
	
	//				删除
					meetingDao.deleteStaff(updateId, deleteStaffId);
					System.out.println("为会议" + updateId + "删除员工" + deleteStaffId + "成功\n");
					meetingManage();
					
	//				判断该会议的员工数是否大于零 如果大于零 继续询问 否则提示
					
					
	//				询问是否还要删除
									
				} else {
					System.out.println("不存在该员工，请重新输入\n");
					updateMeetingStaff();
				}
				
			} else {
				System.out.println("没有该选项");
				meetingManage();
			}
			
			

		}
		
		
	}
	
	
}
