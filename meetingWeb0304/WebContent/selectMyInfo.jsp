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
                   我的信息  > 查看我的信息
                </div>
                <form>
                <%
                EmployeeDao empDao = new EmployeeDao();
                	Employee emp = empDao.selectById(Integer.parseInt(session.getAttribute("empId").toString()));
                %>
                    <fieldset>
                        <legend>我的信息</legend>
                        <table class="formtable" style="width:50%">
                            <tr>
                                <td>姓名：</td>
                                <td>
                                    <span>
                                    <% if (emp.getEmpName() != null && !emp.getEmpName().equals("")) {%>
                                    <%= emp.getEmpName() %>
                                    <% } else { %>
                                    <font color="red" size="12">您还没有完善员工姓名信息</font>
                                    <% } %>
                                    
                                    </span>
                                </td>
                            </tr>
                            <tr>
                                <td>用户名：</td>
                                <td>
                                    <span><%= emp.getUsername() %></span>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>联系电话：</td>
                                <td>
                                    <span>
                                    <% if (emp.getEmpTel() != null && !emp.getEmpTel().equals("")) {%>
                                    <%= emp.getEmpTel() %>
                                    <% } else { %>
                                    <font color="red" size="12">您还没有完善联系电话信息</font>
                                    <% } %>
									</span>
                                </td>
                            </tr>
                            <tr>
                                <td>电子邮件：</td>
                                <td>
                                    <span>
                                    <% if (emp.getEmpEmail() != null && !emp.getEmpEmail().equals("")) {%>
                                    <%= emp.getEmpEmail() %>
                                    <% } else { %>
                                    <font color="red" size="12">您还没有完善电子邮件信息</font>
                                    <% } %>
                                    </span>
                                </td>
                            </tr>
                            <tr>
							<td>所在部门：</td>
                                <td>
                                <% if (emp.getEmpDeptId() != -1) {%>
                                    
                                     <%
                                    DepartmentDao deptDao = new DepartmentDao();
                                    
                                    	String deptName = deptDao.selectDeptName(emp.getEmpDeptId());
                                    %>   
                                     
                                    <span><%= deptName %></span>	
									<% 
									%>
                                    
                                    <% } else { %>
                                    <font color="red" size="12">您还没有完善所在部门信息</font>
                                    <% } %>
                                    
                                   
                                     </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" class="command">
                                    <a class="clickbutton"  
                                    style="background-color: #f1e5e5"
                                    href="ClearUserPwdMessage?username=<%=session.getAttribute("username")%>&empId=<%=session.getAttribute("empId")%>">返回</a>
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