package com.chinasofti.meeting.vo;

public class MeetingRoom {
	private int mid;
	private String type;
	private String location;
	private int volume;
	private String size;
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public MeetingRoom(int mid, String type, String location, int volume,
			String size) {
		super();
		this.mid = mid;
		this.type = type;
		this.location = location;
		this.volume = volume;
		this.size = size;
	}
	public MeetingRoom() {
		super();
	}
	@Override
	public String toString() {
		return "MeetingRoom [mid=" + mid + ", type=" + type + ", location="
				+ location + ", volume=" + volume + ", size=" + size + "]";
	}
	
	
	
	
}
