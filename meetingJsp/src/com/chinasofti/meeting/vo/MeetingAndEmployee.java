package com.chinasofti.meeting.vo;

public class MeetingAndEmployee {
//	会议id 员工id
	private int mId;
	private int eId;
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public int geteId() {
		return eId;
	}
	public void seteId(int eId) {
		this.eId = eId;
	}
	public MeetingAndEmployee(int mId, int eId) {
		super();
		this.mId = mId;
		this.eId = eId;
	}
	public MeetingAndEmployee() {
		super();
	}
	@Override
	public String toString() {
		return "MeetingAndEmployee [mId=" + mId + ", eId=" + eId + "]";
	}
	
}
