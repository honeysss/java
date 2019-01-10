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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	EmployeeDao employeeDao = new EmployeeDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
//		�õ��û���������
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");

		
		int res = 0;

		request.setAttribute("username", username);
		
		EmployeeDao empDao = new EmployeeDao();
		
//		���û��� 1  ������2  �û�������3  �������4
		
		if (!username.equals("")) {
			if (!pwd.equals("")) {
//				�÷����ж��Ƿ���ڸ��û���
				try {
					if (employeeDao.selectByName(username) != null) {
//						�ж������Ƿ����
						if (employeeDao.selectByName(username).getUserPwd().equals(pwd)) {
//							����û�id
							int empId = empDao.getEmpId(username);
							request.setAttribute("empId", empId);
							request.setAttribute("username", username);
//						����Ҫ�ж�һ���ǹ���Ա������ͨ�û�
							if (employeeDao.selectByName(username).getUserRole() == 1) {
								request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
							} else {
								
								request.getRequestDispatcher("userIndex.jsp").forward(request, response);
							}
						} else {
							res = 4;
							request.setAttribute("res", res);
							request.getRequestDispatcher("login.jsp").forward(request, response);
						}
					} else {
						res = 3;
						request.setAttribute("res", res);
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				res = 2;
				request.setAttribute("res", res);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} else {
			res = 1;
			request.setAttribute("res", res);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
		
		
		
//		response.sendRedirect("adminIndex.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
