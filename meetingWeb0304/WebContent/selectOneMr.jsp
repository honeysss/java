<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>527会议管理系统</title>
        <link rel="stylesheet" href="styles/common.css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style>
    .formtable tr td:first-child{
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
            会议室管理 > 查看会议室   > 查看详细信息
                </div>
                <form>
                
                
                <%
                	int mrNum = Integer.parseInt(request.getAttribute("mrNum").toString());
	            	String mrName = (String)request.getAttribute("mrName");
	            	int mrCapacity = Integer.parseInt(request.getAttribute("mrCapacity").toString());
	            	int mrStatus = Integer.parseInt(request.getAttribute("mrStatus").toString());
	            	String mrRemark = (String)request.getAttribute("mrRemark");
                
                %>
                
                
                    <fieldset>
                        <legend>会议室信息</legend>
                        <table class="formtable">
                            <tr>
                                <td>门牌号:</td>
                                <td>
                                    
                                    <span id="roomnumber" ><%= mrNum %></span>
                                </td>
                            </tr>
                            <tr>
                                <td>会议室名称:</td>
                                <td>
                  
                                    <span id="capacity" ><%= mrName %></span>
                                </td>
                            </tr>
                            <tr>
                                <td>最多容纳人数：</td>
                                <td>
                                    <span id="roomcapacity" ><%= mrCapacity %></span>
                                </td>
                            </tr>
                            <tr>
                                <td>当前状态：</td>
                                <td>
                                    <% if(mrStatus == 0) {  %>
                                     <span >启用</span>
                                     <% } else { %>
                                     <span >停用</span>
                                     <% } %>
                                     
                                  </td>
                            </tr>
                            <tr>
                                <td>备注：</td>
                                <td>
                                    <span id="description" ><%= mrRemark %></span>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="command">
                                    <a type="button" class="clickbutton"
                                    style="background-color:white;color:black;" 
                                    href="ClearMrMesServlet"
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