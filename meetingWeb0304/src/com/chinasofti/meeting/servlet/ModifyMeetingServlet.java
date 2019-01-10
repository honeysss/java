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
 * Servlet implementation class modifyMeetingServlet
 */
@WebServlet("/ModifyMeetingServlet")
public class ModifyMeetingServlet extends HttpServlet {
	MeetingDao mDao = new MeetingDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyMeetingServlet() {
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
		
		int mId = Integer.parseInt(request.getParameter("mId").toString());
		int eId = Integer.parseInt(request.getParameter("eId").toString());
		String originEmpIdList = request.getParameter("originEmpIdList");
		String pageNum = request.getParameter("pageNum");
		String mName2 = request.getParameter("mName2");
		String ifSearch = request.getParameter("ifSearch");
		
		try {
//			会议人数减一
			mDao.reduceOneEmp(mId);
//			会议和员工的删除
			mDao.cancelEmpAndMeet(eId, mId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			String mName = request.getParameter("mName");
			String mrName = request.getParameter("mrName");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String mRemark = request.getParameter("mRemark");
			String mId2 = request.getParameter("mId2");
			request.setAttribute("mId2", mId2);
			request.setAttribute("mId", mId);
			request.setAttribute("mName", mName);
			request.setAttribute("mrName", mrName);
			request.setAttribute("startTime", startTime);
			request.setAttribute("endTime", endTime);
			request.setAttribute("mRemark", mRemark);
			request.setAttribute("originEmpIdList", originEmpIdList);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("mName2", mName2);
			request.setAttribute("ifSearch", ifSearch);
			request.getRequestDispatcher("updateMeeting.jsp").forward(request, response);
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
