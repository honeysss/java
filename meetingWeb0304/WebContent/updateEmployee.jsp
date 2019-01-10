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
                    员工管理 > 查看所有员工  >修改
                </div>
				<form action="UpdateEmpServlet2">
				
				
				<%
				String empName = (String)request.getAttribute("empName");
				String empName2 = (String)request.getAttribute("empName2");
				String flag = (String)request.getAttribute("flag");
				String empTel = (String)request.getAttribute("empTel");
				String empEmail = (String)request.getAttribute("empEmail");
				int empDeptId = Integer.parseInt(request.getAttribute("empDeptId").toString());
				int userRole = Integer.parseInt(request.getAttribute("userRole").toString());
				int empId = Integer.parseInt(request.getAttribute("empId").toString());
				int empId2 = 0;
				if (request.getAttribute("empId2") != null) {
					empId2 = Integer.parseInt(request.getAttribute("empId2").toString());
				}
				
				%>
				
				
                    <fieldset>
                        <legend>员工信息</legend>
                        <table class="formtable" style="width:50%">
                        
                        <input type="text" style="display:none;" value="<%= session.getAttribute("empId") %>" name="oldEmpId"/>
                        <input type="text" style="display:none;" value="<%= empId %>" name="empId"/>
                        <input type="text" style="display:none;" value="<%= empName2 %>" name="empName2"/>
                        <input type="text" style="display:none;" value="<%= flag %>" name="flag"/>
                        <input type="text" style="display:none;" value="<%= empId2 %>" name="empId2"/>
                            <tr>
                                <td>姓名：</td>
                                <td>
                                
                                 <%if(empName != null && !empName.equals("null")) { %>
			                        <input type="text" id="employeename" maxlength="20" name="empName"
			                                    value="<%= empName %>"/>
			                        <% } else { %>
			                        <input type="text" id="employeename" maxlength="20" name="empName"
			                                    placeholder="无"/>
			                        <% } %>
                                </td>
                            </tr>
                            <tr>
                                <td>联系电话：</td>
                                <td>
                                <%if(empTel != null && !empTel.equals("null")) { %>
			                        <input type="text" id="phone" maxlength="20" name="empTel"
                                     value="<%= empTel %>"/>
			                        <% } else { %>
			                        <input type="text" id="phone" maxlength="20" name="empTel"
                                     placeholder="无"/>
			                        <% } %>
                                
                                    
                                </td>
                            </tr>
                            <tr>
                                <td>电子邮件：</td>
                                <td>
                                
                                <%if(empEmail != null && !empEmail.equals("null")) { %>
			                        <input type="text" id="email" maxlength="20" name="empEmail"
                                     value="<%= empEmail %>"/>
			                        <% } else { %>
			                        <input type="text" id="phone" maxlength="20" name="empEmail"
                                     placeholder="无"/>
			                        <% } %>
			                        
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
                            
                           
							<td>员工部门：</td>
							
			                           
                                <td>
                                    <select name="empDeptId"> 
                                    <%
                                    	DepartmentDao departmentDao = new DepartmentDao();
                                    	ArrayList<Department> departmentList = departmentDao.selectAllDept();
                                    	if (departmentList.size() != 0) {
                                    		for (int i = 0; i < departmentList.size(); i ++) {
                                    %>   
                                     	<option
                                     	<% if(empDeptId == departmentList.get(i).getDeptId())  {%>
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
                                    <input type="submit" class="clickbutton" value="修改"/>
                                      <a type="button" class="clickbutton"
                                    style="background-color:white;color:black;" 
                                    href="ClearUpdateEmpServlet?flag=<%= flag %>&empName2=<%= empName2 %>&empId2=<%= empId2 %>"
                                    >返回</a>
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