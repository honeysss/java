package com.chinasofti.meeting.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chinasofti.meeting.vo.Department;
import com.mysql.jdbc.PreparedStatement;
//
public class DepartmentDao extends Conn {
//	���Ӳ���
	public void addDept(String deptName) throws SQLException {
		String sql = "insert into department values(null, ?)";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, deptName);
		ps.executeUpdate();
	}
	
//	ɾ������
	public void deleteDept(int deptId) throws SQLException {
		String sql = "delete from department where deptId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, deptId);
		ps.executeUpdate();
	}
	
//	�޸Ĳ���
	public void updateDept(int deptId, String deptName) throws SQLException {
		String sql = "update department set deptName = ? where deptId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, deptName);
		ps.setInt(2, deptId);
		ps.executeUpdate();
	}

//	�鿴����
	public ArrayList<Department> selectAllDept() throws SQLException {
		ArrayList<Department> departmentList = new ArrayList<Department>();
		String sql = "select * from department";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Department department = new Department();
			department.setDeptId(rs.getInt("deptId"));
			department.setDeptName(rs.getString("deptName"));
			departmentList.add(department);
		}
		getConnection().close();
		rs.close();
		
		return departmentList;
	}
	
//	ͨ������id��ѯ��������
	public String selectDeptName(int deptId) throws SQLException {
		String deptName = null;
		String sql = "select deptName from department where deptId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, deptId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			deptName = rs.getString("deptName");
		}
		getConnection().close();
		rs.close();
		return deptName;
	}
	
	
}
