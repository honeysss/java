package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.EmployeeDao;
import com.chinasofti.meeting.dao.MeetingDao;

/**
 * Servlet implementation class updateEmpServlet
 */
@WebServlet("/updateEmpServlet")
public class updateEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateEmpServlet() {
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
	
		String flag = request.getParameter("flag");
		MeetingDao mDao = new MeetingDao();
		
		if(flag.equals("update") || flag.equals("searchUpdate")) {
			int empId = Integer.parseInt(request.getParameter("empId"));
			String empName = request.getParameter("empName");
			String empTel = request.getParameter("empTel");
			String empEmail = request.getParameter("empEmail");
			int empDeptId = Integer.parseInt(request.getParameter("empDeptId"));
			String username = request.getParameter("username");
			int userRole = Integer.parseInt(request.getParameter("userRole"));
			
			request.setAttribute("empId", empId);
			request.setAttribute("empName", empName);
			request.setAttribute("empTel", empTel);
			request.setAttribute("empEmail", empEmail);
			request.setAttribute("empDeptId", empDeptId);
			request.setAttribute("username", username);
			request.setAttribute("userRole", userRole);
			
			if (flag.equals("update")) {
				request.getRequestDispatcher("updateEmployee.jsp").forward(request, response);
			} else {
				String empName2 = request.getParameter("empName2");
				request.setAttribute("flag", flag);
				request.setAttribute("empName2", empName2);
				request.getRequestDispatcher("updateEmployee.jsp").forward(request, response);
			}
			
			
			
			
		} else {
			
			String empName = request.getParameter("empName");
			int empId = Integer.parseInt(request.getParameter("empId"));
//			删除员工
			EmployeeDao empDao = new EmployeeDao();
//			在删除之前先判断一下是否是自己
//			先通过id找到用户名 如果这个用户名和传过来的用户名相等 说明删的是一个人
			String username = request.getParameter("username");
			String oldUsername = null;
			try {
				oldUsername = empDao.getUsername(empId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int flag2 = 0;
			
			if (oldUsername != null && oldUsername.equals(username))  {
				flag2 = 1;
			}
			
			
			try {
				empDao.deleteEmp(empId);
//				把员工删除的同时也要把会议和员工中的员工删除
				mDao.deleteEmpAndMeet(empId);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
//				删除之后判断用户名是否相等 如果相等 提示注销账号 否则正常
				if (flag.equals("delete")) {
					
					
					if (flag2 == 1) {
						
						request.setAttribute("message", "您注销了账号");
						request.getRequestDispatcher("searchAllEmployees.jsp").forward(request, response);
					
					} else {
						request.setAttribute("message", "删除员工" + empName + "成功!");
						request.getRequestDispatcher("searchAllEmployees.jsp").forward(request, response);
					
					}
					
					
					
				} else {
					if (flag2 == 1) {
						System.out.println(flag2);
						request.setAttribute("message", "您注销了账号");
						request.getRequestDispatcher("searchOneEmployee.jsp").forward(request, response);
					
					} else {
						String empName2 = request.getParameter("empName2");
						request.setAttribute("empName", empName2);
						request.setAttribute("message", "删除员工" + empName + "成功!");
						request.getRequestDispatcher("searchOneEmployee.jsp").forward(request, response);
					
					}
					
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
