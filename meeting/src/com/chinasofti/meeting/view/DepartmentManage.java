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
			System.out.println("1、增加部门\t2、删除部门\t3、修改部门\t4、查看部门\t5、返回上一层");
			System.out.print("请输入您要进行的操作:");
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
					System.out.println("没有该选项，请重新选择");
					break;
			}
		}
	}
	
	
//	增加
	public void addDepartment() {
		System.out.print("请输入您要添加的部门名称:");
		String addDeptName = input.next();
		
		if (departmentDao.ifExistDeptName(addDeptName)) {
			System.out.println("该部门已存在，请重新输入");
			addDepartment();
		} else {
			int addDeptId = Init.departmentList.get(Init.departmentList.size() - 1).getDepartmentId() + 1;
		
			departmentDao.add(new Department(addDeptId, addDeptName));
		}
		
		
	}
	
	
//	删除
	public void deleteaddDepartment() {
		
		if (departmentDao.selectAll().size() != 0) {
			System.out.println("目前部门信息如下:");
			for (int i = 0; i < departmentDao.selectAll().size(); i++) {
				System.out.println(departmentDao.selectAll().get(i));
			}
		} else {
			System.out.println("还没有部门信息，快去添加部门吧");
			departmentManage();
		}
		
		System.out.print("请输入您要删除的部门的id:");
		int deleteDeptId = input.nextInt();
		
		if (!departmentDao.delete(deleteDeptId)) {
			System.out.println("不存在该部门id,请重新输入");
			deleteaddDepartment();
		}
	}
	
	
	
//	修改
	public void updateDepartment() {
		
		if (departmentDao.selectAll().size() != 0) {
			System.out.println("目前部门信息如下:");
			for (int i = 0; i < departmentDao.selectAll().size(); i++) {
				System.out.println(departmentDao.selectAll().get(i));
			}
		} else {
			System.out.println("还没有部门信息，快去添加部门吧");
			departmentManage();
		}
		
		System.out.print("请输入您要修改的部门的id:");
		int updateDeptId = input.nextInt();
		
		if (departmentDao.ifExistDept(updateDeptId)) {
			System.out.print("请输入修改后的部门名称:");
			String updateDeptName = input.next();
			
			departmentDao.update(updateDeptId, new Department(updateDeptId, updateDeptName));
		} else {
			System.out.println("不存在该部门，请重新输入");
			updateDepartment();
		}
		
	}
	
	
	
//	查看
	public void selectDepartment() {
		if (departmentDao.selectAll().size() != 0) {
			for (int i = 0; i < departmentDao.selectAll().size(); i++) {
				System.out.println(departmentDao.selectAll().get(i));
			}
		} else {
			System.out.println("还没有部门信息，快去添加部门吧");
			departmentManage();
		}
		
	}
	
	
	
}
