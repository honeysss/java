package com.chinasofti.meeting.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class changeDeptIdServlet
 */
@WebServlet("/ChangeDeptIdServlet")
public class ChangeDeptIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeDeptIdServlet() {
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
		
		String deptId = request.getParameter("deptId");
		String empIdList = request.getParameter("empIdList");
		String mName = request.getParameter("mName");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String mrId = request.getParameter("mrId");
		String mRemark = request.getParameter("mRemark");
		
		request.setAttribute("deptId", deptId);
		request.setAttribute("empIdList", empIdList);
		request.setAttribute("mName", mName);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("mrId", mrId);
		request.setAttribute("mRemark", mRemark);
		request.getRequestDispatcher("addMeeting.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
