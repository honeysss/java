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
			System.out.println("1�����ӻ���\t2��ɾ������\t3���޸Ļ���������Ա\t4���鿴����\t5��������һ��");
			System.out.print("��������Ҫ���еĲ���:");
			int chioce = input.nextInt();
			switch(chioce) {
				case 1:
					addMeeting();
					break;
				case 2:
					deleteMeeting();
					
					break;
				case 3:
					System.out.println("1���޸Ļ�����Ϣ\t2���Ի�����Ա������ɾ����\t3��������һ��");
					System.out.print("��������Ҫ���еĲ���:");
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
							System.out.println("û�и�ѡ�����������");
							break;
					}
				case 4:
					selectMeeting();
					break;
				case 5:
					System.out.println();
					return;
			default:
					System.out.println("û�и�ѡ�������ѡ��");
					break;
			}
		}
	}
	
//	�鿴����
	public void selectMeeting() {
		if (meetingDao.selectAll().size() != 0) {
			ArrayList<MeetingAllInfo> allInfoList = meetingDao.selectAll();
			for (int i = 0; i < allInfoList.size(); i++) {
				System.out.println(allInfoList.get(i));
			}
			System.err.println();
		} else {
			System.out.println("��û�л���,��ȥ��ӻ����");
		}
	}

	
//	���ӻ���
	public void addMeeting() {
		

		int newId = meetingDao.selectMaxId();
		
//		
		
		System.out.print("\n�������������:");
		String newMeetingName = input.next();
		System.out.print("��������鿪ʼʱ��:");
		String newMeetingstartTime = input.next();		
		System.out.print("������������ʱ��:");
		String newMeetingEndTime = input.next();
		
//		���ӻ����ʱ��Ҫ�� ��������Ϣ Ա����Ϣ���г���
		
//		�����������Ϣ
		ArrayList<MeetingRoom> meetings = new ArrayList<MeetingRoom>();
		meetings = meetingRoomDao.selectAll();
		if (meetings.size() != 0) {
			System.out.println("\n���еĻ���������:");
			for (int i = 0; i < meetings.size(); i++) {
				System.out.println(meetings.get(i));
			}
		} else {
			System.out.println("��û�л����ң���ȥ��ӻ����Ұ�");
			meetingManage();
		}
		
		System.out.print("�����������id:");
		int newMeetingRoomId = input.nextInt();
//		��ѯһ���Ƿ���ڸû�����
		if (meetingRoomDao.selectById(newMeetingRoomId) != null) {
//			������ڸû�����
//			�����е�Ա���г���
			if (staffDao.selectAll().size() != 0) {
				System.out.println("\n���е�Ա����Ϣ����:");
				for (int i = 0; i < staffDao.selectAll().size(); i++) {
					System.out.println(staffDao.selectAll().get(i));
				}

			} else {
				System.out.println("��û��Ա����Ϣ����ȥ���Ա����");
				meetingManage();
			}
			
			System.out.print("������Ҫ�μӻ����Ա����id,����Ϊ��;���ָ�:");
			String newStaffId = input.next();
			
//			�õ������id����
			String[] newStaffIdList = newStaffId.split(";");
			
//			�ж�ÿ��id�Ƿ����
			for (int i = 0; i < newStaffIdList.length; i++) {
//				���������ĳ��id
				if (!staffDao.ifExistById(Integer.parseInt(newStaffIdList[i]))) {
					System.out.println("������Ա��id" + Integer.parseInt(newStaffIdList[i]) + ",����������");
					addMeeting();
				}
			}
//			���Ա��id������ �ͻ�����ִ�� ��ÿ��Ա����id����ӵ�������
			for (int i = 0; i < newStaffIdList.length; i++) {
//				���ӻ���
				meetingDao.addMeeting(new Meeting(newId, newMeetingName, newMeetingRoomId, Integer.parseInt(newStaffIdList[i]), newMeetingstartTime, newMeetingEndTime));

			}
			
			System.out.println("���ӻ���" + newMeetingName + "�ɹ���������Ϣ����:");
			
			for (int i = 0; i < meetingDao.selectById(newId).size(); i++) {
				System.out.println(meetingDao.selectById(newId).get(i));
			}
			System.out.println();
			meetingManage();
			
			

//			����һ��
//			System.out.print("������Ҫ�μӻ����Ա����id:");
//			int newStaffId = input.nextInt();
//			
////			������ڸ�Ա��id
//			if (staffDao.ifExistById(newStaffId)) {
//				
////				���Ҹ�Ա����û�μ��������
//				if (meetingDao.ifStaffExistById(newStaffId, newId)) {
//					System.out.println("��Ա���Ѿ��μ����������,����������");
//					addMeeting();
//				} else {
////					���ӻ���
//					meetingDao.addMeeting(new Meeting(newId, newMeetingName, newMeetingRoomId, newStaffId, newMeetingstartTime, newMeetingEndTime));
//
//					
//					while (true) {
//		//				ѭ��ѯ���Ƿ�Ҫ�������Ա��
//						System.out.print("1����  2����\n�Ƿ�Ҫ�������Ա����");
//						int chioce = input.nextInt();
//						if (chioce == 1) {
//							System.out.print("������Ҫ�μӻ����Ա����id:");
//							int newStaffId2 = input.nextInt();
//							
////							����������Ա��id
//							if (staffDao.ifExistById(newStaffId2)) {
////								�������Ա����û�вμ��������
//								if (meetingDao.ifStaffExistById(newStaffId2, newId)) {
//									System.out.println("��Ա���Ѿ��μ����������,����������");
//									addMeeting();
//								} else {
//	//								���ӻ���
//									meetingDao.addMeeting(new Meeting(newId, newMeetingName, newMeetingRoomId, newStaffId2, newMeetingstartTime, newMeetingEndTime));
//								}
//							} else {
//								System.out.println("�����ڸ�Ա��");
//								break;
//							}
//						} else {
//							System.out.println("���ӻ���" + newMeetingName + "�ɹ���������Ϣ����:");
//		//						������֮��Ѹû�����Ϣͨ������id�г���
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
//				System.out.println("�����ڸ�Ա��������������");
//				addMeeting();
//			}
			
			
		} else {
			System.out.println("�����ڸû����ң�����������");
			addMeeting();
		}
		

		
		
	}
	
