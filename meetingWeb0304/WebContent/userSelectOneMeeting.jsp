<%@page import="com.chinasofti.meeting.dao.MeetingDao"%>
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
                    我的信息 > 查看我的会议  > 查看详细信息
                </div>
                <form>
                    <fieldset>
                        <legend>会议信息</legend>
                        <table class="formtable">
                        
                        <% 
                        int mId = 0;
                        if (request.getAttribute("mId") != null) {
                        	mId = Integer.parseInt(request.getAttribute("mId").toString());
                        }
                        int eId = 0;
                        if (request.getAttribute("empId") != null) {
                        	eId = Integer.parseInt(request.getAttribute("empId").toString());
                        }
                        MeetingDao mDao = new MeetingDao();
                        Meeting meeting = mDao.selectMeetingByEmpIdAndmId(eId, mId);
                        MeetingRoomDao mrDao = new MeetingRoomDao();
                        
	                   %>
                        
                            <tr>
                                <td>会议名称：</td>
                                <td><%= meeting.getmName() %></td>
                            </tr>
                            <tr>
                                <td>会议室名称：</td>
                                <td><%= mrDao.selectMrById(meeting.getMrId()) %></td>
                            </tr>
                            <tr>
                                <td>参加人数：</td>
                                <td><%= meeting.getmNum() %></td>
                            </tr>
                            <tr>
                                <td>开始时间：</td>
                                <td><%= meeting.getStartTime() %></td>
                            </tr>
                            <tr>
                                <td>结束时间：</td>
                                <td><%= meeting.getEndTime() %></td>
                            </tr>
                            <tr>
                                <td>会议说明：</td>
                                <td>
                                    <textarea id="description" rows="5" readonly><%= meeting.getmDescribe() %></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>参会人员：</td>
                                <td>
                                
                                     <% 
                                     	ArrayList<Employee> empList = mDao.selectEmpByMId(meeting.getmId());
                                     	if (empList.size() != 0) {
                                     		%>
                                    <table class="listtable">
                                        <tr class="listheader">
                                            <th>姓名</th>
                                            <th>联系电话</th>
                                            <td>电子邮件</td>
                                        </tr>
                                        <%
                                        		for (int i = 0; i < empList.size(); i ++) {
                                         %>
                                        		
	                                        <tr>
	                                            <td><%= empList.get(i).getEmpName() %></td>
	                                            <td><%= empList.get(i).getEmpTel() %></td>
	                                            <td><%= empList.get(i).getEmpEmail() %></td>
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
                                    <a type="button" class="clickbutton"
                                    style="background-color:white;color:black;" 
                                    href="ClearMyMesServlet"
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