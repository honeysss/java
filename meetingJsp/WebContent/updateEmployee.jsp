<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.chinasofti.meeting.dao.DepartmentDao, com.chinasofti.meeting.vo.Department"%>
<!DOCTYPE html>
<html>
    <head>
        <title>527会议管理系统</title>
        <link rel="stylesheet" href="styles/common.css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    </head>
    <style>
    .formtable tr td:first-child {
		width: 40%;    
    }	
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
                    员工管理 > 查看所有员工  >修改
                </div>
				<form action="updateEmpServlet2">
				
				
				<%
				int empId = Integer.parseInt(request.getAttribute("empId").toString());
				String empName = (String)request.getAttribute("empName");
				String empName2 = (String)request.getAttribute("empName2");
				String flag = (String)request.getAttribute("flag");
				String empTel = (String)request.getAttribute("empTel");
				String empEmail = (String)request.getAttribute("empEmail");
				int empDeptId = Integer.parseInt(request.getAttribute("empDeptId").toString());
				int userRole = Integer.parseInt(request.getAttribute("userRole").toString());
				
				%>
				
				
                    <fieldset>
                        <legend>员工信息</legend>
                        <table class="formtable" style="width:50%">
                        
                        <input type="text" style="display:none;" value="<%= empId %>" name="empId"/>
                        <input type="text" style="display:none;" value="<%= empName2 %>" name="empName2"/>
                        <input type="text" style="display:none;" value="<%= flag %>" name="flag"/>
                            <tr>
                                <td>姓名：</td>
                                <td>
                                    <input type="text" id="employeename" maxlength="20" name="empName"
                                    value="<%= empName %>"/>
                                </td>
                            </tr>
                            <tr>
                                <td>联系电话：</td>
                                <td>
                                    <input type="text" id="phone" maxlength="20" name="empTel"
                                     value="<%= empTel %>"/>
                                </td>
                            </tr>
                            <tr>
                                <td>电子邮件：</td>
                                <td>
                                    <input type="text" id="email" maxlength="20" name="empEmail"
                                     value="<%= empEmail %>"/>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>角色：</td>
                                <td>
                                    <input type="radio" id="role" name="userRole" value="0"
                                    <% if(userRole == 0) { %>
                                    checked="checked";
                                    <% } %>
                                    />普通用户
                                    <input type="radio" id="role" name="userRole" value="1"
                                    <% if(userRole == 1) { %>
                                    checked="checked";
                                    <% } %>
                                    />管理员
                                </td>
                            </tr>
                            <tr>
                            
                            <td>员工目前所在部门：</td>
                                    <% 
                                    	DepartmentDao deptDao = new DepartmentDao();
                                    String deptName = deptDao.selectDeptName(empDeptId);
                                    %>
                                <td>
                                	<span class="deptName"><%= deptName %></span>
                                </td>
                            </tr>
							<tr>
							<td>您要修改的员工部门：</td>
                                <td>
                                    <select name="empDeptId"> 
                                    <%
                                    	DepartmentDao departmentDao = new DepartmentDao();
                                    	ArrayList<Department> departmentList = departmentDao.selectAllDept();
                                    	if (departmentList.size() != 0) {
                                    		for (int i = 0; i < departmentList.size(); i ++) {
                                    %>   
                                     	<option value="<%= departmentList.get(i).getDeptId() %>"><%= departmentList.get(i).getDeptName() %></option>
										
									<% 
                                    		}
                                    	}
									%>
                                     </select>
                                </td>
                            </tr>
                            
                            <tr>
                                <td colspan="6" class="command">
                                    <input type="submit" class="clickbutton" value="修改"/>
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
    </body>
</html>