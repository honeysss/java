package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.DepartmentDao;

/**
 * Servlet implementation class modifyDeptServlet
 */
@WebServlet("/ModifyDeptServlet")
public class ModifyDeptServlet extends HttpServlet {
	DepartmentDao deptDao = new DepartmentDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyDeptServlet() {
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
		
		try {
			if(deptDao.selectDeptByName(deptName)) {
				request.setAttribute("deptName", deptName);
				request.setAttribute("deptId", deptId);
				request.setAttribute("message", "部门" + deptName + "已存在");
				request.getRequestDispatcher("updateDept.jsp").forward(request, response);
			
			} else {
				try {
					deptDao.updateDept(deptId, deptName);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					request.setAttribute("message", "修改部门" + deptName + "成功!");
					request.getRequestDispatcher("selectAllDept.jsp").forward(request, response);
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
