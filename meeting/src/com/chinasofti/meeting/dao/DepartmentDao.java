package com.chinasofti.meeting.dao;

import java.util.ArrayList;

import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.Department;

public class DepartmentDao {
//	增加部门
	public void add(Department department) {
		Init.departmentList.add(department);
		System.out.println("添加部门" + department.getDepartmentName() + "成功");
	}
	
//	删除部门
	public boolean delete(int id) {
		boolean flag = false;
		for (int i = 0; i < Init.departmentList.size(); i++) {
			if (id == Init.departmentList.get(i).getDepartmentId()) {
				flag = true;
				Init.departmentList.remove(i);
				System.out.println("删除部门" + id + "成功");
			}
		}
		return flag;
	}
	
//	修改部门
	public void update(int id, Department department) {
		for (int i = 0; i < Init.departmentList.size(); i++) {
			if (id == Init.departmentList.get(i).getDepartmentId()) {
				Init.departmentList.set(i, department);
				System.out.println("修改部门" + id + "成功");
			}
		}
	}
	
//	查看部门
	public ArrayList<Department> selectAll() {
		return Init.departmentList;
	}
	
//	通过部门id查看是否存在该部门
	public boolean ifExistDept(int id) {
		boolean flag = false;
		for (int i = 0; i < Init.departmentList.size(); i++) {
			if (id == Init.departmentList.get(i).getDepartmentId()) {
				flag = true;
			}
		}
		return flag;
	}
	
//	通过部门名称查看是否存在该部门
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
