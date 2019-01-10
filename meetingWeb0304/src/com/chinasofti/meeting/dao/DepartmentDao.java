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
		PreparedStatement ps = (PreparedStatement)getConnection().prepareStatement(sql);
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
	
	
//	ͨ���������Ʋ�ѯ�����Ƿ����
	public boolean selectDeptByName(String deptName) throws SQLException {
		boolean flag = false;
		String sql = "select * from department where deptName = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, deptName);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			flag = true;
		}
		getConnection().close();
		rs.close();
		return flag;
	}
	
//	ͨ������id��ѯ�ò�������Ա����id
	public ArrayList<Integer> selectAllEmpId(int deptId) throws SQLException {
		ArrayList<Integer> empIdlIst = new ArrayList<Integer>();
		String sql = "select empId from employee, department where department.deptId = employee.empDeptId and department.deptId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, deptId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			empIdlIst.add(rs.getInt(1));
		}
		getConnection().close();
		rs.close();
		
		return empIdlIst;
	}
	
//	�鿴��һ�����ŵ�id
	public int selectFirstDeptId() throws SQLException {
		int deptId = 0;
		String sql = "select deptId from department order by deptId asc limit 0, 1";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			deptId = rs.getInt(1);
		}
		getConnection().close();
		rs.close();
		return deptId;
	}
	
	
	
}
