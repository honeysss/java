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
                    会议管理 > 查看所有会议  > 查看详细信息
                </div>
                
                
                <%
                
                int mId2 = -1;
        		if (request.getAttribute("mId2") != null && !request.getAttribute("mId2").equals("")) {
        			mId2 = Integer.parseInt(request.getAttribute("mId2").toString());
        		}
        		
                int mStatus = 0;
            	if (request.getAttribute("mStatus") != null) {
            		mStatus = Integer.parseInt(request.getAttribute("mStatus").toString());
            	}
                int pageNum = 1;
                
                String pageNum2 = (String)request.getAttribute("pageNum");
                if (pageNum2 != null) {
                	pageNum = Integer.parseInt(request.getAttribute("pageNum").toString());
                }
                %>
                
                <form>
                    <fieldset>
                        <legend>会议信息</legend>
                        <table class="formtable">
                        
                        <% 
                        int mId = 0;
                        if (request.getAttribute("mId") != null) {
                        	mId = Integer.parseInt(request.getAttribute("mId").toString());
                        }
                        String mName = (String)request.getAttribute("mName");
	                    	String mrName = (String)request.getAttribute("mrName");
	                    	String mNum = (String)request.getAttribute("mNum");
	                    	String startTime = (String)request.getAttribute("startTime");
	                    	String endTime = (String)request.getAttribute("endTime");
	                    	String mRemark = (String)request.getAttribute("mRemark");
	                    	


	                    	String mName2 = (String)request.getAttribute("mName2");
	                    	String ifSearch = (String)request.getAttribute("ifSearch");
	                    	
	                   %>
                        
                            <tr>
                                <td>会议名称：</td>
                                <td><%= mName %></td>
                            </tr>
                            <tr>
                                <td>会议室名称：</td>
                                <td>
                                <%
                                if (mrName == null || mrName.equals("") || mrName.equals("null")) {
                                %>
                                <font color="red" size="12">无</font>
                                <% } else { %>
                                <%= mrName %>
                                <% } %>
                                </td>
                            </tr>
                            <tr>
                                <td>参加人数：</td>
                                <td><%= mNum %></td>
                            </tr>
                            <tr>
                                <td>开始时间：</td>
                                <td><%= startTime %></td>
                            </tr>
                            <tr>
                                <td>结束时间：</td>
                                <td><%= endTime %></td>
                            </tr>
                            <tr>
                                <td>会议说明：</td>
                                <td>
                                    <textarea id="description" rows="5" readonly><%= mRemark %></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>会议状态：</td>
                                <td>
                                    <% if(mStatus == 0) { %>
                               			 <span>正常</span>
                                    <% } %>
                                    
                                    <% if(mStatus == 1) { %>
                                    <span>取消</span>
                                    <% } %>
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
                                    href="ClearMesServlet?ifSearch=<%=ifSearch%>&mName2=<%=mName2%>&pageNum=<%=pageNum %>&mId2=<%= mId2 %>"
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