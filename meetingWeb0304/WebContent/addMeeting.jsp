<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.chinasofti.meeting.vo.*, 
    com.chinasofti.meeting.dao.MeetingRoomDao, com.chinasofti.meeting.dao.DepartmentDao, 
    com.chinasofti.meeting.vo.Department, com.chinasofti.meeting.dao.*, 
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
                width:150px;
            }
            #divto{
                float:left;
                width:150px;
            }
            #divoperator{
                float:left;
                width:50px;
                padding:60px 5px;
            }
            #divoperator input[type="button"]{
                margin:10px 7px;
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
<a id="changedDeptId"></a>
           
            <%@include file="adminPageBar.jsp" %>
            <div class="page-content">
                <div class="content-nav">
                    会议管理 > 增加会议
                </div>
                <form action="AddMeetingServlet">
                <%
                String mName = (String)request.getAttribute("mName");
                String startTime = (String)request.getAttribute("startTime");
                String endTime = (String)request.getAttribute("endTime");
                String mRemark = (String)request.getAttribute("mRemark");
                int mrId = 0;
                if (request.getAttribute("mrId") != null) {
                	mrId = Integer.parseInt(request.getAttribute("mrId").toString());
                }
                
                %>
                
                
                
                    <fieldset>
                        <legend>会议信息</legend>
                        <table class="formtable">
                            <tr>
                                <td>会议名称：</td>
                                <td>
                                    <input type="text" id="meetingname" maxlength="20" name="mName"
                                    <% if (mName != null && !mName.equals("")) { %>
                                    value="<%= mName %>"
                                    <% } %> 
                                      placeholder="请输入会议名称"/>
                                </td>
                            </tr>
                            <tr>
                                <td>开始时间：</td>
                                <td>
                                    <input type="text" id="startdate" name="startTime"
                                    <% if (startTime != null && !startTime.equals("")) { %>
                                    value="<%= startTime %>"
                                    <% } %> 
                                     placeholder="请输入会议开始时间" />
                                </td>
                            </tr>
                            <tr>
                                <td>结束时间：</td>
                                <td>
                                    <input type="text" id="enddate" name="endTime" 
                                    <% if (endTime != null && !endTime.equals("")) { %>
                                    value="<%= endTime %>"
                                    <% } %> 
                                     placeholder="请输入会议结束时间" />
                                </td>
                            </tr>
							<tr>
                                <td>会议室名称：</td>
                                <td>
                                    <select name="mrId" id="mrId">  
                                        <% 
					                    	MeetingRoomDao mrDao = new MeetingRoomDao();
					                   		ArrayList<MeetingRoom> mrList = mrDao.selectAllMr();
					                   		if (mrList.size() != 0) {
					                   			for (int i = 0; i < mrList.size(); i ++) {
					                   	%>  
                                     	<option
                                     	 <% if (mrId == mrList.get(i).getMrId()) { %>
                                   		selected="true"
                                    <% } %> 
                                     	
                                     	 value="<%= mrList.get(i).getMrId() %>">
                                     		<%= mrList.get(i).getMrName() %>
                                     	</option>
										
										<% } } %>
                                     </select>
                                </td>
                            </tr>
                            <tr>
                                <td>会议说明：</td>
                                <td>
                                    <textarea id="description" rows="5" name="mRemark"
                                     
                                     placeholder="请输入会议说明" ><% if (mRemark != null && !mRemark.equals("")) { %><%= mRemark %><% } %> </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>选择参会人员：</td>
                                <td>
                                    <div id="divfrom">
                                    
                                     <select id="selDepartments" onchange="changeDeptId()">
                                     
                                     <%

                                     DepartmentDao deptDao = new DepartmentDao();
                                     int deptId = 0;
                                 	if (request.getAttribute("deptId") != null) {
                                 		deptId = Integer.parseInt(request.getAttribute("deptId").toString());
                                 	} else {
                                 		deptId = deptDao.selectFirstDeptId();
                                 	}
                                 	
                         			ArrayList<Department> deptList = deptDao.selectAllDept();
                         			if (deptList.size() != 0) {
                         				for (int i = 0; i < deptList.size(); i ++) {
                                     %>
                                     <option 
                                     <% if (deptId == deptList.get(i).getDeptId()) { %>
                                     selected="true"
                                     <% } %>
                                     value="<%= deptList.get(i).getDeptId() %>"><%= deptList.get(i).getDeptName()%></option>
                                    <% } } %>
                                     
                                     
                                        </select>
                                        
              
                                        
                                        
                                        
                                        <select id="selEmployees" multiple="true">
                                        	<%
                                        	
                                        	
                                        	
                                        	EmployeeDao empDao = new EmployeeDao();
                                        	ArrayList<Employee> employeeList = empDao.selectUserByDeptId(deptId);
                                        	if (employeeList.size() != 0) {
                                        		for (int i = 0; i <employeeList.size(); i ++) {
                                        			if (employeeList.get(i).getEmpName() != null && !employeeList.get(i).getEmpName().equals("")) {
                                        	%>		
                                        	
                                        		<option value="<%= employeeList.get(i).getEmpId() %>"><%= employeeList.get(i).getEmpName() %></option>
                                        	<%
                                            		}
                                        		}
                                        	}
                                        	%>
                                        </select>
                                        
                                        
                                    </div>
                                    
                                    
                                    
                                    <div id="divoperator">
                                    
                                    	<%

                                    	ArrayList<Integer> empIdList = new ArrayList<Integer>();
                                    	String empIdList3 = (String)request.getAttribute("empIdList");
                                    	
                                    	if (empIdList3 != null) {
                                        	String[] empIdList2 = empIdList3.split(",");
                                        	for (int i = 0; i < empIdList2.length; i ++) {
                                        		if (!empIdList2[i].equals("")) {
                                        			empIdList.add(Integer.parseInt(empIdList2[i].trim()));
                                        		}
                                        	}
                                    	}
                                    	
                                    	

                   	
                                    	%>
                                    	
                                    	<input type="hidden"  id="changeDeptIdEmpIdList" <% if (empIdList.size() != 0) { %> value="<%= empIdList %>" <% } %>/>
                                    	
                                    	<input type="hidden" name="empIdList" id="empIdListElement" />
                                    	<input type="button" class="clickbutton" value="&gt;" onclick="selectEmployees()"/>
                                        <input type="button" class="clickbutton" value="&lt;" onclick="deSelectEmployees()"/>
                                        
                                       
                                       
                                       
                                       
                                       
                                       
      <script type="application/javascript">
        var changeDeptIdEmpIdList = document.getElementById("changeDeptIdEmpIdList");
        var empIdListElement = document.getElementById("empIdListElement");
        
        	var empIdList;
        	if (changeDeptIdEmpIdList.value == null || changeDeptIdEmpIdList.value == "") {
        		empIdList = [];
        	} else {
        		
        		var a = changeDeptIdEmpIdList.value.replace("[", "");
        		var b = a.replace("]", "");
        		empIdList = b.split(",");

        	}

            empIdListElement.value = empIdList;
        	
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
                
                empIdListElement.value = empIdList;
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
                empIdListElement.value = empIdList;
                
                opt.text = optEmployee.text;
                
                if (insertIndex == -1){
                    selSelectedEmployees.appendChild(opt);
                } else {
                    selSelectedEmployees.insertBefore(opt, options[insertIndex]);
                }
            }    
            
            function changeDeptId() {
            	var beChangedDeptId = document.getElementById("selDepartments");
            	var changedDeptId = document.getElementById("changedDeptId");
            	
            	var mName = document.getElementById("meetingname").value;
            	var startTime = document.getElementById("startdate").value;
            	var endTime = document.getElementById("enddate").value;
            	var mrId = document.getElementById("mrId").value;
            	var mRemark = document.getElementById("description").value;
            	
            	changedDeptId.setAttribute("href",
"ChangeDeptIdServlet?deptId=" + beChangedDeptId.value +"&empIdList=" + empIdList+"&mName=" + mName+"&startTime=" + startTime+"&endTime=" + endTime+"&mrId=" + mrId+"&mRemark=" + mRemark);
            	changedDeptId.click();
            }
            
            
        </script>
                                       
                                       
                                       
                                       
                                       
                                       
                                       
                                        
                                        
                                    </div>
                                    
                                    
                                    <div id="divto">
                                        <select id="selSelectedEmployees" multiple="true" name="empId">
                                        
                                        <%
                                        for (int i = 0; i < empIdList.size(); i ++) {
                                        	
                                        
                                        %>
                                        <option value="<%= empDao.selectById(empIdList.get(i)).getEmpId() %>"><%= empDao.selectById(empIdList.get(i)).getEmpName() %></option>
                                        
                                        <% } %>
                                        
                                        
                                        
                                        </select>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="command" colspan="2">
                                    <input type="submit" class="clickbutton" value="添加会议"/>
                                    <input type="reset" class="clickbutton" value="重置"/>
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
        	<% } message = null;%>
    </body>
</html>