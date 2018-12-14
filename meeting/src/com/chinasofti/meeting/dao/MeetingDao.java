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
// �鿴���л��� ȫ����
	public ArrayList<MeetingAllInfo> selectAll() {
		ArrayList<MeetingAllInfo> allInfoList = new ArrayList<MeetingAllInfo>();
		
		
		for (int i = 0; i < Init.meetingList.size(); i++) {
//			��������
			MeetingAllInfo allInfo1 = new MeetingAllInfo();
			
//			��������
			Meeting meeting = Init.meetingList.get(i);
//			����id
			allInfo1.setMeetingId(meeting.getMeetingId());
//			������
			allInfo1.setMeetingName(meeting.getMeetingName());
//			��ʼʱ��
			allInfo1.setStartdate(meeting.getStartTime());
//			����ʱ��
			allInfo1.setEnddate(meeting.getEndTime());
//			��ȡ������id
			int meetingRoomId;
			meetingRoomId = meeting.getMeetingRoomId();
//			ѭ�������� �õ�id��ͬ��
			for (int j = 0; j < Init.meetingRoomList.size(); j++) {
				if (meetingRoomId == Init.meetingRoomList.get(j).getMid()) {
//					����һ������������
					MeetingRoom meetingRoom = Init.meetingRoomList.get(j);
					
//					����ص�
					allInfo1.setLocation(meetingRoom.getLocation());
//					����
					allInfo1.setType(meetingRoom.getType());
//					����
					allInfo1.setVolume(meetingRoom.getVolume());
//					��С
					allInfo1.setSize(meetingRoom.getSize());
					
				}
			}
			
			int staffId;
			staffId = meeting.getStaffId();
//			ѭ��Ա�� �õ�id��ͬ��		
			for (int j = 0; j < Init.staffList.size(); j++) {
				if (staffId == Init.staffList.get(j).getStaffId()) {
//					Ա������
					Staff staff = Init.staffList.get(j);
					
//					Ա������
					allInfo1.setUname(staff.getStaffName());
//					Ա������
					allInfo1.setStaffAge(staff.getAge());
//					Ա���绰
					allInfo1.setStaffTel(staff.getTel());
					
//					��ȡ����id �õ���������
					int departmentId = staff.getDepartmentId();
//					ѭ������ �õ�id��ͬ��
					for (int k = 0; k < Init.departmentList.size(); k++) {
						if (departmentId == Init.departmentList.get(k).getDepartmentId()) {
//							��������
							Department department = Init.departmentList.get(k);
							
//							Ա������
							allInfo1.setStaffDepartment(department.getDepartmentName());
						}
					}
					
				}
			}
			
//			ѭ��һ�ν�����õ�һ�������Ļ�����Ϣ add��������
			allInfoList.add(allInfo1);
		}
		
		return allInfoList;
		
	}

