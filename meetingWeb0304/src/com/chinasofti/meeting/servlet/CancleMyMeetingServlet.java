package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.MeetingDao;

/**
 * Servlet implementation class cancleMyMeetingServlet
 */
@WebServlet("/CancleMyMeetingServlet")
public class CancleMyMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancleMyMeetingServlet() {
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
		
		int mId = Integer.parseInt(request.getParameter("mId"));
		int empId = Integer.parseInt(request.getParameter("empId"));
		String mName = request.getParameter("mName");
//		ɾ������
		MeetingDao mDao = new MeetingDao();
		try {
			
			mDao.cancelEmpAndMeet(empId, mId);
//			����������������һ
			mDao.reduceOneEmp(mId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", "��ٻ���" + mName + "�ɹ�");
			request.getRequestDispatcher("selectMyMeetings.jsp").forward(request, response);
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
