package com.chinasofti.meeting.vo;

public class Meeting {
//	����id ��������  ������id Ԥ�Ʋ�������  ��ʼʱ��  ����ʱ��  ����˵��  ����״̬Ĭ��Ϊδȡ��1ȡ��0
	private int mId;
	private String mName;
	private int mrId;
	private int mNum;
	private String startTime;
	private String endTime;
	private String mDescribe;
	private int mStatus;
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public int getMrId() {
		return mrId;
	}
	public void setMrId(int mrId) {
		this.mrId = mrId;
	}
	public int getmNum() {
		return mNum;
	}
	public void setmNum(int mNum) {
		this.mNum = mNum;
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
	public String getmDescribe() {
		return mDescribe;
	}
	public void setmDescribe(String mDescribe) {
		this.mDescribe = mDescribe;
	}
	public int getmStatus() {
		return mStatus;
	}
	public void setmStatus(int mStatus) {
		this.mStatus = mStatus;
	}
	public Meeting(int mId, String mName, int mrId, int mNum, String startTime, String endTime, String mDescribe,
			int mStatus) {
		super();
		this.mId = mId;
		this.mName = mName;
		this.mrId = mrId;
		this.mNum = mNum;
		this.startTime = startTime;
		this.endTime = endTime;
		this.mDescribe = mDescribe;
		this.mStatus = mStatus;
	}
	public Meeting() {
		super();
	}
	@Override
	public String toString() {
		return "Meeting [mId=" + mId + ", mName=" + mName + ", mrId=" + mrId + ", mNum=" + mNum + ", startTime="
				+ startTime + ", endTime=" + endTime + ", mDescribe=" + mDescribe + ", mStatus=" + mStatus + "]";
	}
	
	
}
