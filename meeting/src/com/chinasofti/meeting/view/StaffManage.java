package com.chinasofti.meeting.view;

import java.util.Scanner;

import com.chinasofti.meeting.dao.DepartmentDao;
import com.chinasofti.meeting.dao.StaffDao;
import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.Department;
import com.chinasofti.meeting.vo.Staff;

public class StaffManage {
	Scanner input = new Scanner(System.in);
	StaffDao staffDao = new StaffDao();
	DepartmentDao departmentDao = new DepartmentDao();
	
	public void staffManage () {
		while(true) {
			System.out.println("1������Ա��\t2��ɾ��Ա��\t3���޸�Ա��\t4���鿴Ա��\t5��������һ��");
			System.out.print("��������Ҫ���еĲ���:");
			int chioce2 = input.nextInt();
			switch(chioce2) {
				case 1:
					addStaff();
					break;
				case 2:
					deleteStaff();
					break;
				case 3:
					updateStaff();
					break;
				case 4:
					selectStaff();
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
	
//	����
	public void addStaff() {
		int addStaffId;
		if (Init.staffList.size() != 0) {
			addStaffId = Init.staffList.get(Init.staffList.size() - 1).getStaffId() + 1;
		} else {
			addStaffId = 1;
		}
		
		System.out.print("��������Ҫ��ӵ�Ա������:");
		String addStaffName = input.next();
		System.out.print("��������Ҫ��ӵ�Ա������:");
		int addStaffAge = input.nextInt();
		System.out.print("��������Ҫ��ӵ�Ա���绰:");
		String addStaffTel = input.next();
		
//		���벿����Ϣ
		if (departmentDao.selectAll().size() != 0) {
			System.out.println("Ŀǰ������Ϣ����:");
			for (int i = 0; i < departmentDao.selectAll().size(); i++) {
				System.out.println(departmentDao.selectAll().get(i));
			}
			System.out.print("��������Ҫ��ӵ�Ա����������id:");
			int addStaffDeptId = input.nextInt();
			
//			�ж��Ƿ���ڸò���
			if (!departmentDao.ifExistDept(addStaffDeptId)) {
				System.out.println("�����ڸò��ţ�������ѡ��");
				addStaff();
			} else {
				staffDao.addStaff(new Staff(addStaffId, addStaffName, addStaffAge, addStaffTel, addStaffDeptId));
			}
			
		} else {
			System.out.println("��û�в�����Ϣ����ȥ��Ӳ��Ű�");	
			staffDao.addStaff(new Staff(addStaffId, addStaffName, addStaffAge, addStaffTel, -1));
		}
		
	}
	
//	ɾ��
	public void deleteStaff() {
		if (staffDao.selectAll().size() != 0) {
			System.out.println("ĿǰԱ����Ϣ����:");
			for (int i = 0; i < staffDao.selectAll().size(); i++) {
				System.out.println(staffDao.selectAll().get(i));
			}
			
			System.out.print("��������Ҫɾ�����û���id:");
			int deleteStaffId = input.nextInt();
			
			if (!staffDao.deleteStaff(deleteStaffId)) {
				System.out.println("�����ڸ��û�������������");
				deleteStaff();
			}
			
		} else {
			System.out.println("��û��Ա����Ϣ����ȥ���Ա����");
			staffManage();
		}
	}
	
	
//	�޸�
	public void updateStaff() {
		if (staffDao.selectAll().size() != 0) {
			System.out.println("ĿǰԱ����Ϣ����:");
			for (int i = 0; i < staffDao.selectAll().size(); i++) {
				System.out.println(staffDao.selectAll().get(i));
			}
			
			System.out.print("��������Ҫ�޸ĵ��û���id:");
			int updateStaffId = input.nextInt();
			
			if (!staffDao.ifExistById(updateStaffId)) {
				System.out.println("�����ڸ�id������������:");
				updateStaff();
			} else {
				System.out.print("�������޸ĺ��Ա������:");
				String updateStaffName = input.next();
				System.out.print("�������޸ĺ��Ա������:");
				int updateStaffAge = input.nextInt();
				System.out.print("�������޸ĺ��Ա���绰:");
				String updateStaffTel = input.next();
				
//				���벿����Ϣ
				if (departmentDao.selectAll().size() != 0) {
					System.out.println("Ŀǰ������Ϣ����:");
					for (int i = 0; i < departmentDao.selectAll().size(); i++) {
						System.out.println(departmentDao.selectAll().get(i));
					}
					System.out.print("�������޸ĺ��Ա����������id:");
					int updateStaffDeptId = input.nextInt();
					
//					�жϲ����Ƿ����
					if (!departmentDao.ifExistDept(updateStaffDeptId)) {
						System.out.println("�����ڸò��ţ�����������");
						updateStaff();
					} else {
						staffDao.updateStaff(updateStaffId, new Staff(updateStaffId, updateStaffName, updateStaffAge, updateStaffTel, updateStaffDeptId));
					}
					
				} else {
					System.out.println("��û�в�����Ϣ����ȥ��Ӳ��Ű�");	
					staffManage();
				}
				
			}
			
		} else {
			System.out.println("��û��Ա����Ϣ����ȥ���Ա����");
			staffManage();
		}
	}
	
	
//	�鿴
	public void selectStaff() {
		if (staffDao.selectAll().size() != 0) {
			for (int i = 0; i < staffDao.selectAll().size(); i++) {
				System.out.println(staffDao.selectAll().get(i));
			}
		} else {
			System.out.println("��û��Ա����Ϣ����ȥ���Ա����");
			staffManage();
		}
	}
	
	
}
