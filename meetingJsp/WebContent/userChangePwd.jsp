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
                    修改密码
                </div>
                <form action="changePwdServlet">
                
                <% 
                int res = 0;
                if (request.getAttribute("res") != null) {
                	res = Integer.parseInt(request.getAttribute("res").toString());
                }
                int empId = 0;
                if (request.getAttribute("empId") != null) {
                	empId = Integer.parseInt(request.getAttribute("empId").toString());
                }
                %>
                
                <input type="text" style="display:none;"  name="empId" value="<%= empId %>"/>
                <input type="text" style="display:none;"  name="flag" value="user"/>
                    <fieldset>
                        <legend>修改密码信息</legend>
                        <table class="formtable" style="width:50%">
                            <tr>
                                <td>原密码:</td>
                                <td>
                                    <input id="origin" type="password" name="oldPwd"/>
                                    <% if (res == 2) { %>
                                    
                                    <font color="red" size="12">旧密码输入有误</font>
                                    
                                    <% } %>
                                </td>
                            </tr>
                            <tr>
                                <td>新密码:</td>
                                <td>
                                    <input id="new" type="password" name="newPwd" />
                                    <% if (res == 1) { %>
                                    <font color="red" size="12">两次密码不同</font>
                                     <% } %>
                                </td>
                            </tr>
                            <tr>
                                <td>确认新密码：</td>
                                <td>
                                    <input id="confirm" type="password" name="pwdAgain"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="command">
                                    <input type="submit" value="确认修改" class="clickbutton"/>
                                    <input type="button" value="返回" class="clickbutton" onclick="window.location.href='adminIndex.jsp';"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
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
        	<% } message = null;%>

    </body>
</html>