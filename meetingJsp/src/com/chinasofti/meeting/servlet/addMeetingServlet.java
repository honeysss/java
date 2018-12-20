package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.MeetingDao;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * Servlet implementation class addMeetingServlet
 */
@WebServlet("/addMeetingServlet")
public class addMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addMeetingServlet() {
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
		
		
		String mName = request.getParameter("mName");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		int mrId = Integer.parseInt(request.getParameter("roomid"));
		String mRemark = request.getParameter("mRemark");
		String[] empIdList = request.getParameterValues("empIdList");
		
//		��ӻ���
		MeetingDao mDao = new MeetingDao();
		try {
			mDao.addMeeting(mName, mrId, startTime, endTime, mRemark);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		�������Ա���������Ϣ
//		ÿ���һ����Ϣ�û����е�������һ
		int mId = 0;
		try {
			mId = mDao.newMeetingId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String empIdList3 = Arrays.toString(empIdList).replace("[", "");
		String empIdList4 = empIdList3.replace("]", "");
		String[] empIdList2 = empIdList4.split(",");
		
		for (int i = 0; i < empIdList2.length; i ++) {
			try {
				Integer eId = Integer.parseInt(empIdList2[i]);
				mDao.addMeetAndEmp(mId, eId);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				mDao.addOneEmp(mId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.setAttribute("message", "��ӻ���" + mName + "�ɹ�!");
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
