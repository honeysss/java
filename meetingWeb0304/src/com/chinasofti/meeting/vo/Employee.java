package com.chinasofti.meeting.vo;

public class Employee {
	private int empId;
	private String empName;
	private String empTel;
	private String empEmail;
	private int empDeptId;
	private String username;
	private String userPwd;
	private int userRole;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpTel() {
		return empTel;
	}
	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public int getEmpDeptId() {
		return empDeptId;
	}
	public void setEmpDeptId(int empDeptId) {
		this.empDeptId = empDeptId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	public Employee(int empId, String empName, String empTel, String empEmail, int empDeptId, String username,
			String userPwd, int userRole) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empTel = empTel;
		this.empEmail = empEmail;
		this.empDeptId = empDeptId;
		this.username = username;
		this.userPwd = userPwd;
		this.userRole = userRole;
	}
	public Employee() {
		super();
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empTel=" + empTel + ", empEmail=" + empEmail
				+ ", empDeptId=" + empDeptId + ", username=" + username + ", userPwd=" + userPwd + ", userRole="
				+ userRole + "]";
	}
	
}
