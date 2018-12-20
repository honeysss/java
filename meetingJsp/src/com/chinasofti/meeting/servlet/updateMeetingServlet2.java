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
 * Servlet implementation class updateMeetingServlet2
 */
@WebServlet("/updateMeetingServlet2")
public class updateMeetingServlet2 extends HttpServlet {
	MeetingDao mDao = new MeetingDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateMeetingServlet2() {
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
		String mName = request.getParameter("mName");
		int mrId = Integer.parseInt(request.getParameter("mrId").toString());
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String mRemark = request.getParameter("mRemark");
		

		String oldmName = request.getParameter("oldmName");
		String oldmrName = request.getParameter("oldmrName");
		String oldstartTime = request.getParameter("oldstartTime");
		String oldendTime = request.getParameter("oldendTime");
		String oldmRemark = request.getParameter("oldmRemark");
		
		
		try {
			mDao.updateMeeting(mId, mName, mrId, startTime, endTime, mRemark);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
//			获取的之前会议信息和现在的会议信息 都跳转到修改后的会议信息的界面
			
			request.setAttribute("mName", mName);
			request.setAttribute("mrId", mrId);
			request.setAttribute("startTime", startTime);
			request.setAttribute("endTime", endTime);
			request.setAttribute("mRemark", mRemark);
			
			
			request.setAttribute("oldmName", oldmName);
			request.setAttribute("oldmrName", oldmrName);
			request.setAttribute("oldstartTime", oldstartTime);
			request.setAttribute("oldendTime", oldendTime);
			request.setAttribute("oldmRemark", oldmRemark);
			
			request.setAttribute("message", "修改会议" + mName + "成功!");
			request.getRequestDispatcher("updateMeeting2.jsp").forward(request, response);
			
//				
//			String ifSearch = (String) request.getParameter("ifSearch");
//			if (!ifSearch.equals("null")) {
//				String mName2 = (String) request.getParameter("mName2");
//				request.setAttribute("mName", mName2);
//				request.setAttribute("message", "修改会议" + mName + "成功!");
//				request.getRequestDispatcher("searchmeetings.jsp").forward(request, response);
//			} else {
//				request.setAttribute("message", "修改会议" + mName + "成功!");
//				request.getRequestDispatcher("selectAllMeetings.jsp").forward(request, response);
//			}
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
