package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.MeetingRoomDao;

/**
 * Servlet implementation class addMRServlet
 */
@WebServlet("/AddMRServlet")
public class AddMRServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMRServlet() {
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
		
		String mrName = request.getParameter("mrName");
		
		int mrNum = Integer.parseInt(request.getParameter("mrNum"));
		int mrCapacity = Integer.parseInt(request.getParameter("mrCapacity"));
		int mrStatus = Integer.parseInt(request.getParameter("mrStatus"));
		String mrRemark = request.getParameter("mrRemark");

		MeetingRoomDao mrDao = new MeetingRoomDao();
//		先判断该会议室是否存在
		try {
			if (mrDao.selectMrByName(mrName)) {
				request.setAttribute("mrNum", mrNum);
				request.setAttribute("mrCapacity", mrCapacity);
				request.setAttribute("mrStatus", mrStatus);
				request.setAttribute("mrRemark", mrRemark);
				
				request.setAttribute("message", "会议室" + mrName + "已存在");
				request.getRequestDispatcher("addMeetingRoom.jsp").forward(request, response);
			} else if (request.getParameter("mrNum") == null || request.getParameter("mrNum").equals("")
					|| request.getParameter("mrCapacity") == null || request.getParameter("mrCapacity").equals("")
				|| request.getParameter("mrRemark") == null && request.getParameter("mrRemark").equals("")) {
				
				request.setAttribute("message", "所有字段都为必填");
				request.getRequestDispatcher("addMeetingRoom.jsp").forward(request, response);
			}else {
//			增加会议室
				try {
					mrDao.addMeetingRoom(mrNum, mrName, mrCapacity, mrStatus, mrRemark);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					request.setAttribute("message", "添加会议室" + mrName + "成功!");
					request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
