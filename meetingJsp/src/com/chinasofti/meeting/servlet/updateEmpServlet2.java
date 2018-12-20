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
@WebServlet("/updateEmpServlet2")
public class updateEmpServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateEmpServlet2() {
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
		String empName = request.getParameter("empName");
		String empTel = request.getParameter("empTel");
		String empEmail = request.getParameter("empEmail");
		int empDeptId = Integer.parseInt(request.getParameter("empDeptId"));
		int userRole = Integer.parseInt(request.getParameter("userRole"));
		
//		修改员工
		EmployeeDao empDao = new EmployeeDao();
		try {
			empDao.updateEmp(empId, empName, empTel, empEmail, empDeptId, userRole);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			String flag = request.getParameter("flag");
			if (!flag.equals("null")) {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
