<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.chinasofti.meeting.dao.MeetingDao, com.chinasofti.meeting.vo.Meeting,
    java.util.*, com.chinasofti.meeting.dao.MeetingRoomDao, com.chinasofti.meeting.vo.MeetingRoom,
     com.chinasofti.meeting.vo.Employee"%>
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
                    会议管理 > 修改会议
                </div>
                <form action="updateMeetingServlet2" id="form">
                    <fieldset>
                        <legend>会议信息</legend>
                        <table class="formtable">
                        
                        <% 
                        	int mId = 0;
                        	if (request.getAttribute("mId").toString() != null) {
                            	mId = Integer.parseInt(request.getAttribute("mId").toString());
                        	}
                        	String mName = (String)request.getAttribute("mName");
	                    	String mrName = (String)request.getAttribute("mrName");
	                    	String startTime = (String)request.getAttribute("startTime");
	                    	String endTime = (String)request.getAttribute("endTime");
	                    	String mRemark = (String)request.getAttribute("mRemark");
	                    	String mName2 = (String)request.getAttribute("mName2");
	                    	String ifSearch = (String)request.getAttribute("ifSearch");
	                   %>
	                   
                        <input style="display:none;" name="oldmName" value="<%= mName %>" /> 
                        <input style="display:none;" name="oldmrName" value="<%= mrName %>" /> 
                        <input style="display:none;" name="oldstartTime" value="<%= startTime %>" /> 
                        <input style="display:none;" name="oldendTime" value="<%= endTime %>" /> 
                        <input style="display:none;" name="oldmRemark" value="<%= mRemark %>" /> 
	                   
                        
                        <input style="display:none;" name="mId" value="<%= mId %>" /> 
                        <input style="display:none;" name="mName2" value="<%= mName2 %>" /> 
                        <input style="display:none;" name="ifSearch" value="<%= ifSearch %>" /> 
                            <tr>
                                <td>会议名称：</td>
                                <td>
                                <input type="text" name="mName" value="<%= mName %>" />
                            </td>
                            <tr>
                                <td>会议室名称：</td>
                                <td>
                                    <select name="mrId">  
                                        <% 
					                    	MeetingRoomDao mrDao = new MeetingRoomDao();
					                   		ArrayList<MeetingRoom> mrList = mrDao.selectAllMr();
					                   		if (mrList.size() != 0) {
					                   			for (int i = 0; i < mrList.size(); i ++) {
					                   	%>  
                                     	<option value="<%= mrList.get(i).getMrId() %>" >
                                     		<%= mrList.get(i).getMrName() %>
                                     	</option>
										
										<% } } %>
                                     </select>
                                </td>
                            </tr>
                                <td>开始时间：</td>
                                <td>
                                <input type="text" name="startTime" value="<%= startTime %>" />
                            </td>
                            <tr>
                                <td>结束时间：</td>
                                <td>
                                <input type="text" name="endTime" value="<%= endTime %>" />
                            </td>
                            <tr>
                                <td>会议说明：</td>
                                <td>
                                <input type="text" name="mRemark" value="<%= mRemark %>" />
                            </td>
                            </tr>
                            
                             <tr>
                                <td>参会人员：</td>
                                <td>
                                
                                     <% 
                                     	MeetingDao mDao = new MeetingDao();
                                     	ArrayList<Employee> empList = mDao.selectEmpByMId(mId);
                                     	if (empList.size() != 0) {
                                     		%>
                                    <table class="listtable">
                                        <tr class="listheader">
                                            <th>姓名</th>
                                            <th>联系电话</th>
                                            <td>电子邮件</td>
                                            <td>操作</td>
                                        </tr>
                                        <%
                                        		for (int i = 0; i < empList.size(); i ++) {
                                         %>
                                        		
	                                        <tr>
	                                        	<td><%= empList.get(i).getEmpName() %></td>
	                                            <td><%= empList.get(i).getEmpTel() %></td>
	                                            <td><%= empList.get(i).getEmpEmail() %></td>
	                                            <td>
	                                            	<input style="display:none;" name="eId" value="<%= empList.get(i).getEmpId() %>" />
	                                            	<span class="clickbutton" onclick="deleteSubmit()">删除</span>                                       
	                              
                                            	</td>
	                                        </tr>	
                                        			
                                        <%
                                        		}
                                        %>
                                        
                                    </table>
                                    <%  }else { %>
                                    <font color="red" size="12">该会议还没有参与人员</font>
                                    <% } %>
                                </td>
                            </tr>
                            
                            <tr>
                                <td class="command" colspan="2">
	                                <button type="submit" class="clickbutton">确认修改</button>     
                                    <input type="button" class="clickbutton" value="返回" onclick="window.history.back();"/>
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
        <script>
        	var form = document.getElementById('form');
        	function deleteSubmit() {
        		form.setAttribute('action', 'modifyMeetingServlet');
        		form.submit();
        	}
        </script>
        
    </body>
</html>