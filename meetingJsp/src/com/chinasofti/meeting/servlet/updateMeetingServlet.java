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
 * Servlet implementation class updateMeetingServlet
 */
@WebServlet("/updateMeetingServlet")
public class updateMeetingServlet extends HttpServlet {
	MeetingDao mDao = new MeetingDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateMeetingServlet() {
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
		String flag = (String)request.getParameter("flag");
//		判断点击的是哪个
		if (flag.equals("修改")) {
			String mName = request.getParameter("mName");
			String mrName = request.getParameter("mrName");
			String mNum = request.getParameter("mNum");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String mRemark = request.getParameter("mRemark");
			request.setAttribute("mId", mId);
			request.setAttribute("mName", mName);
			request.setAttribute("mrName", mrName);
			request.setAttribute("mNum", mNum);
			request.setAttribute("startTime", startTime);
			request.setAttribute("endTime", endTime);
			request.setAttribute("mRemark", mRemark);
			
			

			String ifSearch = request.getParameter("ifSearch");
			if(ifSearch != null) {
				String mName2 = request.getParameter("mName2");
				request.setAttribute("mName2", mName2);
				request.setAttribute("ifSearch", ifSearch);
				request.getRequestDispatcher("updateMeeting.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("updateMeeting.jsp").forward(request, response);
			}
			
		} else if (flag.equals("取消")) {
			String mName = request.getParameter("mName");
			String pageNum = request.getParameter("pageNum");
			request.setAttribute("pageNum", pageNum);
			try {
				mDao.cancelMeeting(mId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				

				String ifSearch = request.getParameter("ifSearch");
				if (ifSearch != null) {
					String mName2 = request.getParameter("mName2");
					request.setAttribute("mName", mName2);
					request.setAttribute("message", "取消会议" + mName + "成功!");
					request.getRequestDispatcher("searchmeetings.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "取消会议" + mName + "成功!");
					request.getRequestDispatcher("selectAllMeetings.jsp").forward(request, response);
				}
				
			}
		} else {
			String mName = request.getParameter("mName");
			String mrName = request.getParameter("mrName");
			String mNum = request.getParameter("mNum");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String mRemark = request.getParameter("mRemark");
			request.setAttribute("mId", mId);
			request.setAttribute("mName", mName);
			request.setAttribute("mrName", mrName);
			request.setAttribute("mNum", mNum);
			request.setAttribute("startTime", startTime);
			request.setAttribute("endTime", endTime);
			request.setAttribute("mRemark", mRemark);
			request.getRequestDispatcher("selectOneMeeting.jsp").forward(request, response);
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
