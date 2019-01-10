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
                    员工管理 > 查看所有员工
                </div>
				<table class="listtable">
				<caption>所有员工:</caption>
                    <tr class="listheader">
                        <th>员工ID</th>       
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
                        <td><%=employeeList.get(i).getEmpId() %></td>   
                        <td>
                        <%if(employeeList.get(i).getEmpName() != null && !employeeList.get(i).getEmpName().equals("null") && !employeeList.get(i).getEmpName().equals("")
                        ) { %>
                        <%=employeeList.get(i).getEmpName() %>
                        <% } else { %>
                        <font color="red" size="12">无</font>
                        <% } %>
                        </td>                 
                        <td>
                        <%if(employeeList.get(i).getEmpTel() != null && !employeeList.get(i).getEmpTel().equals("null") && !employeeList.get(i).getEmpTel().equals("")) { %>
                        <%=employeeList.get(i).getEmpTel() %>
                        <% } else { %>
                        <font color="red" size="12">无</font>
                        <% } %>
                        </td>
                        <td>
                        <%if(employeeList.get(i).getEmpEmail() != null && !employeeList.get(i).getEmpEmail().equals("null") && !employeeList.get(i).getEmpEmail().equals("")) { %>
                        <%=employeeList.get(i).getEmpEmail() %>
                        <% } else { %>
                        <font color="red" size="12">无</font>
                        <% } %>
                        </td>
                        <td>
                        
                        <%if(employeeList.get(i).getEmpDeptId() != -1) { %>
                        <% 
                        DepartmentDao deptDao = new DepartmentDao();
                        
                        String deptName = deptDao.selectDeptName(employeeList.get(i).getEmpDeptId());
                        %>
                        <%= deptName %>
                        <% } else { %>
                        <font color="red" size="12">无</font>
                        <% } %>
                        
                        </td>
                        <td>
                        <%if(employeeList.get(i).getUsername() != null && !employeeList.get(i).getUsername().equals("null") && !employeeList.get(i).getUsername().equals("")) { %>
                        <%=employeeList.get(i).getUsername() %>
                        <% } else { %>
                        <font color="red" size="12">无</font>
                        <% } %>
                        </td>
                        
                        <td>
                        <% if(employeeList.get(i).getUserRole() == 0) { %>
                        普通用户
                        <% } else { %>
                        管理员
                        <% } %>
                        </td>
                        
                        
                        <td>
                        <form action="UpdateEmpServlet" style="display:inline-block;">
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
                        
                        <form action="UpdateEmpServlet" style="display:inline-block;">
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
				window.location.href="login.jsp";
	        </script>
	        
	        <% } else if (message != null && message.equals("您修改了自己的权限，请重新登录")) { %>
        	
	        <script>
        		alert("<%= message %>");
				window.location.href="login.jsp";
	        </script>
	        
	        
        	<% } else if (message != null && !message.equals("您注销了账号")  && !message.equals("您修改了自己的权限，请重新登录")) { %>
        	
	        <script>
        		alert("<%= message %>");
	        </script>
	        
        	<% }
        	message = null;
        	%>
    </body>
</html>