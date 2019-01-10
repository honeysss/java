package com.chinasofti.meeting.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chinasofti.meeting.vo.Department;
import com.mysql.jdbc.PreparedStatement;
//
public class DepartmentDao extends Conn {
//	增加部门
	public void addDept(String deptName) throws SQLException {
		String sql = "insert into department values(null, ?)";
		PreparedStatement ps = (PreparedStatement)getConnection().prepareStatement(sql);
		ps.setString(1, deptName);
		ps.executeUpdate();
	}
	
//	删除部门
	public void deleteDept(int deptId) throws SQLException {
		String sql = "delete from department where deptId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, deptId);
		ps.executeUpdate();
	}
	
//	修改部门
	public void updateDept(int deptId, String deptName) throws SQLException {
		String sql = "update department set deptName = ? where deptId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, deptName);
		ps.setInt(2, deptId);
		ps.executeUpdate();
	}

//	查看部门
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
	
//	通过部门id查询部门名称
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
	
	
//	通过部门名称查询部门是否存在
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
	
//	通过部门id查询该部门所有员工的id
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
	
//	查看第一个部门的id
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