//	ɾ��
	public void deleteMeeting() {
//		�Ȱ����л����ѯ����
		if (meetingDao.selectAll() != null) {
			ArrayList<MeetingAllInfo> allInfoList = meetingDao.selectAll();
			for (int i = 0; i < allInfoList.size(); i++) {
				System.out.println(allInfoList.get(i));
			}
		} else {
			System.out.println("��û�л���,��ȥ��ӻ����");
			meetingManage();
		}
		
		System.out.print("����������Ҫɾ���Ļ���id:");
		int deleteId = input.nextInt();
		
		if (!meetingDao.delete(deleteId)) {
			System.out.println("��id�����ڣ��������������id:");
			deleteMeeting();
		} else {
			System.out.println("ɾ������" + deleteId + "�ɹ�");
		}
	}

//	�޸�
	public void updateMeeting() {
//		�Ȱ����л����ѯ����
		if (meetingDao.selectAllMeeting() != null) {
			for (int i = 0; i < meetingDao.selectAllMeeting().size(); i++) {
				System.out.println(meetingDao.selectAllMeeting().get(i));
			}
		} else {
			System.out.println("��û�л���,��ȥ��ӻ����");
			meetingManage();
		}
		
		System.out.print("����������Ҫ�޸ĵĻ����id:");
		int updateId = input.nextInt();
		
		if (!meetingDao.ifExistById(updateId)) {
			System.out.println("��id�����ڣ��������������id\n");
			updateMeeting();
		} else {
			
//			�����������Ϣ
			ArrayList<MeetingRoom> meetings = new ArrayList<MeetingRoom>();
			meetings = meetingRoomDao.selectAll();
			if (meetings.size() != 0) {
				System.out.println("\n���еĻ���������:");
				for (int i = 0; i < meetings.size(); i++) {
					System.out.println(meetings.get(i));
				}
			} else {
				System.out.println("��û�л����ң���ȥ��ӻ����Ұ�");
				meetingManage();
			}

			System.out.print("�������޸ĺ�Ļ�����id:");
			int updateMeetingRoomId = input.nextInt();
			
//			���id����
//			��ѯһ���Ƿ���ڸû�����
			if (meetingRoomDao.selectById(updateMeetingRoomId) != null) {
//				������ڸû�����	ֱ���޸Ļ��� 
					System.out.print("�������޸ĺ�Ļ�������:");
					String updateMeetingName = input.next();
					

					System.out.print("�������޸ĺ�Ļ���Ŀ�ʼʱ��:");
					String updateMeetingStartTime = input.next();
					
					System.out.print("�������޸ĺ�Ļ���Ľ���ʱ��:");
					String updateMeetingEndTime = input.next();
					
//					�޸Ļ�����ѭ�����еĻ��� �������id�ʹ���ȥ�Ļ���id��ͬ �ѻ������¸�ֵ
//					�޸�
					meetingDao.update(updateId, updateMeetingName, updateMeetingRoomId, updateMeetingStartTime, updateMeetingEndTime);
//					����޸ĺ�Ļ���
					System.out.println("�޸Ļ���ɹ����޸ĺ�Ļ�����Ϣ����:");

//						������֮��Ѹû�����Ϣͨ������id�г���
					for (int i = 0; i < meetingDao.selectById(updateId).size(); i++) {
						System.out.println(meetingDao.selectById(updateId).get(i));
					}
					System.out.println();
					meetingManage();
				
			} else {
				System.out.println("�����ڸû����ң�����������\n");
				updateMeeting();
			}
				
			
		}
		
		
	}
	
	

	
