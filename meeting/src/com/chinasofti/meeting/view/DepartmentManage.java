package com.chinasofti.meeting.view;

import java.util.Scanner;

import com.chinasofti.meeting.dao.DepartmentDao;
import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.Department;

public class DepartmentManage {

	Scanner input = new Scanner(System.in);
	DepartmentDao departmentDao = new DepartmentDao();
	
	
	public void departmentManage() {
		while(true) {
			System.out.println("1�����Ӳ���\t2��ɾ������\t3���޸Ĳ���\t4���鿴����\t5��������һ��");
			System.out.print("��������Ҫ���еĲ���:");
			int chioce2 = input.nextInt();
			switch(chioce2) {
				case 1:
					addDepartment();
					break;
				case 2:
					deleteaddDepartment();
					break;
				case 3:
					updateDepartment();
					break;
				case 4:
					selectDepartment();
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
	public void addDepartment() {
		System.out.print("��������Ҫ��ӵĲ�������:");
		String addDeptName = input.next();
		
		if (departmentDao.ifExistDeptName(addDeptName)) {
			System.out.println("�ò����Ѵ��ڣ�����������");
			addDepartment();
		} else {
			int addDeptId = Init.departmentList.get(Init.departmentList.size() - 1).getDepartmentId() + 1;
		
			departmentDao.add(new Department(addDeptId, addDeptName));
		}
		
		
	}
	
	
//	ɾ��
	public void deleteaddDepartment() {
		
		if (departmentDao.selectAll().size() != 0) {
			System.out.println("Ŀǰ������Ϣ����:");
			for (int i = 0; i < departmentDao.selectAll().size(); i++) {
				System.out.println(departmentDao.selectAll().get(i));
			}
		} else {
			System.out.println("��û�в�����Ϣ����ȥ��Ӳ��Ű�");
			departmentManage();
		}
		
		System.out.print("��������Ҫɾ���Ĳ��ŵ�id:");
		int deleteDeptId = input.nextInt();
		
		if (!departmentDao.delete(deleteDeptId)) {
			System.out.println("�����ڸò���id,����������");
			deleteaddDepartment();
		}
	}
	
	
	
//	�޸�
	public void updateDepartment() {
		
		if (departmentDao.selectAll().size() != 0) {
			System.out.println("Ŀǰ������Ϣ����:");
			for (int i = 0; i < departmentDao.selectAll().size(); i++) {
				System.out.println(departmentDao.selectAll().get(i));
			}
		} else {
			System.out.println("��û�в�����Ϣ����ȥ��Ӳ��Ű�");
			departmentManage();
		}
		
		System.out.print("��������Ҫ�޸ĵĲ��ŵ�id:");
		int updateDeptId = input.nextInt();
		
		if (departmentDao.ifExistDept(updateDeptId)) {
			System.out.print("�������޸ĺ�Ĳ�������:");
			String updateDeptName = input.next();
			
			departmentDao.update(updateDeptId, new Department(updateDeptId, updateDeptName));
		} else {
			System.out.println("�����ڸò��ţ�����������");
			updateDepartment();
		}
		
	}
	
	
	
//	�鿴
	public void selectDepartment() {
		if (departmentDao.selectAll().size() != 0) {
			for (int i = 0; i < departmentDao.selectAll().size(); i++) {
				System.out.println(departmentDao.selectAll().get(i));
			}
		} else {
			System.out.println("��û�в�����Ϣ����ȥ��Ӳ��Ű�");
			departmentManage();
		}
		
	}
	
	
	
}
