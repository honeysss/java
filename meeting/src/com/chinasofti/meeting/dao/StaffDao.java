package com.chinasofti.meeting.dao;

import java.util.ArrayList;

import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.Staff;

public class StaffDao {
//	查询所有员工信息
	public ArrayList<Staff> selectAll() {
		return Init.staffList;
	}
	
//	通过id判断是否存在该员工
	public boolean ifExistById(int id) {
		boolean flag = false;
		
		for (int i = 0; i < Init.staffList.size(); i++) {
			if (id == Init.staffList.get(i).getStaffId()) {
				flag = true;
			}
		}
		
		return flag;
	}
	
//	增加员工
	public void addStaff(Staff staff) {
		Init.staffList.add(staff);
		System.out.println("添加员工" + staff.getStaffName() + "成功");
	}
	
	
//	删除员工
	public boolean deleteStaff(int id) {
		boolean flag = false;
		for (int i = 0; i < Init.staffList.size(); i++) {
			if (id == Init.staffList.get(i).getStaffId()) {
				flag = true;
				Init.staffList.remove(i);
				System.out.println("删除员工" + id + "成功");
			}
		}
		return flag;
	}
	
//	修改员工
	public boolean updateStaff(int id, Staff staff) {
		boolean flag = false;
		for (int i = 0; i < Init.staffList.size(); i++) {
			if (id == Init.staffList.get(i).getStaffId()) {
				flag = true;
				Init.staffList.set(i, staff);
				System.out.println("更新员工" + id + "成功");
			}
		}
		return flag;
	}
	
//	根据员工的部门id输出员工的部门名称
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
	
	
//	通过用户名查看用户的信息
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
