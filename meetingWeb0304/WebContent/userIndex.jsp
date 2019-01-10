<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>中软会议管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="stylesheet" href="styles/common.css"/>
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
                <% 
                if (request.getAttribute("username") != null) {
                session.setAttribute("username", request.getAttribute("username")); }
                if (request.getAttribute("empId") != null) {
                session.setAttribute("empId", Integer.parseInt(request.getAttribute("empId").toString())); }
                %>
                <% if( session.getAttribute("username") != null) { %>
                欢迎您,dear <strong>
                <%= session.getAttribute("username") %>
                <% } %></strong>
                
                
                
                <form action="UpdatePwdOrQuit" style="display:inline-block;">
                <input type="text" style="display:none;" value="<%= session.getAttribute("empId") %>" name="empId"/>
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
                        <li class="sidebar-menuitem"><a href="completeMyInfo.jsp">完善信息</a></li>
                        <li class="sidebar-menuitem"><a href="changeUsername.jsp">修改用户名</a></li>
                        <li class="sidebar-menuitem"><a href="selectMyMeetings.jsp">查看我的会议</a></li>
                    </ul>
                </div>
            </div>
        </div>
        
         <p 
        style="
        text-align:center;
        margin-top:10px;
        font-size:60px;
        letter-spacing:5px;
        font-family:'幼圆';
        color:gray;
        ">欢迎来到527会议管理系统</p>
        
        
        <div class="page-footer">
       
            <hr/>
            更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
            <img src="images/footer.png" alt="CoolMeeting"/>
        </div>
        
        
         <% String message = (String)request.getAttribute("message"); %>
        	<% if (message != null && !message.equals("null")) { %>
	        <script>
	        		alert("<%= message %>");
	        </script>
        	<% } message = null; %>
    </body>
</html>