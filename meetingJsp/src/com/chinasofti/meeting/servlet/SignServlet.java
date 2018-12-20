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
 * Servlet implementation class SignServlet
 */
@WebServlet("/SignServlet")
public class SignServlet extends HttpServlet {
	EmployeeDao employee = new EmployeeDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignServlet() {
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
		String pwd2 = request.getParameter("pwd2");

		
		int res = 0;

		request.setAttribute("username", username);
		
		
//		空用户名 1  空密码2  空确认密码3   密码不相等4 用户名已存在5
		
		if (!username.equals("")) {
			if (!pwd.equals("")) {
				if (!pwd2.equals("")) {
					if (pwd.equals(pwd2)) {
						
//						密码也相等的情况下判断是否已经存在该用户
						EmployeeDao empDao = new EmployeeDao();
						try {
							if (empDao.selectByName(username) == null) {

//								在这里执行插入数据
								try {
									employee.addEmployee(username, pwd);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								request.getRequestDispatcher("userIndex.jsp").forward(request, response);
							} else {
								res = 5;
								request.setAttribute("res", res);
								request.getRequestDispatcher("userRegister.jsp").forward(request, response);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					} else {
						res = 4;
						request.setAttribute("res", res);
						request.getRequestDispatcher("userRegister.jsp").forward(request, response);
					} 
				} else {
					res = 3;
					request.setAttribute("res", res);
					request.getRequestDispatcher("userRegister.jsp").forward(request, response);
				}
				
			} else {
				res = 2;
				request.setAttribute("res", res);
				request.getRequestDispatcher("userRegister.jsp").forward(request, response);
			}
		} else {
			res = 1;
			request.setAttribute("res", res);
			request.getRequestDispatcher("userRegister.jsp").forward(request, response);
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
