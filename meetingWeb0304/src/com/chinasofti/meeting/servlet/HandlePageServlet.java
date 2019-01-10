package com.chinasofti.meeting.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class handlePageServlet
 */
@WebServlet("/HandlePageServlet")
public class HandlePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandlePageServlet() {
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
		
		String pageNum = request.getParameter("pageNum");
		String flag = request.getParameter("flag");
		request.setAttribute("pageNum", pageNum);
		if (flag != null) {
			String mId2 = request.getParameter("mId2");
			String mName = request.getParameter("mName");
			request.setAttribute("mName", mName);
			request.setAttribute("mId2", mId2);
			request.getRequestDispatcher("searchmeetings.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("selectAllMeetings.jsp").forward(request, response);
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
