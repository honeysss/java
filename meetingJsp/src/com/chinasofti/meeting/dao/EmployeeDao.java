package com.chinasofti.meeting.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chinasofti.meeting.vo.Employee;
import com.mysql.jdbc.PreparedStatement;

public class EmployeeDao extends Conn {

//	查看所有员工信息
	public ArrayList<Employee> selectAllUser() throws SQLException {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		String sql = "select * from employee";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Employee employee = new Employee();
			employee.setEmpId(rs.getInt("empId"));
			employee.setEmpName(rs.getString("empName"));
			employee.setEmpTel(rs.getString("empTel"));
			employee.setEmpEmail(rs.getString("empEmail"));
			employee.setEmpDeptId(rs.getInt("empDeptId"));
			employee.setUsername(rs.getString("username"));
			employee.setUserPwd(rs.getString("userPwd"));
			employee.setUserRole(rs.getInt("userRole"));
			employeeList.add(employee);
		}
		getConnection().close();
		rs.close();
		
		return employeeList;
	}


//通过用户名查询用户
	public Employee selectByName(String username) throws SQLException {
		Employee employee = null;
		String sql = "select * from employee where username = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			employee = new Employee();
			employee.setEmpId(rs.getInt("empId"));
			employee.setEmpName(rs.getString("empName"));
			employee.setEmpTel(rs.getString("empTel"));
			employee.setEmpEmail(rs.getString("empEmail"));
			employee.setEmpDeptId(rs.getInt("empDeptId"));
			employee.setUsername(rs.getString("username"));
			employee.setUserPwd(rs.getString("userPwd"));
			employee.setUserRole(rs.getInt("userRole"));
		}
		getConnection().close();
		rs.close();
		
		
		
		return employee;
	}
	
//	注册时增加用户
	public void addEmployee(String username, String pwd) throws SQLException {
		
		String sql = "INSERT into employee(username, userPwd) VALUES (?, ?)";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, pwd);
		ps.executeUpdate();
		getConnection().close();
		
	}
	
//	管理员增加用户
	public void addEmployeeByAdmin(String empName, String username, String userPwd, String empTel, String empEmail, int empDeptId) throws SQLException {
		String sql = "INSERT into employee VALUES (null, ?, ?, ?, ?, ?, ?, 0)";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, empName);
		ps.setString(2, empTel);
		ps.setString(3, empEmail);
		ps.setInt(4, empDeptId);
		ps.setString(5, username);
		ps.setString(6, userPwd);
		ps.executeUpdate();
		getConnection().close();
	}
	
//	删除员工
	public void deleteEmp(int empId) throws SQLException {
		String sql = "delete from employee where empId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ps.executeUpdate();
		getConnection().close();
	}
	
	
//	修改员工
	public void updateEmp(int empId, String empName, String empTel, String empEmail, int empDeptId, int userRole) throws SQLException {
		String sql = "update employee set empName=?, empTel=?, empEmail=?, empDeptId=?,userRole=? where empId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, empName);
		ps.setString(2, empTel);
		ps.setString(3, empEmail);
		ps.setInt(4, empDeptId);
		ps.setInt(5, userRole);
		ps.setInt(6, empId);
		ps.executeUpdate();
		getConnection().close();
	}
	
//	模糊查找员工信息
	public ArrayList<Employee> selectEmpByName(String empName) throws SQLException {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		String sql = "select * from employee where empName like ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, empName);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Employee employee = new Employee();
			employee.setEmpId(rs.getInt("empId"));
			employee.setEmpName(rs.getString("empName"));
			employee.setEmpTel(rs.getString("empTel"));
			employee.setEmpEmail(rs.getString("empEmail"));
			employee.setEmpDeptId(rs.getInt("empDeptId"));
			employee.setUsername(rs.getString("username"));
			employee.setUserPwd(rs.getString("userPwd"));
			employee.setUserRole(rs.getInt("userRole"));
			employeeList.add(employee);
		}
		getConnection().close();
		rs.close();
		
		return employeeList;
	}
	
//	通过用户名查找用户id
	public int getEmpId(String username) throws SQLException {
		int empId = 0;
		String sql = "select empId from employee where username=?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			empId = rs.getInt("empId");
		}
		getConnection().close();
		rs.close();
		return empId;
	}

//	通过用户id查找用户密码
	public String getUserPwd(int empId) throws SQLException {
		String userPwd = null;
		String sql = "select userPwd from employee where empId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			userPwd = rs.getString("userPwd");
		}
		getConnection().close();
		rs.close();
		return userPwd;
	}
	
//	修改密码
	public void updatePwd(int empId, String pwd) throws SQLException {
		String sql = "update employee set userPwd = ? where empId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(2, empId);
		ps.setString(1, pwd);
		ps.executeUpdate();
		getConnection().close();
	}
	
//	通过用户id查找用户名
	public String getUsername(int empId) throws SQLException {
		String username = null;
		String sql = "select username from employee where empId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			username = rs.getString("username");
			System.out.println(username);
		}
		getConnection().close();
		rs.close();
		return username;
	}
	
//	完善员工信息
	public void completeInfo(int empId, String empName, String empTel, String empEmail, String username, int empDeptId) throws SQLException {
		String sql = "update employee set empName = ?, empTel=?, empEmail=?, username=?, empDeptId=? where empId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, empName);
		ps.setString(2, empTel);
		ps.setString(3, empEmail);
		ps.setString(4, username);
		ps.setInt(5, empDeptId);
		ps.setInt(6, empId);
		ps.executeUpdate();
		getConnection().close();
	}
	
//通过员工id找到员工
	public Employee selectById(int empId) throws SQLException {
		Employee employee = null;
		String sql = "select * from employee where empId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setInt(1, empId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			employee = new Employee();
			employee.setEmpId(rs.getInt("empId"));
			employee.setEmpName(rs.getString("empName"));
			employee.setEmpTel(rs.getString("empTel"));
			employee.setEmpEmail(rs.getString("empEmail"));
			employee.setEmpDeptId(rs.getInt("empDeptId"));
			employee.setUsername(rs.getString("username"));
			employee.setUserPwd(rs.getString("userPwd"));
			employee.setUserRole(rs.getInt("userRole"));
		}
		getConnection().close();
		rs.close();
		
		
		
		return employee;
	}
	
	
//	通过员工id修改用户名
	public void updateUsername(int empId, String username) throws SQLException {
		String sql = "update employee set username=? where empId = ?";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ps.setString(1, username);
		ps.setInt(2, empId);
		ps.executeUpdate();
		getConnection().close();
	}

}

