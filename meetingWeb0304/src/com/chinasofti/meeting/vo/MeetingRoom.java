package com.chinasofti.meeting.vo;

public class MeetingRoom {
//	������id ���������ƺ� ����������  ���������� ������״̬ �����ұ�ע 
	private int mrId;
	private int mrNum;
	private String mrName;
	private int mrCapacity;
	private int mrStatus;	//0ͣ�� 1����
	private String mrRemark;
	public int getMrId() {
		return mrId;
	}
	public void setMrId(int mrId) {
		this.mrId = mrId;
	}
	public int getMrNum() {
		return mrNum;
	}
	public void setMrNum(int mrNum) {
		this.mrNum = mrNum;
	}
	public String getMrName() {
		return mrName;
	}
	public void setMrName(String mrName) {
		this.mrName = mrName;
	}
	public int getMrCapacity() {
		return mrCapacity;
	}
	public void setMrCapacity(int mrCapacity) {
		this.mrCapacity = mrCapacity;
	}
	public int getMrStatus() {
		return mrStatus;
	}
	public void setMrStatus(int mrStatus) {
		this.mrStatus = mrStatus;
	}
	public String getMrRemark() {
		return mrRemark;
	}
	public void setMrRemark(String mrRemark) {
		this.mrRemark = mrRemark;
	}
	public MeetingRoom(int mrId, int mrNum, String mrName, int mrCapacity, int mrStatus, String mrRemark) {
		super();
		this.mrId = mrId;
		this.mrNum = mrNum;
		this.mrName = mrName;
		this.mrCapacity = mrCapacity;
		this.mrStatus = mrStatus;
		this.mrRemark = mrRemark;
	}
	public MeetingRoom() {
		super();
	}
	@Override
	public String toString() {
		return "MeetingRoom [mrId=" + mrId + ", mrNum=" + mrNum + ", mrName=" + mrName + ", mrCapacity=" + mrCapacity
				+ ", mrStatus=" + mrStatus + ", mrRemark=" + mrRemark + "]";
	}
	
}