//	�鿴���� ͨ������id
// ����һ������
	public ArrayList<MeetingAllInfo> selectById(int id) {
		ArrayList<MeetingAllInfo> allInfoList = new ArrayList<MeetingAllInfo>();
		
		for (int i = 0; i < Init.meetingList.size(); i++) {
//			���id ���
			if (id == Init.meetingList.get(i).getMeetingId()) {
//				ÿ�γ�ʼ��һ����������
				MeetingAllInfo allInfo = new MeetingAllInfo();

//				��������
				Meeting meeting = Init.meetingList.get(i);
//				����id
				allInfo.setMeetingId(meeting.getMeetingId());
//				������
				allInfo.setMeetingName(meeting.getMeetingName());
//				��ʼʱ��
				allInfo.setStartdate(meeting.getStartTime());
//				����ʱ��
				allInfo.setEnddate(meeting.getEndTime());
//				��ȡ������id
				int meetingRoomId;
				meetingRoomId = meeting.getMeetingRoomId();
//				ѭ�������� �õ�id��ͬ��
				for (int j = 0; j < Init.meetingRoomList.size(); j++) {
					if (meetingRoomId == Init.meetingRoomList.get(j).getMid()) {
//						����һ������������
						MeetingRoom meetingRoom = Init.meetingRoomList.get(j);
						
//						����ص�
						allInfo.setLocation(meetingRoom.getLocation());
//						����
						allInfo.setType(meetingRoom.getType());
//						����
						allInfo.setVolume(meetingRoom.getVolume());
//						��С
						allInfo.setSize(meetingRoom.getSize());

					}
				}
				
//				��ȡ�û�id
				int staffId;
				staffId = Init.meetingList.get(i).getStaffId();
//				ѭ��Ա�� �õ�id��ͬ��
				for (int j = 0; j < Init.staffList.size(); j++) {
					if (staffId == Init.staffList.get(j).getStaffId()) {
//						Ա������
						Staff staff = Init.staffList.get(j);
						
//						Ա������
						allInfo.setUname(staff.getStaffName());
//						Ա������
						allInfo.setStaffAge(staff.getAge());
//						Ա���绰
						allInfo.setStaffTel(staff.getTel());
						
//						��ȡ����id �õ���������
						int departmentId = staff.getDepartmentId();
//						ѭ������ �õ�id��ͬ��
						for (int k = 0; k < Init.departmentList.size(); k++) {
							if (departmentId == Init.departmentList.get(k).getDepartmentId()) {
//								��������
								Department department = Init.departmentList.get(k);
								
//								Ա������
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
	
//	ɾ������ 
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

	
//	�޸Ļ���
//	���� ����id ͨ������id��ѯ  �޸ĺ�� �������� ������id  ��ʼʱ�� ����ʱ�� 
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

//	���ӻ���
	public void addMeeting(Meeting meeting) {
		Init.meetingList.add(meeting);
	}
	
	
//	ͨ������id�鿴�û����Ƿ����
	public boolean ifExistById(int id) {
		boolean flag = false;
		for (int i = 0; i < Init.meetingList.size(); i++) {
			if (id == Init.meetingList.get(i).getMeetingId()) {
				flag = true;
			}
		}
		return flag;
	}
	
//	Ϊ��������Ա��
	public Meeting addStaff(int meetingId, int staffId) {
		Meeting meeting = null;
		for (int i = 0; i < Init.meetingList.size(); i++) {
			if (meetingId == Init.meetingList.get(i).getMeetingId()) {
//				���id��� ʵ����һ���µ�meeting
				meeting = Init.meetingList.get(i);
				break;
			}
		}
		
		return meeting;
	}
	
	
//	Ϊ����ɾ��Ա��
	public void deleteStaff(int meetingId, int staffId) {
		int j = 0;
		for (int i = 0; i < Init.meetingList.size(); i++) {
//			�������id��Ա��id ����� ��Ϊһ��Ա�����ܲμӶ������ 
//			һ��Ա����Ӧһ������id���������ֻ��һ����Ϣ
//			ɾ��������� �������ֻʣһ�� ��ֻ���Ա����Ϣ
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
	
	
//	�鿴���л���
	public ArrayList<Meeting> selectAllMeeting() {
		return Init.meetingList;
	}
	
	
//	ͨ��Ա��id�ͻ���id�鿴��Ա���Ƿ����
	public boolean ifStaffExistById(int staffId, int meetingId) {
		boolean flag = false;
		for (int i = 0; i < Init.meetingList.size(); i++) {
			if (meetingId == Init.meetingList.get(i).getMeetingId()  && staffId == Init.meetingList.get(i).getStaffId()) {
				flag = true;
			}
		}
		return flag;
	}
	
//	ͨ������id�鿴�Ƿ���Ա���ɱ�ɾ��
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
	
	
//	ͨ���û������鿴�Լ��Ļ�����Ϣ
	public ArrayList<Meeting> selectMeetingByName(String staffName) {
		ArrayList<Meeting> meeting = new ArrayList<Meeting>();
		
		boolean flag = false;
		
		for (int i = 0; i < Init.staffList.size(); i++) {
			if (staffName.equals(Init.staffList.get(i).getStaffName())) {
				flag = true;
//				ͨ��Ա������ȡid ͨ��Ա��id����Ա����Ϣ
				for (int j = 0; j < Init.meetingList.size(); j++) {
					if (Init.staffList.get(i).getStaffId() == Init.meetingList.get(j).getStaffId()) {
						meeting.add(Init.meetingList.get(j));
					}
				} 
			} 
		}
		
		if (!flag) {
			System.out.println("��û�����Ա����Ϣ");
		} else {
			if (meeting.size() == 0) {
				System.out.println("�㻹û�вμ��κ��Ż���");
			}
		}
		
		
		
		return meeting;
	}
	
	
//	ͨ������id��Ա������ɾ������
	public void deleteMeeting(int meetingId, String staffName) {
		for (int i = 0; i < Init.staffList.size(); i++) {
			if (staffName.equals(Init.staffList.get(i).getStaffName())) {
//				ͨ��Ա������ȡid ͨ��Ա��id����Ա����Ϣ
				for (int j = 0; j < Init.meetingList.size(); j++) {
					if (meetingId == Init.meetingList.get(j).getMeetingId() && Init.staffList.get(i).getStaffId() == Init.meetingList.get(j).getStaffId()) {
					
						Init.meetingList.remove(j);
						System.out.println("ɾ������" + meetingId + "�ɹ�");
						break;
					}
				} 
			} 
		}
	}
	
	
//  �õ���������idֵ
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
