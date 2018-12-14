package com.chinasofti.meeting.dao;

import java.awt.List;
import java.util.ArrayList;

import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.Department;
import com.chinasofti.meeting.vo.Meeting;
import com.chinasofti.meeting.vo.MeetingAllInfo;
import com.chinasofti.meeting.vo.MeetingRoom;
import com.chinasofti.meeting.vo.Staff;

public class MeetingDao {
// 查看所有会议 全部的
	public ArrayList<MeetingAllInfo> selectAll() {
		ArrayList<MeetingAllInfo> allInfoList = new ArrayList<MeetingAllInfo>();
		
		
		for (int i = 0; i < Init.meetingList.size(); i++) {
//			会议类型
			MeetingAllInfo allInfo1 = new MeetingAllInfo();
			
//			会议类型
			Meeting meeting = Init.meetingList.get(i);
//			会议id
			allInfo1.setMeetingId(meeting.getMeetingId());
//			会议名
			allInfo1.setMeetingName(meeting.getMeetingName());
//			开始时间
			allInfo1.setStartdate(meeting.getStartTime());
//			结束时间
			allInfo1.setEnddate(meeting.getEndTime());
//			获取会议室id
			int meetingRoomId;
			meetingRoomId = meeting.getMeetingRoomId();
//			循环会议室 得到id相同的
			for (int j = 0; j < Init.meetingRoomList.size(); j++) {
				if (meetingRoomId == Init.meetingRoomList.get(j).getMid()) {
//					声明一个会议室类型
					MeetingRoom meetingRoom = Init.meetingRoomList.get(j);
					
//					会议地点
					allInfo1.setLocation(meetingRoom.getLocation());
//					类型
					allInfo1.setType(meetingRoom.getType());
//					容量
					allInfo1.setVolume(meetingRoom.getVolume());
//					大小
					allInfo1.setSize(meetingRoom.getSize());
					
				}
			}
			
			int staffId;
			staffId = meeting.getStaffId();
//			循环员工 得到id相同的		
			for (int j = 0; j < Init.staffList.size(); j++) {
				if (staffId == Init.staffList.get(j).getStaffId()) {
//					员工类型
					Staff staff = Init.staffList.get(j);
					
//					员工姓名
					allInfo1.setUname(staff.getStaffName());
//					员工年龄
					allInfo1.setStaffAge(staff.getAge());
//					员工电话
					allInfo1.setStaffTel(staff.getTel());
					
//					获取部门id 得到部门名称
					int departmentId = staff.getDepartmentId();
//					循环部门 得到id相同的
					for (int k = 0; k < Init.departmentList.size(); k++) {
						if (departmentId == Init.departmentList.get(k).getDepartmentId()) {
//							部门类型
							Department department = Init.departmentList.get(k);
							
//							员工部门
							allInfo1.setStaffDepartment(department.getDepartmentName());
						}
					}
					
				}
			}
			
//			循环一次结束后得到一个完整的会议信息 add到数组中
			allInfoList.add(allInfo1);
		}
		
		return allInfoList;
		
	}

//	查看会议 通过会议id
// 返回一个数组
	public ArrayList<MeetingAllInfo> selectById(int id) {
		ArrayList<MeetingAllInfo> allInfoList = new ArrayList<MeetingAllInfo>();
		
		for (int i = 0; i < Init.meetingList.size(); i++) {
//			如果id 相等
			if (id == Init.meetingList.get(i).getMeetingId()) {
//				每次初始化一个会议类型
				MeetingAllInfo allInfo = new MeetingAllInfo();

//				会议类型
				Meeting meeting = Init.meetingList.get(i);
//				会议id
				allInfo.setMeetingId(meeting.getMeetingId());
//				会议名
				allInfo.setMeetingName(meeting.getMeetingName());
//				开始时间
				allInfo.setStartdate(meeting.getStartTime());
//				结束时间
				allInfo.setEnddate(meeting.getEndTime());
//				获取会议室id
				int meetingRoomId;
				meetingRoomId = meeting.getMeetingRoomId();
//				循环会议室 得到id相同的
				for (int j = 0; j < Init.meetingRoomList.size(); j++) {
					if (meetingRoomId == Init.meetingRoomList.get(j).getMid()) {
//						声明一个会议室类型
						MeetingRoom meetingRoom = Init.meetingRoomList.get(j);
						
//						会议地点
						allInfo.setLocation(meetingRoom.getLocation());
//						类型
						allInfo.setType(meetingRoom.getType());
//						容量
						allInfo.setVolume(meetingRoom.getVolume());
//						大小
						allInfo.setSize(meetingRoom.getSize());

					}
				}
				
//				获取用户id
				int staffId;
				staffId = Init.meetingList.get(i).getStaffId();
//				循环员工 得到id相同的
				for (int j = 0; j < Init.staffList.size(); j++) {
					if (staffId == Init.staffList.get(j).getStaffId()) {
//						员工类型
						Staff staff = Init.staffList.get(j);
						
//						员工姓名
						allInfo.setUname(staff.getStaffName());
//						员工年龄
						allInfo.setStaffAge(staff.getAge());
//						员工电话
						allInfo.setStaffTel(staff.getTel());
						
//						获取部门id 得到部门名称
						int departmentId = staff.getDepartmentId();
//						循环部门 得到id相同的
						for (int k = 0; k < Init.departmentList.size(); k++) {
							if (departmentId == Init.departmentList.get(k).getDepartmentId()) {
//								部门类型
								Department department = Init.departmentList.get(k);
								
//								员工部门
								allInfo.setStaffDepartment(department.getDepartmentName());
							}
						}
						
					}
				}
				allInfoList.add(allInfo);
			}
			
		}
		
		
		return allInfoList;
	}
	
//	删除会议 
	public boolean delete(int id) {
		boolean flag = false;
		for (int i = Init.meetingList.size() - 1; i >= 0; i --) {
			if (id == Init.meetingList.get(i).getMeetingId()) {
				flag = true;
				Init.meetingList.remove(i);
			}
		}
		return flag;
	}

	
//	修改会议
//	接收 会议id 通过会议id查询  修改后的 会议名称 会议室id  开始时间 结束时间 
	public boolean update(int id, String meetingName, int meetingRoomId, String startTime, String endTime) {
		boolean flag = false;
		for (int i = 0; i < Init.meetingList.size(); i++) {
			if (id == Init.meetingList.get(i).getMeetingId()) {
				flag = true;
				Init.meetingList.get(i).setMeetingName(meetingName);
				Init.meetingList.get(i).setMeetingRoomId(meetingRoomId);
				Init.meetingList.get(i).setStartTime(startTime);
				Init.meetingList.get(i).setEndTime(endTime);
			}
		}
		
		return flag;
	}

//	增加会议
	public void addMeeting(Meeting meeting) {
		Init.meetingList.add(meeting);
	}
	
	
//	通过会议id查看该会议是否存在
	public boolean ifExistById(int id) {
		boolean flag = false;
		for (int i = 0; i < Init.meetingList.size(); i++) {
			if (id == Init.meetingList.get(i).getMeetingId()) {
				flag = true;
			}
		}
		return flag;
	}
	
//	为会议增加员工
	public Meeting addStaff(int meetingId, int staffId) {
		Meeting meeting = null;
		for (int i = 0; i < Init.meetingList.size(); i++) {
			if (meetingId == Init.meetingList.get(i).getMeetingId()) {
//				如果id相等 实例化一个新的meeting
				meeting = Init.meetingList.get(i);
				break;
			}
		}
		
		return meeting;
	}
	
	
//	为会议删除员工
	public void deleteStaff(int meetingId, int staffId) {
		int j = 0;
		for (int i = 0; i < Init.meetingList.size(); i++) {
//			如果会议id和员工id 都相等 因为一个员工可能参加多个会议 
//			一个员工对应一个会议id这种情况下只有一条信息
//			删除这个会议 如果会议只剩一个 则只清空员工信息
			if (meetingId == Init.meetingList.get(i).getMeetingId()) {
				j++;
			}
		}
		if (j > 1) {
			for (int i = 0; i < Init.meetingList.size(); i++) {
				if (meetingId == Init.meetingList.get(i).getMeetingId() && staffId == Init.meetingList.get(i).getStaffId()) {
					Init.meetingList.remove(i);
				}
			}
		} else {
			for (int i = 0; i < Init.meetingList.size(); i++) {
				if (meetingId == Init.meetingList.get(i).getMeetingId() && staffId == Init.meetingList.get(i).getStaffId()) {
					Init.meetingList.get(i).setStaffId(-1);
				}
			}
		}
	}
	
	
//	查看所有会议
	public ArrayList<Meeting> selectAllMeeting() {
		return Init.meetingList;
	}
	
	
//	通过员工id和会议id查看该员工是否存在
	public boolean ifStaffExistById(int staffId, int meetingId) {
		boolean flag = false;
		for (int i = 0; i < Init.meetingList.size(); i++) {
			if (meetingId == Init.meetingList.get(i).getMeetingId()  && staffId == Init.meetingList.get(i).getStaffId()) {
				flag = true;
			}
		}
		return flag;
	}
	
//	通过会议id查看是否有员工可被删除
	public boolean ifCanBeDelete(int id) {
		boolean flag = false;
		for (int i = 0; i < Init.meetingList.size(); i++) {
			if (id == Init.meetingList.get(i).getMeetingId()) {
				if (Init.meetingList.get(i).getStaffId() != -1) {
					flag = true;
				}
			}
		}
		
		
		return flag;
	}
	
	
//	通过用户姓名查看自己的会议信息
	public ArrayList<Meeting> selectMeetingByName(String staffName) {
		ArrayList<Meeting> meeting = new ArrayList<Meeting>();
		
		boolean flag = false;
		
		for (int i = 0; i < Init.staffList.size(); i++) {
			if (staffName.equals(Init.staffList.get(i).getStaffName())) {
				flag = true;
//				通过员工名获取id 通过员工id查找员工信息
				for (int j = 0; j < Init.meetingList.size(); j++) {
					if (Init.staffList.get(i).getStaffId() == Init.meetingList.get(j).getStaffId()) {
						meeting.add(Init.meetingList.get(j));
					}
				} 
			} 
		}
		
		if (!flag) {
			System.out.println("还没有你的员工信息");
		} else {
			if (meeting.size() == 0) {
				System.out.println("你还没有参加任何信会议");
			}
		}
		
		
		
		return meeting;
	}
	
	
//	通过会议id和员工姓名删除会议
	public void deleteMeeting(int meetingId, String staffName) {
		for (int i = 0; i < Init.staffList.size(); i++) {
			if (staffName.equals(Init.staffList.get(i).getStaffName())) {
//				通过员工名获取id 通过员工id查找员工信息
				for (int j = 0; j < Init.meetingList.size(); j++) {
					if (meetingId == Init.meetingList.get(j).getMeetingId() && Init.staffList.get(i).getStaffId() == Init.meetingList.get(j).getStaffId()) {
					
						Init.meetingList.remove(j);
						System.out.println("删除会议" + meetingId + "成功");
						break;
					}
				} 
			} 
		}
	}
	
	
//  得到会议最大的id值
	public int selectMaxId() {
		ArrayList<Integer> meetId = new ArrayList<Integer>();
		
		for (int i = 0; i < Init.meetingList.size(); i++) {
			meetId.add(Init.meetingList.get(i).getMeetingId());
		}
		
		int max = meetId.get(0);
		
		for (int i = 0; i < meetId.size(); i++) {
			if (meetId.get(i) > max) {
				max = meetId.get(i);
			}
		}
		
		return max + 1;
	}
	
	
}