//	�޸Ļ�����Ա
	public void updateMeetingStaff() {
//		�Ȱ����л����ѯ����
		if (meetingDao.selectAllMeeting().size() != 0) {
			System.out.println("Ŀǰ�Ļ�����Ϣ����:");
			for (int i = 0; i < meetingDao.selectAllMeeting().size(); i++) {
				System.out.println(meetingDao.selectAllMeeting().get(i));
			}
		} else {
			System.out.println("��û�л���,��ȥ��ӻ����");
			meetingManage();
		}
		
		System.out.print("����������Ҫ��ɾԱ���Ļ����id:");
		int updateId = input.nextInt();
		
		if (!meetingDao.ifExistById(updateId)) {
			System.out.println("��id�����ڣ��������������id\n");
			updateMeeting();
		} else {
			
//			ѯ����Ҫ����Ա������ɾ��Ա��
			System.out.println("1��Ϊ��������Ա��\t2��Ϊ����ɾ��Ա��");
			System.out.print("��������Ҫ���еĲ���:");
			int chioce = input.nextInt();
			if (chioce == 1) {
//				�����е�Ա���г���
				if (staffDao.selectAll().size() != 0) {
					System.out.println("\n���е�Ա����Ϣ����:");
					for (int i = 0; i < staffDao.selectAll().size(); i++) {
						System.out.println(staffDao.selectAll().get(i));
					}

				} else {
					System.out.println("��û��Ա����Ϣ����ȥ���Ա����");
					meetingManage();
				}
				
				System.out.print("����������ҪΪ���λ������Ա����id:");
				int addStaffId = input.nextInt();
				
				
//				���Ա������
				if (staffDao.ifExistById(addStaffId)) {
//					����û��黹û�и�Ա��
					if (!meetingDao.ifStaffExistById(addStaffId, updateId) ) {

//						���Ա�� 
						 Meeting meeting = meetingDao.addStaff(updateId, addStaffId);
						 meetingDao.addMeeting(new Meeting(meeting.getMeetingId(), meeting.getMeetingName(), meeting.getMeetingRoomId(), addStaffId, meeting.getStartTime(), meeting.getEndTime()));
						System.out.println("Ϊ����" + updateId + "���Ա��" + addStaffId + "�ɹ�");
						
						System.out.println();
						meetingManage();
						
						
////						���֮��ѯ���Ƿ�Ҫ�������
//						while (true) {
//			//				ѭ��ѯ���Ƿ�Ҫ�������Ա��
//							System.out.print("1����  2����\n�Ƿ�Ҫ�������Ա����");
//							int chioce21 = input.nextInt();
//							if (chioce21 == 1) {
//								System.out.print("����������ҪΪ���λ������Ա����id:");
//								int addStaffId2 = input.nextInt();
//								
//								if (staffDao.ifExistById(addStaffId2)) {
////																	���Ա��
//									meetingDao.addStaff(updateId, addStaffId2);
//								} else {
//									System.out.println("�����ڸ�Ա��");
//									break;
//								}
//							} else {
//								System.out.println("���Ա���ɹ���Ŀǰ����" + updateId + "��Ϣ����:");
//			//						������֮��Ѹû�����Ϣͨ������id�г���
//								for (int i = 0; i < meetingDao.selectById(updateId).size(); i++) {
//									System.out.println(meetingDao.selectById(updateId).get(i));
//								}
//								System.out.println();
//								return;
//							}
//						}
						
						System.out.println();
					} else {
						System.out.println("��Ա���ѲμӸû���\n");
						updateMeetingStaff();
					}

					
				} else {
					System.out.println("�����ڸ�Ա��������������\n");
					updateMeetingStaff();
				}
				
			} else if (chioce == 2) {
				
//				ѡ��ɾ����ʱ����ж�һ����������Ƿ���Ա����ɾ��
				if (!meetingDao.ifCanBeDelete(updateId)) {
					System.out.println("�û����Ѿ�û��Ա����,����б�Ĳ�����");
					meetingManage();
				}
				
				System.out.print("��������Ҫɾ����Ա����id:");
				int deleteStaffId = input.nextInt();
		
	//			Ҫͨ��Ա��id�ͻ���id�ж��Ƿ�������Ա��
				if (meetingDao.ifStaffExistById(deleteStaffId, updateId)) {
	
	//				ɾ��
					meetingDao.deleteStaff(updateId, deleteStaffId);
					System.out.println("Ϊ����" + updateId + "ɾ��Ա��" + deleteStaffId + "�ɹ�\n");
					meetingManage();
					
	//				�жϸû����Ա�����Ƿ������ ��������� ����ѯ�� ������ʾ
					
					
	//				ѯ���Ƿ�Ҫɾ��
									
				} else {
					System.out.println("�����ڸ�Ա��������������\n");
					updateMeetingStaff();
				}
				
			} else {
				System.out.println("û�и�ѡ��");
				meetingManage();
			}
			
			

		}
		
		
	}
	
	
}
