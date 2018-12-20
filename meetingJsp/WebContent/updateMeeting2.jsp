<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>527会议管理系统</title>
        <link rel="stylesheet" href="styles/common.css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <style type="text/css">
            #divfrom{
                float:left;
                width:200px;
            }
            #divto{
                float:left;
                width:200px;
            }
            #divoperator{
                float:left;
                width:50px;
                padding:60px 5px;
            }
            #divoperator input[type="button"]{
                margin:10px 0;
            }
            #selDepartments{
                display:block;
                width:100%;
            }
            #selEmployees{
                display:block;
                width:100%;
                height:200px;
            }
            #selSelectedEmployees{
                display:block;
                width:100%;
                height:225px;
            }
            .formtable tr td:third-child {
            width: 25%;
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
            
            <%
            String mName = (String)request.getAttribute("mName");
        	int mrId = 0;
        	if (request.getAttribute("mrId") != null) {
        		mrId = Integer.parseInt(request.getAttribute("mrId").toString());
        	}
        	String startTime = (String)request.getAttribute("startTime");
        	String endTime = (String)request.getAttribute("endTime");
        	String mRemark = (String)request.getAttribute("mRemark");
        	
        	String oldmName = (String)request.getAttribute("oldmName");
        	String oldmrName = (String)request.getAttribute("oldmrName");
        	String oldstartTime = (String)request.getAttribute("oldstartTime");
        	String oldendTime = (String)request.getAttribute("oldendTime");
        	String oldmRemark = (String)request.getAttribute("oldmRemark");
            %>
            
            <div class="page-content">
                <div class="content-nav">
                    会议管理 > 修改会议  > 会议修改成功
                </div>
                <form>
                    <fieldset>
                        <legend>会议信息</legend>
                        <table class="formtable">
                            <tr>
                                <td>原会议名称：</td>
                                <td><%= oldmName %></td>   
                                <td>修改后会议名称：</td>
                                <td><span style="color:red"><%= mName %></span></td>
                            
                            </tr>
                            <tr>
                                <td>原定会议室:</td>
                                <td><%= oldmName %></td>
                                <td>变更后会议室:</td>
                                <td><span style="color:red"><%= mrId %></span></td>
                            </tr>
                            <tr>
                                <td>原开始时间：</td>
                                <td><%= oldstartTime %></td>
                                <td>变更后开始时间：</td>
                                <td><span style="color:red"><%= startTime %></span></td>
                            </tr>
                            <tr>
                                <td>原会议结束时间：</td>
                                <td><%= oldendTime %></td>
                                <td>变更后结束时间：</td>
                                <td><span style="color:red"><%= oldmName %></span></td>
                            </tr>
                            <tr>
                                <td>原会议描述：</td>
                                <td><%= oldmRemark %></td>
                                <td>变更后会议描述：</td>
                                <td><span style="color:red"><%= oldmRemark %></span></td>
                            </tr>
                              <tr>
                                <td colspan="4" class="command">
                                    <input type="button" class="clickbutton" value="确认" onclick="window.location.href='adminIndex.jsp'"/>
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
        	<% } message = null; %>
    </body>
</html>