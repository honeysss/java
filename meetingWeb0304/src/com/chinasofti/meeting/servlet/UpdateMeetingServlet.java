package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.MeetingDao;

/**
 * Servlet implementation class updateMeetingServlet
 */
@WebServlet("/UpdateMeetingServlet")
public class UpdateMeetingServlet extends HttpServlet {
	MeetingDao mDao = new MeetingDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMeetingServlet() {
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
		String pageNum = (String)request.getParameter("pageNum");
		String flag = (String)request.getParameter("flag");
		String ifSearch = request.getParameter("ifSearch");
		request.setAttribute("pageNum", pageNum);
		String mStatus = request.getParameter("mStatus");
		request.setAttribute("mStatus", mStatus);

		
//		�жϵ�������ĸ�
		if (flag.equals("�޸�")) {
			String mName = request.getParameter("mName");
			String mrName = request.getParameter("mrName");
			String mNum = request.getParameter("mNum");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String mRemark = request.getParameter("mRemark");
			String[] originEmpIdList = request.getParameterValues("originEmpIdList");
			request.setAttribute("mId", mId);
			request.setAttribute("mName", mName);
			request.setAttribute("mrName", mrName);
			request.setAttribute("mNum", mNum);
			request.setAttribute("startTime", startTime);
			request.setAttribute("endTime", endTime);
			request.setAttribute("mRemark", mRemark);
			request.setAttribute("originEmpIdList", Arrays.toString(originEmpIdList));
			
			

			if(ifSearch != null  && !ifSearch.equals("null")) {
				String mId2 = request.getParameter("mId2");
				request.setAttribute("mId2", mId2);
				String mName2 = request.getParameter("mName2");
				request.setAttribute("mName2", mName2);
				request.setAttribute("ifSearch", ifSearch);
				request.getRequestDispatcher("updateMeeting.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("updateMeeting.jsp").forward(request, response);
			}
			
		} else if (flag.equals("ȡ��")) {
			String mName = request.getParameter("mName");
			request.setAttribute("pageNum", pageNum);
			try {
				mDao.cancelMeeting(mId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				

				if (ifSearch != null  && !ifSearch.equals("null")) {
					String mId2 = request.getParameter("mId2");
					request.setAttribute("mId2", mId2);
					String mName2 = request.getParameter("mName2");
					request.setAttribute("mName", mName2);
					request.setAttribute("message", "ȡ������" + mName + "�ɹ�!");
					request.getRequestDispatcher("searchmeetings.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "ȡ������" + mName + "�ɹ�!");
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
			

			String mName2 = request.getParameter("mName2");
			request.setAttribute("mName2", mName2);
			request.setAttribute("ifSearch", ifSearch);
			String mId2 = request.getParameter("mId2");
			request.setAttribute("mId2", mId2);
			
			
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
