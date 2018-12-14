package com.chinasofti.meeting.dao;

import java.util.ArrayList;

import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.Staff;

public class StaffDao {
//	��ѯ����Ա����Ϣ
	public ArrayList<Staff> selectAll() {
		return Init.staffList;
	}
	
//	ͨ��id�ж��Ƿ���ڸ�Ա��
	public boolean ifExistById(int id) {
		boolean flag = false;
		
		for (int i = 0; i < Init.staffList.size(); i++) {
			if (id == Init.staffList.get(i).getStaffId()) {
				flag = true;
			}
		}
		
		return flag;
	}
	
//	����Ա��
	public void addStaff(Staff staff) {
		Init.staffList.add(staff);
		System.out.println("���Ա��" + staff.getStaffName() + "�ɹ�");
	}
	
	
//	ɾ��Ա��
	public boolean deleteStaff(int id) {
		boolean flag = false;
		for (int i = 0; i < Init.staffList.size(); i++) {
			if (id == Init.staffList.get(i).getStaffId()) {
				flag = true;
				Init.staffList.remove(i);
				System.out.println("ɾ��Ա��" + id + "�ɹ�");
			}
		}
		return flag;
	}
	
//	�޸�Ա��
	public boolean updateStaff(int id, Staff staff) {
		boolean flag = false;
		for (int i = 0; i < Init.staffList.size(); i++) {
			if (id == Init.staffList.get(i).getStaffId()) {
				flag = true;
				Init.staffList.set(i, staff);
				System.out.println("����Ա��" + id + "�ɹ�");
			}
		}
		return flag;
	}
	
//	����Ա���Ĳ���id���Ա���Ĳ�������
	public String printDept(int id) {
		String deptName = "";
		
		for (int i = 0; i < Init.staffList.size(); i++) {
			if (id == Init.staffList.get(i).getDepartmentId()) {
				for (int j = 0; j < Init.departmentList.size(); j++) {
					if (id == Init.departmentList.get(j).getDepartmentId()) {
						deptName = Init.departmentList.get(j).getDepartmentName();
					}
				}
			}
		}
		
		return deptName;
	}
	
	
//	ͨ���û����鿴�û�����Ϣ
	public Staff selectStaff(String name) {
		Staff staff = null;
		
		for (int i = 0; i < Init.staffList.size(); i++) {
			if (name.equals(Init.staffList.get(i).getStaffName())) {
				staff = Init.staffList.get(i);
			}
		}
		
		return staff;
	}
	
	
}
