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
			System.out.println("1、增加员工\t2、删除员工\t3、修改员工\t4、查看员工\t5、返回上一层");
			System.out.print("请输入您要进行的操作:");
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
					System.out.println("没有该选项，请重新选择");
					break;
			}
		}
	}
	
//	增加
	public void addStaff() {
		int addStaffId;
		if (Init.staffList.size() != 0) {
			addStaffId = Init.staffList.get(Init.staffList.size() - 1).getStaffId() + 1;
		} else {
			addStaffId = 1;
		}
		
		System.out.print("请输入您要添加的员工名称:");
		String addStaffName = input.next();
		System.out.print("请输入您要添加的员工年龄:");
		int addStaffAge = input.nextInt();
		System.out.print("请输入您要添加的员工电话:");
		String addStaffTel = input.next();
		
//		输入部门信息
		if (departmentDao.selectAll().size() != 0) {
			System.out.println("目前部门信息如下:");
			for (int i = 0; i < departmentDao.selectAll().size(); i++) {
				System.out.println(departmentDao.selectAll().get(i));
			}
			System.out.print("请输入您要添加的员工所属部门id:");
			int addStaffDeptId = input.nextInt();
			
//			判断是否存在该部门
			if (!departmentDao.ifExistDept(addStaffDeptId)) {
				System.out.println("不存在该部门，请重新选择");
				addStaff();
			} else {
				staffDao.addStaff(new Staff(addStaffId, addStaffName, addStaffAge, addStaffTel, addStaffDeptId));
			}
			
		} else {
			System.out.println("还没有部门信息，快去添加部门吧");	
			staffDao.addStaff(new Staff(addStaffId, addStaffName, addStaffAge, addStaffTel, -1));
		}
		
	}
	
//	删除
	public void deleteStaff() {
		if (staffDao.selectAll().size() != 0) {
			System.out.println("目前员工信息如下:");
			for (int i = 0; i < staffDao.selectAll().size(); i++) {
				System.out.println(staffDao.selectAll().get(i));
			}
			
			System.out.print("请输入您要删除的用户的id:");
			int deleteStaffId = input.nextInt();
			
			if (!staffDao.deleteStaff(deleteStaffId)) {
				System.out.println("不存在该用户，请重新输入");
				deleteStaff();
			}
			
		} else {
			System.out.println("还没有员工信息，快去添加员工吧");
			staffManage();
		}
	}
	
	
//	修改
	public void updateStaff() {
		if (staffDao.selectAll().size() != 0) {
			System.out.println("目前员工信息如下:");
			for (int i = 0; i < staffDao.selectAll().size(); i++) {
				System.out.println(staffDao.selectAll().get(i));
			}
			
			System.out.print("请输入您要修改的用户的id:");
			int updateStaffId = input.nextInt();
			
			if (!staffDao.ifExistById(updateStaffId)) {
				System.out.println("不存在该id，请重新输入:");
				updateStaff();
			} else {
				System.out.print("请输入修改后的员工姓名:");
				String updateStaffName = input.next();
				System.out.print("请输入修改后的员工年龄:");
				int updateStaffAge = input.nextInt();
				System.out.print("请输入修改后的员工电话:");
				String updateStaffTel = input.next();
				
//				输入部门信息
				if (departmentDao.selectAll().size() != 0) {
					System.out.println("目前部门信息如下:");
					for (int i = 0; i < departmentDao.selectAll().size(); i++) {
						System.out.println(departmentDao.selectAll().get(i));
					}
					System.out.print("请输入修改后的员工所属部门id:");
					int updateStaffDeptId = input.nextInt();
					
//					判断部门是否存在
					if (!departmentDao.ifExistDept(updateStaffDeptId)) {
						System.out.println("不存在该部门，请重新输入");
						updateStaff();
					} else {
						staffDao.updateStaff(updateStaffId, new Staff(updateStaffId, updateStaffName, updateStaffAge, updateStaffTel, updateStaffDeptId));
					}
					
				} else {
					System.out.println("还没有部门信息，快去添加部门吧");	
					staffManage();
				}
				
			}
			
		} else {
			System.out.println("还没有员工信息，快去添加员工吧");
			staffManage();
		}
	}
	
	
//	查看
	public void selectStaff() {
		if (staffDao.selectAll().size() != 0) {
			for (int i = 0; i < staffDao.selectAll().size(); i++) {
				System.out.println(staffDao.selectAll().get(i));
			}
		} else {
			System.out.println("还没有员工信息，快去添加员工吧");
			staffManage();
		}
	}
	
	
}
