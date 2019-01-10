package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.MeetingRoomDao;

/**
 * Servlet implementation class updateMRServlet
 */
@WebServlet("/UpdateMRServlet")
public class UpdateMRServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMRServlet() {
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
		String flag = request.getParameter("flag");
		
		MeetingRoomDao mrDao  = new MeetingRoomDao();
		
		
		if(flag.equals("delete")) {
//			ɾ��
			try {
//				�����е���Ҫ�û����ҵĻ���id�ҳ���
				ArrayList<Integer> mIdList = mrDao.selectmIdByMrId(mrId);
//				����Щ����Ļ�����id��Ϊ1
				for (int i = 0; i < mIdList.size(); i ++) {
					mrDao.updateMrId(mIdList.get(i));
				}
				
				
				mrDao.deleteMr(mrId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				request.setAttribute("message", "ɾ��������" + mrName + "�ɹ�!");
				request.getRequestDispatcher("selectAllMeetingRooms.jsp").forward(request, response);
			}
			
		} else if(flag.equals("update")) {
//			�޸�
			request.setAttribute("mrId", mrId);
			request.setAttribute("mrNum", mrNum);
			request.setAttribute("mrName", mrName);
			request.setAttribute("mrCapacity", mrCapacity);
			request.setAttribute("mrStatus", mrStatus);
			request.setAttribute("mrRemark", mrRemark);
			request.getRequestDispatcher("updateMeetingRoom.jsp").forward(request, response);
			
			
		} else {
//			�鿴��ϸ
			request.setAttribute("mrId", mrId);
			request.setAttribute("mrNum", mrNum);
			request.setAttribute("mrName", mrName);
			request.setAttribute("mrCapacity", mrCapacity);
			request.setAttribute("mrStatus", mrStatus);
			request.setAttribute("mrRemark", mrRemark);
			request.getRequestDispatcher("selectOneMr.jsp").forward(request, response);
			
			
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
