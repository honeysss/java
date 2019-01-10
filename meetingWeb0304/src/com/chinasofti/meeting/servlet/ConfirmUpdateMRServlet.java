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
 * Servlet implementation class confirmUpdateMRServlet
 */
@WebServlet("/ConfirmUpdateMRServlet")
public class ConfirmUpdateMRServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmUpdateMRServlet() {
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
	
		int mrId = Integer.parseInt(request.getParameter("mrId"));
		int mrNum = Integer.parseInt(request.getParameter("mrNum"));
		String mrName = request.getParameter("mrName");
		int mrCapacity = Integer.parseInt(request.getParameter("mrCapacity"));
		int mrStatus = Integer.parseInt(request.getParameter("mrStatus"));
		String mrRemark = request.getParameter("mrRemark");
		

		String oldMrName = request.getParameter("oldMrName");
		
		MeetingRoomDao mrDao  = new MeetingRoomDao();
		
//		������λ������������ ֱ���޸Ļ��� ������λ��������Ʋ���ȵ�����»���������ȵĻ���������˵���ͱ�Ļ����������ظ���
		if (oldMrName.equals(mrName)) {
//			�޸Ļ���
			try {
				mrDao.updateMr(mrId, mrNum, mrName, mrCapacity, mrStatus, mrRemark);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				request.setAttribute("message", "�޸Ļ�����" + mrName + "�ɹ�!");
				request.getRequestDispatcher("selectAllMeetingRooms.jsp").forward(request, response);
			}
		} else {
			try {
				if (mrDao.selectMrByName(mrName)) {
					request.setAttribute("mrId", mrId);
					request.setAttribute("mrNum", mrNum);
					request.setAttribute("mrName", oldMrName);
					request.setAttribute("mrCapacity", mrCapacity);
					request.setAttribute("mrRemark", mrRemark);
					request.setAttribute("mrStatus", mrStatus);
					request.setAttribute("message", "������" + mrName + "�Ѵ���");
					request.getRequestDispatcher("updateMeetingRoom.jsp").forward(request, response);
				
				} else {
					
//					�޸Ļ���
					try {
						mrDao.updateMr(mrId, mrNum, mrName, mrCapacity, mrStatus, mrRemark);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						request.setAttribute("message", "�޸Ļ�����" + mrName + "�ɹ�!");
						request.getRequestDispatcher("selectAllMeetingRooms.jsp").forward(request, response);
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
