package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.EmployeeDao;

/**
 * Servlet implementation class updateEmpServlet2
 */
@WebServlet("/UpdateEmpServlet2")
public class UpdateEmpServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmpServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());

		int empId = Integer.parseInt(request.getParameter("empId"));
		int oldEmpId = 0;
		if (request.getParameter("oldEmpId") != null) {
			oldEmpId = Integer.parseInt(request.getParameter("oldEmpId"));
		}
		String empName = request.getParameter("empName");
		String empTel = request.getParameter("empTel");
		String empEmail = request.getParameter("empEmail");
		int empDeptId = Integer.parseInt(request.getParameter("empDeptId"));
		int userRole = Integer.parseInt(request.getParameter("userRole"));
		String flag = request.getParameter("flag");
		

//		修改员工
		EmployeeDao empDao = new EmployeeDao();
//		先判断修改的员工是否是自己 并且修改了自己的权限 提示 您修改了自己的权限 请重新登录
		try {
			empDao.updateEmp(empId, empName, empTel, empEmail, empDeptId, userRole);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (empId == oldEmpId && userRole == 0) {
				if (!flag.equals("null")) {
					int empId2 = Integer.parseInt(request.getParameter("empId2"));
					request.setAttribute("empId2", empId2);
					
					request.setAttribute("message", "您修改了自己的权限，请重新登录");
					request.getRequestDispatcher("searchOneEmployee.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "您修改了自己的权限，请重新登录");
					request.getRequestDispatcher("searchAllEmployees.jsp").forward(request, response);
				}
			}  else {
				if (!flag.equals("null")) {
					int empId2 = Integer.parseInt(request.getParameter("empId2"));
					request.setAttribute("empId2", empId2);
					String empName2 = request.getParameter("empName2");
					request.setAttribute("empName", empName2);
					request.setAttribute("message", "修改员工" + empName + "成功!");
					request.getRequestDispatcher("searchOneEmployee.jsp").forward(request, response);
				
				} else {
					request.setAttribute("message", "修改员工" + empName + "成功!");
					request.getRequestDispatcher("searchAllEmployees.jsp").forward(request, response);
				
				}
			}
		}
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
