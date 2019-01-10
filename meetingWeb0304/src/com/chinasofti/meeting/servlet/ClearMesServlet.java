package com.chinasofti.meeting.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class clearMesServlet
 */
@WebServlet("/ClearMesServlet")
public class ClearMesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClearMesServlet() {
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
		
		String ifSearch = request.getParameter("ifSearch");
		String pageNum = request.getParameter("pageNum");
		request.setAttribute("message", null);
		request.setAttribute("pageNum", pageNum);
		if (!ifSearch.equals("null")) {
			String mId2 = request.getParameter("mId2");
			request.setAttribute("mId2", mId2);
			String mName = request.getParameter("mName2");
			request.setAttribute("mName", mName);
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
