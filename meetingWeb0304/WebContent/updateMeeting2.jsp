<%@page import="com.chinasofti.meeting.dao.EmployeeDao"%>
<%@page import="com.chinasofti.meeting.vo.Employee"%>
<%@page import="java.util.*"%>
<%@page import="com.chinasofti.meeting.dao.MeetingRoomDao"%>
<%@page import="com.chinasofti.meeting.dao.DepartmentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Arrays"%>
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
            .formtable tr td:last-child {
            width: 40% !important;
            }
            .formtable tr td:first-child {
            width: 16% !important;
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
            <%

            int mId2 = -1;
    		if (request.getAttribute("mId2") != null && !request.getAttribute("mId2").equals("")) {
    			mId2 = Integer.parseInt(request.getAttribute("mId2").toString());
    		}
            
            
            EmployeeDao empDao = new EmployeeDao();
            
            int pageNum = 1;
            if (request.getAttribute("pageNum") != null) {
            	pageNum = Integer.parseInt(request.getAttribute("pageNum").toString());
            }
            String mName2 = (String)request.getAttribute("mName2");
            String ifSearch = (String)request.getAttribute("ifSearch");
            
            String mName = (String)request.getAttribute("mName");
        	int mrId = 0;
        	if (request.getAttribute("mrId") != null) {
        		mrId = Integer.parseInt(request.getAttribute("mrId").toString());
        	}
        	int mStatus = 0;
        	if (request.getAttribute("mStatus") != null) {
        		mStatus = Integer.parseInt(request.getAttribute("mStatus").toString());
        	}
        	
        	int oldmStatus = 0;
        	if (request.getAttribute("oldmStatus") != null) {
        		oldmStatus = Integer.parseInt(request.getAttribute("oldmStatus").toString());
        	}
        	String startTime = (String)request.getAttribute("startTime");
        	String endTime = (String)request.getAttribute("endTime");
        	String mRemark = (String)request.getAttribute("mRemark");
        	
        	String oldmName = (String)request.getAttribute("oldmName");
        	String oldmrName = (String)request.getAttribute("oldmrName");
        	String oldstartTime = (String)request.getAttribute("oldstartTime");
        	String oldendTime = (String)request.getAttribute("oldendTime");
        	String oldmRemark = (String)request.getAttribute("oldmRemark");

        	String oldEmpIdList2 = (String)request.getAttribute("oldEmpIdList");
        	String empIdList2 = (String)request.getAttribute("empIdList");
        	
        	

        	String empIdList3 = empIdList2.replace("[", "");
			String empIdList4 = empIdList3.replace("]", "");
			String[] empIdList = empIdList4.split(",");
			
			
			
			String oldEmpIdList3 = oldEmpIdList2.replace("[", "");
			String oldEmpIdList4 = oldEmpIdList3.replace("]", "");
			String[] oldEmpIdList = oldEmpIdList4.split(",");
			
			

        	String originEmpIdList2 = (String)request.getAttribute("originEmpIdList");
        	

        	String originEmpIdList3 = originEmpIdList2.replace("[", "");
			String originEmpIdList4 = originEmpIdList3.replace("]", "");
			String[] originEmpIdList = originEmpIdList4.split(",");
			
			
			
			
			ArrayList<Employee> oldEmpName = new ArrayList<Employee>();
         	
         	if (originEmpIdList.length != 0) {
         		for(int i = 0; i < originEmpIdList.length; i ++) {
         			if (!originEmpIdList[i].trim().equals("")) {
                 		oldEmpName.add(empDao.selectById(Integer.parseInt(originEmpIdList[i].trim())));
         			}
             	}
         	}
			


			
			 ArrayList<Employee> newEmpName = new ArrayList<Employee>();
           	
			 ArrayList<Integer> newEmpIdList = new ArrayList<Integer>();
			 
			 if (empIdList.length != 0 ) {
				 for(int i = 0; i < empIdList.length; i ++) {
					 if (!empIdList[i].equals("")) {
					 	newEmpIdList.add(Integer.parseInt(empIdList[i]));
					 }
				 }
			 }

 			if (oldEmpIdList.length != 0) {
				 for(int i = 0; i < oldEmpIdList.length; i ++) {
					 if (!oldEmpIdList[i].trim().equals("")) {
					 	newEmpIdList.add(Integer.parseInt(oldEmpIdList[i].trim()));
					 }
				 } 
			 }
			 
			 if (newEmpIdList.size() != 0) {
				 for(int i = 0; i < newEmpIdList.size(); i ++) {
                  		newEmpName.add(empDao.selectById(newEmpIdList.get(i)));
                  	}
			 }
			 
			

			
        	
            %>
            
            <div class="page-content">
                <div class="content-nav">
                    会议管理 > 修改会议  > 会议修改成功
                </div>
                <form action="UpdatedMeetingServlet">
                <input type="hidden" name="pageNum" value="<%= pageNum %>"/>
                <input type="hidden" name="ifSearch" value="<%= ifSearch %>"/>
                <input type="hidden" name="mName2" value="<%= mName2 %>"/>
                <input type="hidden" name="mId2" value="<%= mId2 %>"/>
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
                                <td><%= oldmrName %></td>
                                <td>变更后会议室:</td>
                                <td><span style="color:red"><%
                                MeetingRoomDao mrDao = new MeetingRoomDao();
                               String mrName = mrDao.selectMrById(mrId);
                              
                                %>
                                <%= mrName %></span></td>
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
                                <td><span style="color:red"><%= endTime %></span></td>
                            </tr>
                            
                            <tr>
                                <td>原会议状态：</td>
                                <td>
                                <% if(oldmStatus == 0) { %>
                               		     正常
                                    <% } %>
                                    
                                    
                                    <% if(oldmStatus == 1) { %>
                                 	   取消
                                    <% } %>
                                
                                </td>
                                <td>变更后会议状态：</td>
                                <td><span style="color:red">
                                <% if(mStatus == 0) { %>
                               		     正常
                                    <% } %>
                                    
                                    
                                    <% if(mStatus == 1) { %>
                                 	   取消
                                    <% } %>
                              </span></td>
                            </tr>
                            
                            
                            <tr>
                                <td>原会议描述：</td>
                                <td><%= oldmRemark %></td>
                                <td>变更后会议描述：</td>
                                <td><span style="color:red"><%= mRemark %></span></td>
                            </tr>
                            
                      



      <tr>
                                <td style="line-height:170px;">原参与人员：</td>
                                
                                <td style="padding-right:100px;">
                                
                                     <% 
                                     	
                                     	
                                     
                                     	if (oldEmpName.size() != 0) {
                                     %>
                                     
                                    <table class="listtable">
                                        <tr class="listheader">
                                        </tr>
                                        <%
                                        	for (int i = 0; i < oldEmpName.size(); i ++) {
                                         %>
                                        		
	                                        <tr>
	                                        	<td><%= oldEmpName.get(i).getEmpName() %></td>
	                                        </tr>	
	                                        
                                        			
                                        <%
                                        	}
                                        %>
                                        
                                    </table>
                                    
                                    <%  }else { %>
                                    
                                    <font color="red" size="12">该会议还没有参与人员</font>
                                    
                                    <% } %>
                                    
                                    
                                    
                                </td>
                                
                                
                                
                                <td>变更后参与人员：</td>
                                <td>

									 <% 
									
									 
                                  	
                                  
                                     	if (newEmpName.size() != 0) {
                                     %>
                                     
                                    <table class="listtable" style="width:29%;">
                                        <tr class="listheader">
                                        </tr>
                                        <%
                                        	for (int i = 0; i < newEmpName.size(); i ++) {
                                         %>
                                        		
	                                        <tr>
	                                        	<td><%= newEmpName.get(i).getEmpName() %></td>
	                                        </tr>	
	                                        
                                        			
                                        <%
                                        	}
                                        %>
                                        
                                    </table>
                                    
                                    <%  }else { %>
                                    
                                    <font color="red" size="12">该会议没有参与人员了</font>
                                    
                                    <% } %>
									
								</td>
                            </tr>


    



                            
                              <tr>
                                <td colspan="4" class="command">
                                    <input type="submit" class="clickbutton" value="确认"/>
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