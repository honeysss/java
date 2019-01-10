package com.chinasofti.meeting.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chinasofti.meeting.vo.Employee;
import com.chinasofti.meeting.vo.MeetingRoom;

public class MeetingRoomDao extends Conn {

//	��ѯ���л���
	public ArrayList<MeetingRoom> selectAllMr() throws SQLException {
		ArrayList<MeetingRoom> mrList = new ArrayList<MeetingRoom>();
		String sql = "select * from meetingRoom";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			MeetingRoom mr = new MeetingRoom();
			mr.setMrId(rs.getInt("mrId"));
			mr.setMrNum(rs.getInt("mrNum"));
			mr.setMrName(rs.getString("mrName"));
			mr.setMrCapacity(rs.getInt("mrCapacity"));
			mr.setMrStatus(rs.getInt("mrStatus"));
			mr.setMrRemark(rs.getString("mrRemark"));
			mrList.add(mr);
		}
		getConnection().close();
		rs.close();
		
		return mrList;
	}
	
//	ͨ��id��ѯ����������
	public String selectMrById(int mrId) throws SQLException {
		String sql = "select * from meetingRoom where mrId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, mrId);
		ResultSet rs = ps.executeQuery();
		String mrName = null;
		while(rs.next()) {
			mrName = rs.getString("mrName");
		}
		getConnection().close();
		rs.close();
		
		return mrName;
	}
	
//	���ӻ�����
	public void addMeetingRoom(int mrNum, String mrName, int mrCapacity, int mrStatus, String mrRemark) throws SQLException {
		String sql = "INSERT INTO meetingRoom VALUES (null, ?, ?, ?, ?, ?)";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, mrNum);
		ps.setString(2, mrName);
		ps.setInt(3, mrCapacity);
		ps.setInt(4, mrStatus);
		ps.setString(5, mrRemark);
		ps.executeUpdate();
		getConnection().close();
	}
	
//	ɾ��������
	public void deleteMr(int mrId) throws SQLException {
		String sql = "delete from meetingRoom where mrId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, mrId);
		ps.executeUpdate();
		getConnection().close();
	}
	
//	�޸Ļ�����
	public void updateMr(int mrId, int mrNum, String mrName, int mrCapacity, int mrStatus, String mrRemark) throws SQLException {
		String sql = "update meetingRoom set mrNum = ?, mrName = ?, mrCapacity=?, mrStatus=?, mrRemark=? where mrId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, mrNum);
		ps.setString(2, mrName);
		ps.setInt(3, mrCapacity);
		ps.setInt(4, mrStatus);
		ps.setString(5, mrRemark);
		ps.setInt(6, mrId);
		ps.executeUpdate();
		getConnection().close();
	}
	
//	ͨ������������ѯ�Ƿ���ڸû�����
	public boolean selectMrByName(String mrName) throws SQLException {
		boolean flag = false;
		String sql = "select * from meetingRoom where mrName = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, mrName);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			flag = true;
		}
		getConnection().close();
		rs.close();
		
		return flag;
	}
	
//	ͨ������id��ѯ��������
	public int selectMrCapacityById(int mrId) throws SQLException {
		int mrCapacity = 0;
		String sql = "select mrCapacity from meetingRoom where mrId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, mrId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			mrCapacity = rs.getInt(1);
		}
		getConnection().close();
		rs.close();
		
		return mrCapacity;
	}
	
//	ͨ��������id�ҵ���Ҫ�û����ҵ����л���id
	public ArrayList<Integer> selectmIdByMrId(int mrId) throws SQLException {
		ArrayList<Integer> mIdList = new ArrayList<Integer>();
		String sql = "select mId from meeting, meetingRoom where meeting.mrId = meetingRoom.mrId and meetingRoom.mrId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, mrId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			mIdList.add(rs.getInt(1));
		}
		getConnection().close();
		rs.close();
		
		return mIdList;
	}
	
//	�ѻ�����Ļ�����id��Ϊ-1
	public void updateMrId(int mId) throws SQLException {
		String sql = "update meeting set mrId = -1 where mId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, mId);
		ps.executeUpdate();
		getConnection().close();
	}
	

}
