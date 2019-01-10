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
 * Servlet implementation class changeUsernameServlet
 */
@WebServlet("/ChangeUsernameServlet")
public class ChangeUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUsernameServlet() {
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
		int empId = Integer.parseInt(request.getParameter("empId"));
		
//		�޸��û���
		EmployeeDao empDao = new EmployeeDao();
		
		
//		�ж����Ƿ���ڸ��û�
		try {
			if (empDao.selectByName(username) == null) {
				try {
					empDao.updateUsername(empId, username);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					request.setAttribute("username", username);
					request.setAttribute("message", "�޸��û����ɹ�!");
					request.getRequestDispatcher("userIndex.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("message", "���û����Ѵ���");
				request.getRequestDispatcher("changeUsername.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
