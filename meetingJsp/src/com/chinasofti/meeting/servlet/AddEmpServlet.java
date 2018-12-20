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
 * Servlet implementation class AddEmployee
 */
@WebServlet("/AddEmpServlet")
public class AddEmpServlet extends HttpServlet {
	EmployeeDao employeeDao = new EmployeeDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmpServlet() {
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
		
		String empName = request.getParameter("empName");
		String username = request.getParameter("username");
		String userPwd = request.getParameter("pwd");
		String pwd2 = request.getParameter("pwd2");
		String empTel = request.getParameter("empTel");
		String empEmail = request.getParameter("empEmail");
		int empDeptId = Integer.parseInt(request.getParameter("empDeptId"));
		
		if (userPwd.equals(pwd2)) {
			
//			�ж��Ƿ���ڸ��û���
			try {
				if (employeeDao.selectByName(username) == null) {
					try {
						employeeDao.addEmployeeByAdmin(empName, username, userPwd, empTel, empEmail, empDeptId);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						request.setAttribute("message", "ע��ɹ�!");
						request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("message", "�û����Ѵ���!");
					request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		} else {
			request.setAttribute("message", "�������벻ͬ");
			request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
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
