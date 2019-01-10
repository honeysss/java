<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.chinasofti.meeting.dao.MeetingDao, com.chinasofti.meeting.vo.Meeting,
    java.util.*, com.chinasofti.meeting.dao.*, com.chinasofti.meeting.vo.*,
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
     <script type="application/javascript">
        	var empIdList = [];
        	
            function selectEmployees(){
                for(var i=0;i<selEmployees.options.length;i++){
                    if (selEmployees.options[i].selected){
                        addEmployee(selEmployees.options[i]);
                        selEmployees.options[i].selected = false;
                    }
                }
            }
            
            function deSelectEmployees(){
                var elementsToRemoved = new Array();
                var options = selSelectedEmployees.options;
                for(var i=0;i<options.length;i++){
                    if (options[i].selected){
                        elementsToRemoved.push(options[i]);
                       
                       
                    }
                }
                for(i=0;i<elementsToRemoved.length;i++){
                    for (var j = 0; j < empIdList.length; j ++) {
                    	if (empIdList[j] == elementsToRemoved[i].value) {
                    		empIdList.splice(j, 1);
                    	}
                    }

                    selSelectedEmployees.removeChild(elementsToRemoved[i]);
                }
                
                document.getElementById("empIdList").value = empIdList;
            }
            
            function addEmployee(optEmployee){
                var options = selSelectedEmployees.options;
                var i = 0;
                var insertIndex = -1;
                while(i < options.length){
                    if (optEmployee.value == options[i].value){
                        return;
                    } else if (optEmployee.value < options[i].value) {
                        insertIndex = i;
                        break;
                    }
                    i++;
                }
                var opt = document.createElement("option");
                opt.value = optEmployee.value;
                
                empIdList.push(opt.value);
                document.getElementById("empIdList").value = empIdList;
                
                opt.text = optEmployee.text;
                
                if (insertIndex == -1){
                    selSelectedEmployees.appendChild(opt);
                } else {
                    selSelectedEmployees.insertBefore(opt, options[insertIndex]);
                }
            }            
        </script>
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
        <%
        
        int mId2 = -1;
		if (request.getAttribute("mId2") != null && !request.getAttribute("mId2").equals("")) {
			mId2 = Integer.parseInt(request.getAttribute("mId2").toString());
		}
        
        
        int pageNum = 1;
        
        String pageNum2 = (String)request.getAttribute("pageNum");
        if (pageNum2 != null) {
        	pageNum = Integer.parseInt(request.getAttribute("pageNum").toString());
        }
        %>
           
            <%@include file="adminPageBar.jsp" %>
            <div class="page-content">
                <div class="content-nav">
                    会议管理 > 修改会议
                </div>
                <form action="UpdateMeetingServlet2" id="form">
                        <input type="hidden" value="<%= pageNum %>" name="pageNum"/>
                <input type="hidden" name="mId2" value="<%= mId2 %>"/>
                    <fieldset>
                        <legend>会议信息</legend>
                        <table class="formtable">
                        
                        <% 
                     	MeetingDao mDao = new MeetingDao();
                        	int mId = 0;
                        	if (request.getAttribute("mId") != null) {
                            	mId = Integer.parseInt(request.getAttribute("mId").toString());
                        	}
                        	String mName = (String)request.getAttribute("mName");
	                    	String mrName = (String)request.getAttribute("mrName");
	                    	String startTime = (String)request.getAttribute("startTime");
	                    	String endTime = (String)request.getAttribute("endTime");
	                    	String mRemark = (String)request.getAttribute("mRemark");
	                    	String mName2 = (String)request.getAttribute("mName2");
	                    	String ifSearch = (String)request.getAttribute("ifSearch");
	                    	String originEmpIdList = (String)request.getAttribute("originEmpIdList");
	                    	int mStatus = 0;
                        	if (request.getAttribute("mStatus") != null) {
                        		mStatus = Integer.parseInt(request.getAttribute("mStatus").toString());
                        	}
	                    	

	            			
	                    	
                            int num = mDao.meetingEmpNum(mId);
	                   %>
	                   
                        <input style="display:none;" value="<%= num %>" /> 
                        <input style="display:none;" name="originEmpIdList" value="<%= originEmpIdList %>" /> 
                        <input style="display:none;" name="oldmName" value="<%= mName %>" /> 
                        <input style="display:none;" name="oldmrName" value="<%= mrName %>" /> 
                        <input style="display:none;" name="oldstartTime" value="<%= startTime %>" /> 
                        <input style="display:none;" name="oldendTime" value="<%= endTime %>" /> 
                        <input style="display:none;" name="oldmRemark" value="<%= mRemark %>" /> 
                        <input style="display:none;" name="oldmStatus" value="<%= mStatus %>" /> 
	                   
                        
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
                            
                           
                            
                            <tr>
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
                                <td>会议状态：</td>
                                <td>
                                    <input type="radio" id="role" name="mStatus" value="0"
                                    <% if(mStatus == 0) { %>
                                    checked="checked";
                                    <% } %>
                                    />正常
                                    <input type="radio" id="role" name="mStatus" value="1"
                                    <% if(mStatus == 1) { %>
                                    checked="checked";
                                    <% } %>
                                    />取消
                                </td>
                            </tr>
                            
                            
                             <tr>
                                <td>参会人员：</td>
                                <td>
                                
                                     <% 
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
	                                            	                                     
	                              <a class="clickbutton" 
	                              href="ModifyMeetingServlet?eId=<%= empList.get(i).getEmpId() %>&mId=<%= mId %>&mName=<%= mName %>&mrName=<%= mrName %>&startTime=<%= startTime %>&endTime=<%= endTime %>&mRemark=<%= mRemark %>&originEmpIdList=<%= originEmpIdList %>&pageNum=<%= pageNum %>&ifSearch=<%= ifSearch %>&mName2=<%= mName2 %>&mId2=<%= mId2 %>"
	                              >删除</a>
	     
	                              
	                              
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
                            
                            
                            
	                                        
	                                        <%
	                                        ArrayList<Integer> oldEmpIdList = new ArrayList<Integer>();
	                                        for (int i2 = 0; i2 < empList.size(); i2 ++) {
	                                        	oldEmpIdList.add(empList.get(i2).getEmpId());
	                                        }
	                                        %>
	                                        
	                                        <input name="oldEmpIdList" value="<%= oldEmpIdList %>" type="hidden"/>
                            
                              <tr>
                                <td>增加参会人员：</td>
                                <td>
                                    <div id="divfrom">
                                    
                                    <%
                                    EmployeeDao empDao = new EmployeeDao();
                                    ArrayList<Employee> employeeList2 = empDao.selectAllUser();
                                    ArrayList<Integer> newEmpId = new ArrayList<Integer>();
                                    
                                    for (int i = 0; i < empList.size(); i ++) {
                                    	newEmpId.add(empList.get(i).getEmpId());
                                    }
                                    
                                    for (int i = 0; i < employeeList2.size(); i ++) {
                                    	newEmpId.add(employeeList2.get(i).getEmpId());
                                    }
                                    
                                    
                                    Collections.sort(newEmpId, new Comparator<Integer>() {
                                    	public int compare(Integer lhs, Integer rhs) {
                                  			 if ( lhs > rhs ) {
                                    		   return 1;
                                    		} else {
                                    		    return -1;
                                    		  }
                                    	  }
                                    });
                                    

                                    for (int i = 0; i < newEmpId.size() - 1; i ++) {
                                    	if (newEmpId.get(i) == newEmpId.get(i+1)) {
                                    		newEmpId.remove(i+1);
                                    		newEmpId.remove(i);
                                    		i = i-1;
                                    	}
                                    }
                                    	
                                    
                                    
                                    %>
                                    
                                        
                                        	 <%

                                            ArrayList<Employee> employeeList = new ArrayList<Employee>();
                                        	 
                                        	 if (newEmpId.size() != 0) {
                                             	for (int i = 0; i < newEmpId.size(); i ++) {
                                            		employeeList.add(empDao.selectById(newEmpId.get(i)));
                                            	}
                                        	 }
                                        	 
                                        	 
                                        		if (employeeList.size() != 0) {
                                        	 %>
                                        
                                        <select id="selEmployees" multiple="true">
                                        <%
                                            		for (int j = 0; j <employeeList.size(); j ++) {
                                            			if (employeeList.get(j).getEmpName() != null && !employeeList.get(j).getEmpName().equals("")) {
                                            	%>		
                                            	
                                            		<option value="<%= employeeList.get(j).getEmpId() %>"><%= employeeList.get(j).getEmpName() %></option>
                                            	<%
                                            	}
                                            		}
                                            	%>
                                        </select>
                                        
                                        
                                    </div>
                                    
                                    
                                    
                                    <div id="divoperator">
                                    
                                    
                                    	<input type="text" name="empIdList" style="display:none;" id="empIdList" />
                                    	<input type="button" class="clickbutton" value="&gt;" onclick="selectEmployees()"/>
                                        <input type="button" class="clickbutton" value="&lt;" onclick="deSelectEmployees()"/>
                                        
                                        
                                        
                                    </div>
                                    <div id="divto">
                                        <select id="selSelectedEmployees" multiple="true" name="empId">
                                        	
                                        </select>
                                    </div>
                                    <%
                                    } else {
                                            	%>
                                            	<font color="red" size="12">所有员工都参加该会议了</font>
                                            	<% } %>
                                </td>
                            </tr>
                            
                            
                            <tr>
                                <td class="command" colspan="2">
	                                <button type="submit" class="clickbutton">确认修改</button>     
                                    <a type="button" class="clickbutton"
                                    style="background-color:white;color:black;" 
                                    href="ClearMesServlet?ifSearch=<%=ifSearch%>&mName2=<%=mName2%>&pageNum=<%= pageNum %>&mId2=<%=mId2 %>"
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