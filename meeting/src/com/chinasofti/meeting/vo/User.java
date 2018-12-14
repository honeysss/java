package com.chinasofti.meeting.vo;

public class User {
	private int uid;
	private String uname;
	private String pwd;
	private int role;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public User(int uid, String uname, String pwd, int role) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.pwd = pwd;
		this.role = role;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		String userRole = role == 0 ? "普通用户" : "管理员";
		return "id:" + uid + "\t姓名:" + uname +  "\t身份:" + userRole;
	}
	
	
}
