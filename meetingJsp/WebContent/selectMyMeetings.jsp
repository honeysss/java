<%@page import="com.chinasofti.meeting.dao.DepartmentDao"%>
<%@page import="com.chinasofti.meeting.vo.Meeting"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.chinasofti.meeting.dao.MeetingDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>527会议管理系统</title>
        <link rel="stylesheet" href="styles/common.css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
         <style>
   .update {
    	color: gray;
    	border:none;
    	padding: 3px;
    	cursor: pointer;
    	outline: none;
    }
    .page-header .header-title {
    width:35% !important;
    margin-left: 13%;
    }
    </style>
    </head>
    <body>
        <div class="page-header">
            <div class="header-banner">
                <img src="images/header.png" alt="CoolMeeting"/>
            </div>
            <div class="header-title">
                欢迎访问527会议管理系统
            </div>
            <div class="header-quicklink">
                
                欢迎您,dear <strong>
                <%= session.getAttribute("username") %>
               </strong>
                
                <% 
                int empId2 = 0;
                	if (request.getAttribute("empId") != null) {
                		empId2 = Integer.parseInt(request.getAttribute("empId").toString());
                	}
                	
                %>
                
                <form action="updatePwdOrQuit" style="display:inline-block;">
                <input type="text" style="display:none;" value="<%= empId2 %>" name="empId"/>
                <input type="text" style="display:none;" value="user" name="flag"/>
                <input class="update" type="submit" value="修改密码" />
                <input class="update" type="button" value="退出" 
                onclick="quit()" />
                
                <script>
                	function quit() {
                        var con = confirm('您确定要退出吗?');
                        console.log(con);
                        if (con) {
                        	window.location.href = 'login.jsp';
                        }
                	}
                </script>
                
                </form>
            </div>
        </div>
        
        
        <div class="page-body">
            <div class="page-sidebar">
                <div class="sidebar-menugroup">
                    <div class="sidebar-grouptitle">我的信息</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="selectMyInfo.jsp">查看我的信息</a></li>
                        <li class="sidebar-menuitem"><a href="completeMyInfo.jsp">完善资料</a></li>
                        <li class="sidebar-menuitem"><a href="changeUsername.jsp">修改用户名</a></li>
                        <li class="sidebar-menuitem"><a href="selectMyMeetings.jsp">查看我的会议</a></li>
                    </ul>
                </div>
            </div>
            <div class="page-content">
                <div class="content-nav">
                    我的信息 > 我的会议
                </div>
                
                <% 
                 		MeetingDao mDao = new MeetingDao();
                 	ArrayList<Meeting> mList = mDao.selectMeetingByEmpId(Integer.parseInt(session.getAttribute("empId").toString()));
                 	if (mList.size() != 0) {
                 		
                 		
                 	%>
                
                <table class="listtable">
                    <caption>我的会议：</caption>
                    <tr class="listheader">
                        <th>会议名称</th>
                        <th>会议室名称</th>
                        <th>会议开始时间</th>
                        <th>会议结束时间</th>
                        <th>会议状态</th>
                        <th>操作</th>
                    </tr>
                 	
                 	
                 	<%
                 	DepartmentDao deptDao = new DepartmentDao();
                 		for(int i = 0; i < mList.size(); i++) {
                 			
                 	
                 	%>
                 	
                 	
                    <tr>
                        <td><%= mList.get(i).getmName() %></td>
                        <td><%= deptDao.selectDeptName(mList.get(i).getMrId()) %></td>
                        <td><%= mList.get(i).getStartTime() %></td>
                        <td><%= mList.get(i).getEndTime() %></td>
                        <td>
                        <% if (mList.get(i).getmStatus() == 0) { %>
                        正常
                        <% } else { %>
                        已取消
                        <% } %>
                        </td>
                        <td>
                            <a class="clickbutton" href="">查看</a>
                            <a class="clickbutton" 
                            href="cancleMyMeetingServlet?empId=<%=session.getAttribute("empId")%>&mId=<%= mList.get(i).getmId() %>&mName=<%= mList.get(i).getmName() %>">请假</a>
                        </td>
                    </tr>
                
                <% } %>
                
                </table>
                
                <% }  else {
                	%>
                	<font color="red" size="12">您还没有参加任何会议</font>
                	
                	<%	
                }
                %>
                
            </div>
        </div>
        <div class="page-footer">
            <hr/>
            更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
            <img src="images/footer.png" alt="CoolMeeting"/>
        </div>
        
          <% String message = (String)request.getAttribute("message"); %>
        	<% if (message != null) { %>
	        <script>
	        		alert("<%= message %>");
	        </script>
        	<% } message = null; %>
    </body>
</html>