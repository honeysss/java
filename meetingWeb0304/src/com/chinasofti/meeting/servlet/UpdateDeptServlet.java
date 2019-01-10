package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.DepartmentDao;
import com.chinasofti.meeting.dao.EmployeeDao;

/**
 * Servlet implementation class updateDeptServlet
 */
@WebServlet("/UpdateDeptServlet")
public class UpdateDeptServlet extends HttpServlet {
	DepartmentDao deptDao = new DepartmentDao();
	EmployeeDao empDao = new EmployeeDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDeptServlet() {
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
		
		
		int deptId = Integer.parseInt(request.getParameter("deptId").toString());
		String deptName = (String)request.getParameter("deptName");
		String flag = (String)request.getParameter("flag");
		if (flag.equals("ɾ��")) {
			try {
//				ɾ�����ŵ�ʱ�������Ա������������� ��Ա���Ĳ���idΪ-1
//				�Ȼ�ȡ����Ա��id
				ArrayList<Integer> empIdList = deptDao.selectAllEmpId(deptId);
				for (int i = 0; i < empIdList.size(); i ++) {
					empDao.updateDeptId(empIdList.get(i));
				}
				
				deptDao.deleteDept(deptId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				request.setAttribute("message", "ɾ������" + deptName + "�ɹ�!");
				request.getRequestDispatcher("selectAllDept.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("deptId", deptId);
			request.setAttribute("deptName", deptName);
			request.getRequestDispatcher("updateDept.jsp").forward(request, response);
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
