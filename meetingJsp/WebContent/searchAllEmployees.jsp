<%@page import="com.chinasofti.meeting.dao.DepartmentDao"%>
<%@page import="javax.script.ScriptContext"%>
<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.chinasofti.meeting.dao.EmployeeDao, com.chinasofti.meeting.vo.Employee"%>
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
                int empId = 0;
                	if (request.getAttribute("empId") != null) {
                		empId = Integer.parseInt(request.getAttribute("empId").toString());
                	}
                	
                %>
                
                <form action="updatePwdOrQuit" style="display:inline-block;">
                <input type="text" style="display:none;" value="<%= empId %>" name="empId"/>
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
                    <div class="sidebar-grouptitle">员工管理</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="addEmployee.jsp">增加员工</a></li>
                        <li class="sidebar-menuitem"><a href="searchAllEmployees.jsp">查看所有员工</a></li>
                        <li class="sidebar-menuitem"><a href="searchOneEmployee.jsp">查找员工</a></li>
                    </ul>
                </div>
                <div class="sidebar-menugroup">
                    <div class="sidebar-grouptitle">部门管理</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="addDepartment.jsp">增加部门</a></li>
                        <li class="sidebar-menuitem"><a href="selectAllDept.jsp">查看所有部门</a></li>
                    </ul>
                </div>
                <div class="sidebar-menugroup">
                    <div class="sidebar-grouptitle">会议室管理</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="addMeetingRoom.jsp">添加会议室</a></li>
                        <li class="sidebar-menuitem"><a href="selectAllMeetingRooms.jsp">查看会议室</a></li>
                    </ul>
                </div>
                <div class="sidebar-menugroup">
                    <div class="sidebar-grouptitle">会议管理</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="addMeeting.jsp">增加会议</a></li>
                        <li class="sidebar-menuitem"><a href="selectAllMeetings.jsp">查看所有会议</a></li>
                        <li class="sidebar-menuitem"><a href="searchmeetings.jsp">查找会议</a></li>
                    </ul>
                </div>
            </div>
            <div class="page-content">
                <div class="content-nav">
                    员工管理 > 查看所有员工
                </div>
				<table class="listtable">
				<caption>所有员工:</caption>
                    <tr class="listheader">
                        <th>姓名</th>                     
                        <th>联系电话</th>
                        <th>电子邮件</th>
                        <th>部门名称</th>
                        <th>账号名</th>
                        <th>角色</th>
                        <th>操作</th>
                    </tr>
                    <%  EmployeeDao empDao = new EmployeeDao();
                    	ArrayList<Employee> employeeList = new ArrayList<Employee>();
                    	employeeList = empDao.selectAllUser();
                    %>
                    
                    <%for (int i = 0; i < employeeList.size(); i ++) { %>
                    <tr>
                        <td><%=employeeList.get(i).getEmpName() %></td>                 
                        <td><%=employeeList.get(i).getEmpTel() %></td>
                        <td><%=employeeList.get(i).getEmpEmail() %></td>
                        <td>
                        
                        <% 
                        DepartmentDao deptDao = new DepartmentDao();
                        
                        String deptNme = deptDao.selectDeptName(employeeList.get(i).getEmpDeptId());
                        %>
                        <%= deptNme %>
                        </td>
                        <td><%=employeeList.get(i).getUsername() %></td>
                        
                        <td>
                        <% if(employeeList.get(i).getUserRole() == 0) { %>
                        普通用户
                        <% } else { %>
                        管理员
                        <% } %>
                        </td>
                        <td>
                        <form action="updateEmpServlet" style="display:inline-block;">
                        	<input type="text" style="display:none;" name="flag" value="update" />
                        	<input type="text" style="display:none;" name="empId" value="<%= employeeList.get(i).getEmpId() %>" />
                        	<input type="text" style="display:none;" name="empName" value="<%= employeeList.get(i).getEmpName() %>" />
                        	<input type="text" style="display:none;" name="empTel" value="<%= employeeList.get(i).getEmpTel() %>" />
                        	<input type="text" style="display:none;" name="empEmail" value="<%= employeeList.get(i).getEmpEmail() %>" />
                        	<input type="text" style="display:none;" name="empDeptId" value="<%= employeeList.get(i).getEmpDeptId() %>" />
                        	<input type="text" style="display:none;" name="username" value="<%= employeeList.get(i).getUsername() %>" />
                        	<input type="text" style="display:none;" name="userRole" value="<%= employeeList.get(i).getUserRole() %>" />
                        	<input type="submit" value="修改" />
                        </form>
                        
                        <form action="updateEmpServlet" style="display:inline-block;">
                        	<input type="text" style="display:none;" name="flag" value="delete"/>
                        	<input type="text" style="display:none;" name="empId" value="<%= employeeList.get(i).getEmpId() %>"  />
                        	<input type="text" style="display:none;" name="empName" value="<%= employeeList.get(i).getEmpName() %>" />
                        	<input type="text" style="display:none;" name="username" value="<%= session.getAttribute("username") %>" />
                        	<input type="submit" value="删除" />
                        </form>
                        </td>
                    </tr>
                    <%} %>
                    
                </table>
            </div>
        </div>
        <div class="page-footer">
            <hr/>
            更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
            <img src="images/footer.png" alt="CoolMeeting"/>
        </div>
        
        <% String message = (String)request.getAttribute("message");  %>
        	<% if (message != null && message.equals("您注销了账号")) {%>
	        <script>
	        
				alert("<%= message %>");
				window.location.href="loginDefault.jsp";
	        </script>
        	<% } else if (message != null && !message.equals("您注销了账号")) { %>
	        <script>
        		alert("<%= message %>");
	        </script>
        	<% }
        	message = null;
        	%>
    </body>
</html>