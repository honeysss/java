package com.chinasofti.meeting.dao;

import java.util.ArrayList;

import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.Department;

public class DepartmentDao {
//	���Ӳ���
	public void add(Department department) {
		Init.departmentList.add(department);
		System.out.println("��Ӳ���" + department.getDepartmentName() + "�ɹ�");
	}
	
//	ɾ������
	public boolean delete(int id) {
		boolean flag = false;
		for (int i = 0; i < Init.departmentList.size(); i++) {
			if (id == Init.departmentList.get(i).getDepartmentId()) {
				flag = true;
				Init.departmentList.remove(i);
				System.out.println("ɾ������" + id + "�ɹ�");
			}
		}
		return flag;
	}
	
//	�޸Ĳ���
	public void update(int id, Department department) {
		for (int i = 0; i < Init.departmentList.size(); i++) {
			if (id == Init.departmentList.get(i).getDepartmentId()) {
				Init.departmentList.set(i, department);
				System.out.println("�޸Ĳ���" + id + "�ɹ�");
			}
		}
	}
	
//	�鿴����
	public ArrayList<Department> selectAll() {
		return Init.departmentList;
	}
	
//	ͨ������id�鿴�Ƿ���ڸò���
	public boolean ifExistDept(int id) {
		boolean flag = false;
		for (int i = 0; i < Init.departmentList.size(); i++) {
			if (id == Init.departmentList.get(i).getDepartmentId()) {
				flag = true;
			}
		}
		return flag;
	}
	
//	ͨ���������Ʋ鿴�Ƿ���ڸò���
	public boolean ifExistDeptName(String name) {
		boolean flag = false;
		for (int i = 0; i < Init.departmentList.size(); i++) {
			if (name.equals(Init.departmentList.get(i).getDepartmentName())) {
				flag = true;
			}
		}
		return flag;
	}
	
}
