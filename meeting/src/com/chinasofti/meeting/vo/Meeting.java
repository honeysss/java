package com.chinasofti.meeting.vo;

public class Meeting {
//	����id  ʱ��  
//	������id ͨ��������id�ͻ������������� ���Եõ��˴λ����ַ��������Ϣ
//	Ա��id Ա����Ϣ ������Ϣ
	private int meetingId;
	private String meetingName;
	private int meetingRoomId;
	private int staffId;
	private String startTime;
	private String endTime;
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
	public int getMeetingRoomId() {
		return meetingRoomId;
	}
	public void setMeetingRoomId(int meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Meeting(int meetingId, String meetingName, int meetingRoomId,
			int staffId, String startTime, String endTime) {
		super();
		this.meetingId = meetingId;
		this.meetingName = meetingName;
		this.meetingRoomId = meetingRoomId;
		this.staffId = staffId;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public Meeting() {
		super();
	}
	@Override
	public String toString() {
		return "Meeting [meetingId=" + meetingId + ", meetingName="
				+ meetingName + ", meetingRoomId=" + meetingRoomId
				+ ", staffId=" + staffId + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}
	
	
	
}
