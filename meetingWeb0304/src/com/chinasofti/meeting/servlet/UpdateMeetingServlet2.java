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
 * Servlet implementation class updateMeetingServlet2
 */
@WebServlet("/UpdateMeetingServlet2")
public class UpdateMeetingServlet2 extends HttpServlet {
	MeetingDao mDao = new MeetingDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMeetingServlet2() {
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
		
		int mId = 0;
		if (request.getParameter("mId") != null && !request.getParameter("mId").equals("")) {
			mId = Integer.parseInt(request.getParameter("mId").toString());
		}
		int mrId = 0;
		if (request.getParameter("mrId") != null && !request.getParameter("mrId").equals("")) {
			mrId = Integer.parseInt(request.getParameter("mrId").toString());
		}		
		int mStatus = 0;
		if (request.getParameter("mStatus") != null && !request.getParameter("mStatus").equals("")) {
			mStatus = Integer.parseInt(request.getParameter("mStatus").toString());
		}
		String mName = request.getParameter("mName");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String mRemark = request.getParameter("mRemark");
		String pageNum = request.getParameter("pageNum");
		String mName2 = request.getParameter("mName2");
		String ifSearch = request.getParameter("ifSearch");
		

		String oldmName = request.getParameter("oldmName");
		String oldmrName = request.getParameter("oldmrName");
		String oldstartTime = request.getParameter("oldstartTime");
		String oldendTime = request.getParameter("oldendTime");
		String oldmRemark = request.getParameter("oldmRemark");
		String oldmStatus = request.getParameter("oldmStatus");
		

		String mId2 = request.getParameter("mId2");
		
//		这两个是修改后的员工
		String[] empIdList = request.getParameterValues("empIdList");
		String oldEmpIdList = request.getParameter("oldEmpIdList");
		
//		这个是原始的员工
		String originEmpIdList = request.getParameter("originEmpIdList");
		
		
		
		try {
			mDao.updateMeeting(mId, mName, mrId, startTime, endTime, mRemark, mStatus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
//			往会议和员工中添加信息
//			每添加一次信息让会议中的人数加一
			
			String empIdList3 = Arrays.toString(empIdList).replace("[", "");
			String empIdList4 = empIdList3.replace("]", "");
			String[] empIdList2 = empIdList4.split(",");
			
			
			
			if (empIdList2.length != 0) {
				for (int i = 0; i < empIdList2.length; i ++) {
					if (!empIdList2[i].trim().equals("")) {
						Integer eId = Integer.parseInt(empIdList2[i]);
						try {
							mDao.addMeetAndEmp(mId, eId);
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
					
				}
			}
			
			
			
			
			
//			获取的之前会议信息和现在的会议信息 都跳转到修改后的会议信息的界面

			request.setAttribute("pageNum", pageNum);
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
			request.setAttribute("mStatus", mStatus);
			
			request.setAttribute("empIdList", Arrays.toString(empIdList));
			request.setAttribute("oldEmpIdList", oldEmpIdList);
			request.setAttribute("oldmStatus", oldmStatus);
			request.setAttribute("originEmpIdList", originEmpIdList);
			
			request.setAttribute("message", "修改会议" + mName + "成功!");
			
			
			request.setAttribute("mName2", mName2);
			request.setAttribute("ifSearch", ifSearch);
			request.setAttribute("mId2", mId2);
			request.getRequestDispatcher("updateMeeting2.jsp").forward(request, response);
			
			
			
			
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
