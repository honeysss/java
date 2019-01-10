<%@page import="com.chinasofti.meeting.dao.MeetingRoomDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.chinasofti.meeting.vo.MeetingRoom"%>
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
                
                
                <form action="UpdatePwdOrQuit" style="display:inline-block;">
                <input type="text" style="display:none;" value="<%= session.getAttribute("empId") %>" name="empId"/>
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
           
            <%@include file="adminPageBar.jsp" %>
            <div class="page-content">
                <div class="content-nav">
                    会议室管理 > 查看会议室
                </div>
                <table class="listtable">
                    <caption>所有会议室:</caption>
                    <tr class="listheader">
                        <th>门牌编号</th>
                        <th>会议室名称</th>
                        <th>容纳人数</th>
                        <th>当前状态</th>
                        <th>操作</th>
                    </tr>
                    
                    <% 
                    	MeetingRoomDao mrDao = new MeetingRoomDao();
                   		ArrayList<MeetingRoom> mrList = mrDao.selectAllMr();
                   		if (mrList.size() != 0) {
                   			for (int i = 0; i < mrList.size(); i ++) {
                   	%>
                    <tr>
                        <td><%= mrList.get(i).getMrNum() %></td>
                        <td><%= mrList.get(i).getMrName() %></td>
                        <td><%= mrList.get(i).getMrCapacity() %></td>
                        <td>
                        	<% if (mrList.get(i).getMrStatus() == 1) { %>
                        	停用
                        	<% } else if (mrList.get(i).getMrStatus() == 0) { %>
                        	启用
                        	<% } %>
                        </td>
                        
                   <td>
                        <form action="UpdateMRServlet" style="display:inline-block;">
                        	<input type="text" style="display:none;" name="flag" value="select" />
                        	<input type="text" style="display:none;" name="mrId" value="<%= mrList.get(i).getMrId() %>" />
                        	<input type="text" style="display:none;" name="mrNum" value="<%= mrList.get(i).getMrNum() %>" />
                        	<input type="text" style="display:none;" name="mrName" value="<%= mrList.get(i).getMrName() %>" />
                        	<input type="text" style="display:none;" name="mrCapacity" value="<%= mrList.get(i).getMrCapacity() %>" />
                        	<input type="text" style="display:none;" name="mrStatus" value="<%= mrList.get(i).getMrStatus() %>" />
                        	<input type="text" style="display:none;" name="mrRemark" value="<%= mrList.get(i).getMrRemark() %>" />
                            <input type="submit" class="clickbutton" value="查看详情" />
                        </form>
                            
                            
                          <form action="UpdateMRServlet" style="display:inline-block;">
                        	<input type="text" style="display:none;" name="flag" value="update" />
                        	<input type="text" style="display:none;" name="mrId" value="<%= mrList.get(i).getMrId() %>" />
                        	<input type="text" style="display:none;" name="mrNum" value="<%= mrList.get(i).getMrNum() %>" />
                        	<input type="text" style="display:none;" name="mrName" value="<%= mrList.get(i).getMrName() %>" />
                        	<input type="text" style="display:none;" name="mrCapacity" value="<%= mrList.get(i).getMrCapacity() %>" />
                        	<input type="text" style="display:none;" name="mrStatus" value="<%= mrList.get(i).getMrStatus() %>" />
                        	<input type="text" style="display:none;" name="mrRemark" value="<%= mrList.get(i).getMrRemark() %>" />
                            <input type="submit" class="clickbutton" value="修改" />
                        </form>
                            
                            
                        <form action="UpdateMRServlet" style="display:inline-block;">
                        	<input type="text" style="display:none;" name="flag" value="delete" />
                        	<input type="text" style="display:none;" name="mrId" value="<%= mrList.get(i).getMrId() %>" />
                        	<input type="text" style="display:none;" name="mrNum" value="<%= mrList.get(i).getMrNum() %>" />
                        	<input type="text" style="display:none;" name="mrName" value="<%= mrList.get(i).getMrName() %>" />
                        	<input type="text" style="display:none;" name="mrCapacity" value="<%= mrList.get(i).getMrCapacity() %>" />
                        	<input type="text" style="display:none;" name="mrStatus" value="<%= mrList.get(i).getMrStatus() %>" />
                        	<input type="text" style="display:none;" name="mrRemark" value="<%= mrList.get(i).getMrRemark() %>" />
                            <input type="submit" class="clickbutton" value="删除" />
                        </form>
                            </td>
                            
                            
                    </tr>
                   				
                   	<%
                   			}
                   		}
                    %>
                   
                </table>
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