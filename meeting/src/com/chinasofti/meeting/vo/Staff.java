package com.chinasofti.meeting.vo;

import com.chinasofti.meeting.dao.StaffDao;

public class Staff {
	private int staffId;
	private String staffName;	//参与人员姓名
	private int age;	//参与人员年龄
	private String tel;	//参与人员电话
	private int departmentId;	//参与人员部门
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public Staff(int staffId, String staffName, int age, String tel,
			int departmentId) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.age = age;
		this.tel = tel;
		this.departmentId = departmentId;
	}
	public Staff() {
		super();
	}
	@Override
	public String toString() {
		StaffDao staffDao = new StaffDao();
		String deptName = staffDao.printDept(departmentId);
		return "Staff [staffId=" + staffId + ", staffName=" + staffName
				+ ", age=" + age + ", tel=" + tel + ", departmentId="
				+ departmentId + ", departmentName="
						+ deptName + "]";
	}
	
	
}
