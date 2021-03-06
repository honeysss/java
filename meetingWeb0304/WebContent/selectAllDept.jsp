<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.chinasofti.meeting.dao.DepartmentDao, 
    com.chinasofti.meeting.vo.Department, java.util.*"%>
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
                    部门管理  > 查看所有部门
                </div>
                <table class="listtable">
                    <caption>所有部门:</caption>
                    <tr class="listheader">
                        <th>部门编号</th>
                        <th>部门名称</th>
                        <th>操作</th>
                    </tr>
                    <%
                    	DepartmentDao deptDao = new DepartmentDao();
            			ArrayList<Department> deptList = deptDao.selectAllDept();
            			if (deptList.size() != 0) {
            				for (int i = 0; i < deptList.size(); i ++) {
            		%>	
            				
                    <tr>
                        <td id="deptId"><%= deptList.get(i).getDeptId() %></td>
                        <td id="deptName"><%= deptList.get(i).getDeptName() %></td>
                        <td>
                        <form action="UpdateDeptServlet" style="display:inline-block;">
                        	<input type="text" style="display:none;" 
                        	value="<%= deptList.get(i).getDeptId() %>"
                        	name="deptId"/>
                        	<input type="text" style="display:none;" 
                        	value="<%= deptList.get(i).getDeptName() %>"
                        	name="deptName"/>
                            <input type="submit" class="clickbutton" name="flag" value="修改"/>
                        </form>
                        <form action="UpdateDeptServlet" style="display:inline-block;">
                        	<input type="text" style="display:none;" 
                        	value="<%= deptList.get(i).getDeptId() %>"
                        	name="deptId"/>
                        	<input type="text" style="display:none;" 
                        	value="<%= deptList.get(i).getDeptName() %>"
                        	name="deptName"/>
                            <input type="submit" class="clickbutton" name="flag" value="删除" />
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