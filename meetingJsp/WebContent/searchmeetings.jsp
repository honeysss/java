<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.chinasofti.meeting.dao.MeetingDao, com.chinasofti.meeting.vo.Meeting,
    java.util.*, com.chinasofti.meeting.dao.MeetingRoomDao, com.chinasofti.meeting.vo.MeetingRoom"%>
<!DOCTYPE html>
<html>
    <head>
        <title>中软会议管理系统</title>
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
                    会议管理  > 查找会议
                </div>
                <form action="searchMeetingServlet">
                    <fieldset>
                        <legend>查询会议</legend>
                        <table class="formtable">
                            <tr>
                                <td>会议名称：</td>
                                <td>
                                    <input type="text" id="meetingname" maxlength="20" name="mName" 
                                    style="margin-left:-35%; margin-bottom:10px;"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" class="command">
                                    <input type="submit" class="clickbutton" value="查询"/>
                                    <input type="reset" class="clickbutton" value="重置"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
                
                
                
                
                <% 
               	 String mName = (String)request.getAttribute("mName") + "%";
                String mName2 = (String)request.getAttribute("mName");
                MeetingDao mDao = new MeetingDao();
                int pageNum = 1;
                int showNum = 5;
                ArrayList<Meeting> selectmList = new ArrayList<Meeting>();
                if (mName != null) {
                    selectmList = mDao.selectMeetingBymName(mName, pageNum, showNum);
                }
               if (selectmList.size() != 0) {
            	%>   
            		
            		
            		 
                
                 <div>
                    <h3 style="text-align:center;color:black">查询结果</h3>
                    <div class="pager-header">
                        <div class="header-info">
                            共<span class="info-number">
                            <% 
                            int count = mDao.selectMeetingNum(mName);
                            %>
                            <%= count %>
                            </span>条结果，
                            分成<span class="info-number">
                            <% if (count%5 != 0) {
                            	count = count/5 + 1;
                            } else {
                            	count = count/5;
                            }
                          	%>
                            <%= count %>
                            </span>页显示，
                            当前第<span class="info-number" id="pageNum"><%= pageNum %></span>页
                        </div>
                        
                        
                        
                        <div class="header-nav">
                            <input type="button" class="clickbutton" value="首页" onclick="
                            	document.getElementById('pageNum').innerText = 1; 
                            <% 
                            pageNum = 1;
                            selectmList = mDao.selectMeetingBymName(mName, pageNum, showNum);	%>
                            "/>
                            <input type="button" class="clickbutton" value="上页" onclick="
                            if (document.getElementById('pageNum').innerText > 1) {
                            	document.getElementById('pageNum').innerText -= 1; 
                            }
                            <%
                            if (pageNum > 1) {
                            	pageNum -= 1;
                            }
                            selectmList = mDao.selectMeetingBymName(mName, pageNum, showNum);	
                            %>
                            "/>
                            <input type="button" class="clickbutton" value="下页" onclick="
                            if (document.getElementById('pageNum').innerText < <%= count %>) {
                            	document.getElementById('pageNum').innerText = Number(document.getElementById('pageNum').innerText)
                            	+ Number(1); 
                            }
                                <%
                                if (pageNum < count) {
                                	pageNum += 1;
                                }
                                selectmList = mDao.selectMeetingBymName(mName, pageNum, showNum);	
                                %>
                            	
                            "/>
                            <input type="button" class="clickbutton" value="末页" onclick="
                            	document.getElementById('pageNum').innerText = <%= count %>;
                            	<%
                                pageNum = count;
                                selectmList = mDao.selectMeetingBymName(mName, pageNum, showNum);	
                                %> "
                            />
                            跳到第<input type="text" id="pagenum" class="nav-number"/>页
                            <input type="button" class="clickbutton" value="跳转"/>
                        </div>
                        
                        
                        
                    </div>
                </div>
                <table class="listtable">
                    <tr class="listheader">
                        <th>会议名称</th>
                        <th>会议室名称</th>
                        <th>会议开始时间</th>
                        <th>会议结束时间</th>
                        <th>会议人数</th>
                        <th>会议状态</th>
                        <th>操作</th>
                    </tr>
                    
                    <% 
                     	   for (int i = 0; i < selectmList.size(); i ++) { 
                    			
                    %>			
                    		<tr>
		                        <td><%= selectmList.get(i).getmName() %></td>
		                        <td>
		                        
		                        <% 
		                        MeetingRoomDao mrDao = new MeetingRoomDao();
		                        String mrName = mrDao.selectMrById(selectmList.get(i).getMrId());
		                        %>
		                        <%= mrName %>
		                        </td>
		                        <td><%= selectmList.get(i).getStartTime() %></td>
		                        <td><%= selectmList.get(i).getEndTime() %></td>
		                        <td><%= selectmList.get(i).getmNum() %></td>
		                        <td>
		                        	<% if (selectmList.get(i).getmStatus() == 0) { %>
		                        		正常
		                        	<% } else {%>
		                        		取消 
		                        	<% } %>
		                        
		                        </td>
		                        <td>
		                        <form action="updateMeetingServlet" style="display:inline-block;">
		                        	<input type="text" style="display:none;" name="ifSearch" value="查找修改"/>
		                        	<input type="text" style="display:none;" name="mName2" value="<%=mName2%>"/>
		                        	<input type="text" style="display:none;" name="mId" value="<%= selectmList.get(i).getmId() %>"/>
		                            <input type="text" style="display:none;" name="mName" value="<%= selectmList.get(i).getmName() %>"/>
		                            <input type="text" style="display:none;" name="mrName" value="<%= mrName %>"/>
		                            <input type="text" style="display:none;" name="mNum" value="<%= selectmList.get(i).getmNum() %>"/>
		                            <input type="text" style="display:none;" name="startTime" value="<%= selectmList.get(i).getStartTime() %>"/>
		                            <input type="text" style="display:none;" name="endTime" value="<%= selectmList.get(i).getEndTime() %>"/>
		                            <input type="text" style="display:none;" name="mRemark" value="<%= selectmList.get(i).getmDescribe() %>"/>
		                            <input type="submit" name="flag" value="修改" class="clickbutton"/>
		                        </form>
		                        <form action="updateMeetingServlet" style="display:inline-block;">
		                        	<input type="text" style="display:none;" name="ifSearch" value="查找取消"/>
		                        	<input type="text" style="display:none;" name="mName2" value="<%=mName2%>"/>
		                        	
		                        	<input type="text" style="display:none;" name="mId" value="<%= selectmList.get(i).getmId() %>"/>
		                            <input type="text" style="display:none;" name="mName" value="<%= selectmList.get(i).getmName() %>"/>
		                            <input type="submit" name="flag" value="取消" class="clickbutton"/>
		                        </form>
		                        <form action="updateMeetingServlet" style="display:inline-block;">
		                        	<input type="text" style="display:none;" name="mId" value="<%= selectmList.get(i).getmId() %>"/>
		                            <input type="text" style="display:none;" name="mName" value="<%= selectmList.get(i).getmName() %>"/>
		                            <input type="text" style="display:none;" name="mrName" value="<%= mrName %>"/>
		                            <input type="text" style="display:none;" name="mNum" value="<%= selectmList.get(i).getmNum() %>"/>
		                            <input type="text" style="display:none;" name="startTime" value="<%= selectmList.get(i).getStartTime() %>"/>
		                            <input type="text" style="display:none;" name="endTime" value="<%= selectmList.get(i).getEndTime() %>"/>
		                            <input type="text" style="display:none;" name="mRemark" value="<%= selectmList.get(i).getmDescribe() %>"/>
		                            <input type="submit" name="flag" value="查看详细信息" class="clickbutton"/>
		                        </form>
		                        </td>
                 		   </tr>	
                    		
                    <%			
                    	}
                    
                    %>
                    
                      
                   
                </table>
            	<%
               	} else  
               	{ if (!mName.equals("null%")) {
               	%>
               
               
               <font color="red" size="12">还没有该会议信息</font>
               
               <% 	
               	}
               	}
                %>	
                
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