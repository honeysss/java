package com.chinasofti.meeting.vo;

public class MeetingAllInfo {
	private int meetingId;
	private String meetingName;	//会议名
	private String location;	//会议地点
	private String type;	//会议室类型
	private int volume;		//会议室容量
	private String size;	//会议室大小
	private String uname;	//参与人员姓名
	private int staffAge;	//参与人员年龄
	private String staffTel;	//参与人员电话
	private String staffDepartment;	//参与人员部门
	private String startdate;	//会议开始时间
	private String enddate;	//会议结束时间
	public int getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getStaffAge() {
		return staffAge;
	}
	public void setStaffAge(int staffAge) {
		this.staffAge = staffAge;
	}
	public String getStaffTel() {
		return staffTel;
	}
	public void setStaffTel(String staffTel) {
		this.staffTel = staffTel;
	}
	public String getStaffDepartment() {
		return staffDepartment;
	}
	public void setStaffDepartment(String staffDepartment) {
		this.staffDepartment = staffDepartment;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public MeetingAllInfo(int meetingId, String meetingName, String location,
			String type, int volume, String size, String uname, int staffAge,
			String staffTel, String staffDepartment, String startdate,
			String enddate) {
		super();
		this.meetingId = meetingId;
		this.meetingName = meetingName;
		this.location = location;
		this.type = type;
		this.volume = volume;
		this.size = size;
		this.uname = uname;
		this.staffAge = staffAge;
		this.staffTel = staffTel;
		this.staffDepartment = staffDepartment;
		this.startdate = startdate;
		this.enddate = enddate;
	}
	public MeetingAllInfo() {
		super();
	}
	@Override
	public String toString() {
		return "MeetingAllInfo [meetingId=" + meetingId + ", meetingName="
				+ meetingName + ", location=" + location + ", type=" + type
				+ ", volume=" + volume + ", size=" + size + ", uname=" + uname
				+ ", staffAge=" + staffAge + ", staffTel=" + staffTel
				+ ", staffDepartment=" + staffDepartment + ", startdate="
				+ startdate + ", enddate=" + enddate + "]";
	}
	

	
}
