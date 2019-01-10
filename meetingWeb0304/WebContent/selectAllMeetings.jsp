<%@page import="com.chinasofti.meeting.vo.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.chinasofti.meeting.dao.MeetingDao, com.chinasofti.meeting.vo.Meeting,
    java.util.*, com.chinasofti.meeting.dao.MeetingRoomDao, com.chinasofti.meeting.vo.MeetingRoom"%>
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
                    会议管理  > 查看会议
                </div>
                
                
                    <% 

	                    int pageNum = 1;
	                    int showNum = 5;
	                    
	                    String pageNum2 = (String)request.getAttribute("pageNum");
	                    if (pageNum2 != null) {
	                    	pageNum = Integer.parseInt(request.getAttribute("pageNum").toString());
	                    }
	                    

                		MeetingDao mDao = new MeetingDao();
                    	ArrayList<Meeting> mList = mDao.selectAllMeeting(pageNum, showNum);
                    	if (mList.size() != 0) {
                    			
                    %>	
                
                
                
                <form action="HandlePageServlet">
                 <div>
                    <h3 style="text-align:center;">查询结果</h3>
                    <div class="pager-header">
                        <div class="header-info">
                            共<span class="info-number">
                            <% 
                            int count = mDao.meetingNum();
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
                        
                        <input type="text" id="pageNum2" style="display:none;" name="pageNum" value="<%= pageNum %>" />
                        
                        <div class="header-nav">
                            <input type="submit" class="clickbutton" value="首页" onclick="
                            	document.getElementById('pageNum').innerText = 1; 
                            	document.getElementById('pageNum2').value = 1; 

                            "/>
                            <input type="submit" class="clickbutton" value="上页" onclick="
                            if (document.getElementById('pageNum').innerText > 1) {
                            	document.getElementById('pageNum').innerText -= 1; 
                            }
                            if (document.getElementById('pageNum2').value > 1) {
                            	document.getElementById('pageNum2').value -= 1; 
                            }
                            "/>
                            <input type="submit" class="clickbutton" value="下页" onclick="
                            if (document.getElementById('pageNum').innerText < <%= count %>) {
                            	document.getElementById('pageNum').innerText = Number(document.getElementById('pageNum').innerText)
                            	+ Number(1); 
                            }
                            if (document.getElementById('pageNum2').value < <%= count %>) {
                            	document.getElementById('pageNum2').value = Number(document.getElementById('pageNum2').value)
                            	+ Number(1); 
                            }
                            	
                            "/>
                            <input type="submit" class="clickbutton" value="末页" onclick="
                            	document.getElementById('pageNum').innerText = <%= count %>;
                            	document.getElementById('pageNum2').value = <%= count %>;
                            	 "
                            />
                            跳到第<input type="text" id="pagenum" class="nav-number"/>页
                            <input type=submit class="clickbutton" 
                            
                            onclick="changePage()" value="跳转"/>
                        </div>
                        
                        
                        <script>
                       	
                       		function changePage() {
                       			var value = document.getElementById("pagenum").value;
                       			if (value >= 1 && value <= <%=count%>) {
                       				document.getElementById('pageNum').innerText = value;
                       				document.getElementById('pageNum2').value = value;
                       			} else {
                       				alert("您输入的页数有误!");
                       			}
                       		}
                        </script>
                        
                        
                        
                    </div>
                </div>
                
                </form>
                
                
                <table class="listtable">
                    <tr class="listheader">
                        <th>会议ID</th>
                        <th>会议名称</th>
                        <th>会议室名称</th>
                        <th>会议开始时间</th>
                        <th>会议结束时间</th>
                        <th>会议人数</th>
                        <th>会议状态</th>
                        <th>操作</th>
                    </tr>
                    
                    
			        
                    
                    <% 
                    		for (int i = 0; i < mList.size(); i ++) {
                    			
                    %>			
                    		<tr>
		                        <td><%= mList.get(i).getmId() %></td>
		                        <td><%= mList.get(i).getmName() %></td>
		                        <td>
		                        
		                        <% 
		                        MeetingRoomDao mrDao = new MeetingRoomDao();
		                        String mrName = null;
		                        if(mList.get(i).getMrId() != -1) {
			                        mrName = mrDao.selectMrById(mList.get(i).getMrId());
		                        
		                        %>
		                        <%= mrName %>
		                        
		                        <% } else { %>
		                        <font color="red" size="12">无</font>
		                        <% } %>
		                        
		                        </td>
		                        <td><%= mList.get(i).getStartTime() %></td>
		                        <td><%= mList.get(i).getEndTime() %></td>
		                        <td><%= mList.get(i).getmNum() %></td>
		                        <td>
		                        	<% if (mList.get(i).getmStatus() == 0) { %>
		                        		正常
		                        	<% } else {%>
		                        		取消 
		                        	<% } %>
		                        
		                        </td>
		                        <td>
		                        
		                        <%
		                        

		                        ArrayList<Integer> originEmpIdList = mDao.meetingEmpId(mList.get(i).getmId());
		                        
		                        
		                        
		                        %>
		                        
		                        <form action="UpdateMeetingServlet" style="display:inline-block;">
		                        	<input type="text" style="display:none;" name="mId" value="<%= mList.get(i).getmId() %>"/>
		                            <input type="text" style="display:none;" name="mName" value="<%= mList.get(i).getmName() %>"/>
		                            <input type="text" style="display:none;" name="mrName" value="<%= mrName %>"/>
		                            <input type="text" style="display:none;" name="mNum" value="<%= mList.get(i).getmNum() %>"/>
		                            <input type="text" style="display:none;" name="startTime" value="<%= mList.get(i).getStartTime() %>"/>
		                            <input type="text" style="display:none;" name="endTime" value="<%= mList.get(i).getEndTime() %>"/>
		                            <input type="text" style="display:none;" name="mRemark" value="<%= mList.get(i).getmDescribe() %>"/>
		                            <input type="text" style="display:none;" name="originEmpIdList" value="<%= originEmpIdList %>"/>
		                            <input type="text" style="display:none;" name="mStatus" value="<%= mList.get(i).getmStatus() %>"/>
		                            
                       				 <input type="hidden" name="pageNum" value="<%= pageNum %>" />
		                            <input type="submit" name="flag" value="修改" class="clickbutton" 
		                            "
		                            />
		                        </form>
		                        <form action="UpdateMeetingServlet" style="display:inline-block;">
		                        	<input type="text" style="display:none;" name="mId" value="<%= mList.get(i).getmId() %>"/>
		                            <input type="text" style="display:none;" name="mName" value="<%= mList.get(i).getmName() %>"/>
                       				 <input type="hidden" name="pageNum" value="<%= pageNum %>" />
		                            
		                            <input type="submit" name="flag" value="取消" class="clickbutton"
		                            />
		                        </form>
		                        <form action="UpdateMeetingServlet" style="display:inline-block;">
		                        	<input type="hidden" name="mId" value="<%= mList.get(i).getmId() %>"/>
		                            <input type="hidden" name="mName" value="<%= mList.get(i).getmName() %>"/>
		                            <input type="hidden" name="mrName" value="<%= mrName %>"/>
		                            <input type="hidden"  name="mNum" value="<%= mList.get(i).getmNum() %>"/>
		                            <input type="hidden" name="startTime" value="<%= mList.get(i).getStartTime() %>"/>
		                            <input type="hidden" name="endTime" value="<%= mList.get(i).getEndTime() %>"/>
		                            <input type="hidden" name="mRemark" value="<%= mList.get(i).getmDescribe() %>"/>
		                            <input type="hidden" name="mStatus" value="<%= mList.get(i).getmStatus() %>"/>
		                            
                       				 <input type="hidden" name="pageNum" value="<%= pageNum %>" />
                       				 <input type="submit" name="flag" value="查看详细信息" class="clickbutton"/>
		                        </form>
		                        </td>
                 		   </tr>	
                    			
                    <%			
                    	}
                    
                    %>
                    
                    
                    
                </table>
                
                <%} else { %>
                	<font color="red" size="12">还没有会议信息，快去添加会议吧</font>
                <%} %>
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
			        	<% } %>
    </body>
</html>