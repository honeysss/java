package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.DepartmentDao;
import com.chinasofti.meeting.vo.Department;

/**
 * Servlet implementation class addDeptServlet
 */
@WebServlet("/AddDeptServlet")
public class AddDeptServlet extends HttpServlet {
	DepartmentDao deptDao = new DepartmentDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDeptServlet() {
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
		
		String deptName = request.getParameter("deptName");
		
		if(deptName == null || deptName.equals("")) {
			request.setAttribute("message", "�������Ʋ���Ϊ��");
			request.getRequestDispatcher("addDepartment.jsp").forward(request, response);
		} else {
//			���ж��Ƿ���ڸò���
			try {
				if (deptDao.selectDeptByName(deptName)) {
//					�������
					request.setAttribute("message", deptName + "�Ѵ���!");
					request.getRequestDispatcher("addDepartment.jsp").forward(request, response);
				} else {
					try {
						deptDao.addDept(deptName);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						request.setAttribute("message", "��Ӳ���" + deptName + "�ɹ�!");
						request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
