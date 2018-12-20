package com.chinasofti.meeting.dao;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chinasofti.meeting.vo.Department;
import com.chinasofti.meeting.vo.Employee;
import com.chinasofti.meeting.vo.Meeting;
import com.chinasofti.meeting.vo.MeetingRoom;
import com.mysql.jdbc.PreparedStatement;

public class MeetingDao extends Conn {

//	��ѯ��������Ϣ
	public ArrayList<Meeting> selectAllMeeting(int pageNum, int showNum) throws SQLException {
		ArrayList<Meeting> mList = new ArrayList<Meeting>();
		String sql = "select * from meeting limit ?, ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, (pageNum-1)*5);
		ps.setInt(2, 5);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Meeting meeting = new Meeting();
			meeting.setmId(rs.getInt("mId"));
			meeting.setmName(rs.getString("mName"));
			meeting.setMrId(rs.getInt("mrId"));
			meeting.setmNum(rs.getInt("mNum"));
			meeting.setStartTime(rs.getString("startTime"));
			meeting.setmDescribe(rs.getString("mDescribe"));
			meeting.setEndTime(rs.getString("endTime"));
			meeting.setmStatus(rs.getInt("mStatus"));
			mList.add(meeting);
		}
		getConnection().close();
		rs.close();
		
		return mList;
	}
	
	
//	��ѯ�ж���������
	public int meetingNum() throws SQLException {
		int count = 0;
		String sql = "select count(*) from meeting";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			count = rs.getInt(1);
		}
		getConnection().close();
		rs.close();
		
		return count;
	}
	
	
//	ȡ������ 
	public void cancelMeeting(int mId) throws SQLException {
		String sql = "update meeting set mStatus = 1 where mId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, mId);
		ps.executeUpdate();
	}
	
	
//	ͨ������id��ѯ��������Ա��
	public ArrayList<Employee> selectEmpByMId(int mId) throws SQLException {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		String sql = "select empId, empName, empTel, empEmail from meetingAndEmployee, employee where meetingAndEmployee.eId = employee.empId and meetingAndEmployee.mId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, mId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Employee employee = new Employee();
			employee.setEmpId(rs.getInt("empId"));
			employee.setEmpName(rs.getString("empName"));
			employee.setEmpTel(rs.getString("empTel"));
			employee.setEmpEmail(rs.getString("empEmail"));
			empList.add(employee);
		}
		getConnection().close();
		rs.close();
		
		return empList;
	}

//	ɾ��ĳ���������Ա��
	public void deleteEmpFromMeeting(int eId, int mId) throws SQLException {
		String sql = "delete from meetingAndEmployee where mId = ? and eId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, mId);
		ps.setInt(2, eId);
		ps.executeUpdate();
	}
	
	
//	�޸Ļ���
	public void updateMeeting(int mId, String mName, int mrId, String startTime, String endTime, String mRemark) throws SQLException {
		String sql = "update meeting set mName=?, mrId = ?, startTime = ?, endTime=?, mDescribe=? where mId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, mName);
		ps.setInt(2, mrId);
		ps.setString(3, startTime);
		ps.setString(4, endTime);
		ps.setString(5, mRemark);
		ps.setInt(6, mId);
		ps.executeUpdate();
	}
	
	
//	����������һ
	public void reduceOneEmp(int mId) throws SQLException {
		String sql = "update meeting set mNum = mNum - 1 where mId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, mId);
		ps.executeUpdate();
	}
	
//	����������һ
	public void addOneEmp(int mId) throws SQLException {
		String sql = "update meeting set mNum = mNum + 1 where mId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, mId);
		ps.executeUpdate();
	}

//	ͨ����������ģ����ѯ����
	public ArrayList<Meeting> selectMeetingBymName(String mName, int pageNum, int showNum) throws SQLException {
		ArrayList<Meeting> mList = new ArrayList<Meeting>();
		String sql = "select * from meeting where mName like ? limit ?, ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, mName);
		ps.setInt(2, (pageNum-1)*5);
		ps.setInt(3, showNum);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Meeting meeting = new Meeting();
			meeting.setmId(rs.getInt("mId"));
			meeting.setmName(rs.getString("mName"));
			meeting.setMrId(rs.getInt("mrId"));
			meeting.setmNum(rs.getInt("mNum"));
			meeting.setStartTime(rs.getString("startTime"));
			meeting.setmDescribe(rs.getString("mDescribe"));
			meeting.setEndTime(rs.getString("endTime"));
			meeting.setmStatus(rs.getInt("mStatus"));
			mList.add(meeting);
		}
		getConnection().close();
		rs.close();
		
		return mList;
	}
	
	
//	��ѯ���һ���ĸ���
	public int selectMeetingNum(String mName) throws SQLException {
		int count = 0;
		String sql = "select count(*) from meeting where mName like ? ";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, mName);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			count = rs.getInt(1);
		}
		getConnection().close();
		rs.close();
		
		return count;
	}
	
	
//	��ӻ���
	public void addMeeting(String mName, int mrId, String startTime, String endTime, String mRemark) throws SQLException {
		String sql = "insert into meeting values(null, ?, ?, 0, ?, ?, ?, 0)";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, mName);
		ps.setInt(2, mrId);
		ps.setString(3, startTime);
		ps.setString(4, endTime);
		ps.setString(5, mRemark);
		ps.executeUpdate();
	}
	
//	�õ����µĻ���Id
	public int newMeetingId() throws SQLException {
		int mId = 0;
		String sql = "select mId from meeting ORDER BY mId desc limit 0,1 ";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			mId = rs.getInt("mId");
		}
		getConnection().close();
		rs.close();
		
		return mId;
	}
	
	
//	��ӻ���id��Ա��id
	public void addMeetAndEmp(int mId, int eId) throws SQLException {
		String sql = "INSERT into meetingAndEmployee VALUES (?, ?)";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, mId);
		ps.setInt(2, eId);
		ps.executeUpdate();
	}
	
	
//	ͨ��Ա��idɾ�������Ա���е�Ա��
	public void deleteEmpAndMeet(int empId) throws SQLException {
		String sql = "delete from meetingAndEmployee where eId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ps.executeUpdate();
	}
	
//	ͨ��Ա��id��ѯԱ������
	public ArrayList<Meeting> selectMeetingByEmpId(int empId) throws SQLException {
		ArrayList<Meeting> mList = new ArrayList<Meeting>();
		String sql = "select * from meeting, meetingAndEmployee where meeting.mId = meetingAndEmployee.mId and meetingAndEmployee.eId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Meeting meeting = new Meeting();
			meeting.setmId(rs.getInt("mId"));
			meeting.setmName(rs.getString("mName"));
			meeting.setMrId(rs.getInt("mrId"));
			meeting.setmNum(rs.getInt("mNum"));
			meeting.setStartTime(rs.getString("startTime"));
			meeting.setmDescribe(rs.getString("mDescribe"));
			meeting.setEndTime(rs.getString("endTime"));
			meeting.setmStatus(rs.getInt("mStatus"));
			mList.add(meeting);
		}
		getConnection().close();
		rs.close();
		
		return mList;
	}
	
	
//	ͨ������id��Ա��idɾ��Ա������
	public void cancelEmpAndMeet(int empId, int mId) throws SQLException {
		String sql = "delete from meetingAndEmployee where mId = ? and eId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, mId);
		ps.setInt(2, empId);
		ps.executeUpdate();
	}
	
	
	
}
