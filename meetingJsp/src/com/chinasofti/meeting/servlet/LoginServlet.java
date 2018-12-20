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
		
//		得到用户名和密码
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");

		
		int res = 0;

		request.setAttribute("username", username);
		
		EmployeeDao empDao = new EmployeeDao();
		
//		空用户名 1  空密码2  用户名错误3  密码错误4
		
		if (!username.equals("")) {
			if (!pwd.equals("")) {
//				用方法判断是否存在该用户名
				try {
					if (employeeDao.selectByName(username) != null) {
//						判断密码是否相等
						if (employeeDao.selectByName(username).getUserPwd().equals(pwd)) {
//							获得用户id
							int empId = empDao.getEmpId(username);
							request.setAttribute("empId", empId);
							request.setAttribute("username", username);
//						这里要判断一下是管理员还是普通用户
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
