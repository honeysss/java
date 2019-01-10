<%@page import="com.chinasofti.meeting.dao.EmployeeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.chinasofti.meeting.dao.*, com.chinasofti.meeting.vo.*,
    java.util.*"%>
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
           
                 <% if (request.getAttribute("username") != null) {
                	 session.setAttribute("username", request.getAttribute("username"));
                 }
                %>
                欢迎您,dear <strong>
                <%= session.getAttribute("username") %>
               </strong>
                
                
                
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
            
            
            <div class="page-content">
                <div class="content-nav">
                   我的信息 > 完善信息
                </div>
                <form action="CompleteInfoServlet">
                    <fieldset>
                        <legend>完善信息</legend>
                        <table class="formtable" style="width:50%">
                            <tr>
                           
                                    <input type="hidden" name="empId" value="<%= session.getAttribute("empId") %>"/>
                                <td>姓名：</td>
                                <td>
                                    <input type="text" id="employeename" maxlength="20" name="empName"
                                    placeholder="请输入您的真实姓名"/>
                                </td>
                            </tr>
                            <tr>
                                <td>用户名：</td>
                                <td>
                                	<input type="text" id="employeename" maxlength="20" name="username"
                                	value="<%=session.getAttribute("username")%>"
                                	placeholder="请输入您的用户名"/>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>联系电话：</td>
                                <td>
                                    <input type="text" id="phone" maxlength="20" name="empTel"
                                    placeholder="请输入您的联系电话"/>
                                </td>
                            </tr>
                            <tr>
                                <td>电子邮件：</td>
                                <td>
                                    <input type="text" id="email" maxlength="20" name="empEmail"
                                    placeholder="请输入您的电子邮件"/>
                                </td>
                            </tr>
                            <tr>
							<td>所在部门：</td>
                                <td>
                                    <select name="empDeptId"> 
                                    
                                    <%
                                    EmployeeDao empDao = new EmployeeDao();
                                    int empId = Integer.parseInt(session.getAttribute("empId").toString());
                                    int empDeptId = empDao.selectDeptIdById(empId);
                                    %>
                                    
                                    <%
                                    	DepartmentDao departmentDao = new DepartmentDao();
                                    	ArrayList<Department> departmentList = departmentDao.selectAllDept();
                                    	if (departmentList.size() != 0) {
                                    		for (int i = 0; i < departmentList.size(); i ++) {
                                    %>   
                                     	<option
                                     	
                                     	<% if (empDeptId == departmentList.get(i).getDeptId()) { %>
                                     	selected="true"
                                     	<% } %>
                                     	
                                     	 value="<%= departmentList.get(i).getDeptId() %>"><%= departmentList.get(i).getDeptName() %></option>
										
									<% 
                                    		}
                                    	}
									%>
                                     </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" class="command">
                                    <input type="submit" class="clickbutton" value="确定"/>
                                    <input type="reset" class="clickbutton" value="重置"/>
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