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
import com.chinasofti.meeting.dao.MeetingRoomDao;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * Servlet implementation class addMeetingServlet
 */
@WebServlet("/AddMeetingServlet")
public class AddMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMeetingServlet() {
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
		
		MeetingRoomDao mrDao = new MeetingRoomDao();
		
		String mName = request.getParameter("mName");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		int mrId = Integer.parseInt(request.getParameter("mrId"));
		String mRemark = request.getParameter("mRemark");
		String[] empIdList = (request.getParameter("empIdList")).split(",");
		
		

		
		
		
		
		int mrCapacity = 0;
		try {
			mrCapacity = mrDao.selectMrCapacityById(mrId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int addCapacity = empIdList.length;
		
		
		
		if (mName == null || mName.equals("") ||
				startTime == null || startTime.equals("") ||
						endTime == null || endTime.equals("") ||
								mRemark == null || mRemark.equals("") ) {

			String empIdList11 = request.getParameter("empIdList");
			request.setAttribute("empIdList", empIdList11);
			request.setAttribute("message", "�����ֶζ�Ϊ����");
			request.getRequestDispatcher("addMeeting.jsp").forward(request, response);

//			���ж�һ�»��������Ƿ���ڻ��������� ���������ʾ��Ա ������ӻ������
		} else if (addCapacity > mrCapacity) {
			request.setAttribute("message", "������Ա�����û�����������������ѡ��");
			request.getRequestDispatcher("addMeeting.jsp").forward(request, response);
		} else {
//			��ӻ���
			MeetingDao mDao = new MeetingDao();
			try {
				mDao.addMeeting(mName, mrId, startTime, endTime, mRemark);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			�������Ա���������Ϣ
//			ÿ���һ����Ϣ�û����е�������һ
			int mId = 0;
			try {
				mId = mDao.newMeetingId();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			ѭ���õ���Ա��id
			for (int i = 0; i < empIdList.length; i ++) {
				try {
					Integer eId = Integer.parseInt(empIdList[i].trim());
//					�������Ա������ѭ���������id��Ա��id
					mDao.addMeetAndEmp(mId, eId);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
//					ÿ����һ�� �øû����������һ
					mDao.addOneEmp(mId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			request.setAttribute("message", "��ӻ���" + mName + "�ɹ�!");
			request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
			
			
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
