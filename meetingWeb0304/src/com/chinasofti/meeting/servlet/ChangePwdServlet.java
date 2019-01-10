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
 * Servlet implementation class changePwdServlet
 */
@WebServlet("/ChangePwdServlet")
public class ChangePwdServlet extends HttpServlet {
	EmployeeDao empDao = new EmployeeDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePwdServlet() {
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

		
		String oldPwd = request.getParameter("oldPwd");
		String pwd1 = request.getParameter("newPwd");
		String pwd2 = request.getParameter("pwdAgain");
		String flag = request.getParameter("flag");
		
		int res = 0;
		
		
//		1 �������벻���  2����������
//		��������������
		if (pwd1.equals(pwd2)) {
//			ͨ���û�id�����û����� 
			String pwd = null;
			try {
				pwd = empDao.getUserPwd(empId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
//			�����������ȷ
			if (oldPwd.equals(pwd)) {
				
//				�޸�����
				try {
					empDao.updatePwd(empId, pwd1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("message", "�����޸ĳɹ�");
				if (flag != null) {
					request.getRequestDispatcher("userIndex.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
				}
				
			} else {
				res = 2;
				request.setAttribute("res", res);
				if (flag != null) {
					request.getRequestDispatcher("userChangePwd.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("changePassword.jsp").forward(request, response);
				}
			}
			
		} else {
			res = 1;
			request.setAttribute("res", res);if (flag != null) {
				request.getRequestDispatcher("userChangePwd.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("changePassword.jsp").forward(request, response);
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
