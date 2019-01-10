<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                    会议室管理 > 添加会议室
                </div>
                
                <%
               
                String mrRemark = (String)request.getAttribute("mrRemark");
                int mrStatus = 0;
                if (request.getAttribute("mrStatus") != null) {
                	mrStatus = Integer.parseInt(request.getAttribute("mrStatus").toString());
                }

                int mrNum = 0;
                if (request.getAttribute("mrNum") != null) {
                	mrNum = Integer.parseInt(request.getAttribute("mrNum").toString());
                }

                int mrCapacity = 0;
                if (request.getAttribute("mrCapacity") != null) {
                	mrCapacity = Integer.parseInt(request.getAttribute("mrCapacity").toString());
                }
                %>
                
                <form action="AddMRServlet">
                    <fieldset>
                        <legend>会议室信息</legend>
                        <table class="formtable">
                            <tr>
                                <td>门牌号:</td>
                                <td>
                                    <input id="roomnumber" type="text" placeholder="例如：201" maxlength="10" name="mrNum"
                                    <% if (mrNum != 0) { %>
                                    value="<%= mrNum %>"
                                    <% } %>
                                    
                                    />
                                </td>
                            </tr>
                            <tr>
                                <td>会议室名称:</td>
                                <td>
                                    <input id="capacity" type="text" placeholder="例如：第一会议室" maxlength="20" name="mrName"/>
                                </td>
                            </tr>
                            <tr>
                                <td>最多容纳人数：</td>
                                <td>
                                    <input id="roomcapacity" type="text" placeholder="填写一个正整数" name="mrCapacity"
                                    <% if (mrCapacity != 0) { %>
                                    value="<%= mrCapacity %>"
                                    <% } %>/>
                                </td>
                            </tr>
                            <tr>
                                <td>当前状态：</td>
                                <td>
                                    <input type="radio" id="mrStatus" name="mrStatus"
                                     <% if (mrStatus == 0) { %>
                                      checked="checked"
                                    <% } %>
                                    value="0"/><label for="mrStatus">启用</label>
                                    <input type="radio" id="mrStatus"
                                     <% if (mrStatus == 1) { %>
                                      checked="checked"
                                    <% } %>
                                     name="mrStatus" value="1"/><label for="mrStatus" >停用</label>
                                </td>
                            </tr>
                            <tr>
                                <td>备注：</td>
                                <td>
                                    <textarea id="description" maxlength="200" rows="5" cols="60" placeholder="200字以内的文字描述" name="mrRemark"><% if (mrRemark != null && !mrRemark.equals("")) { %><%= mrRemark %><% } %></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="command">
                                    <input type="submit" value="添加" class="clickbutton"/>
                                    <input type="reset" value="重置" class="clickbutton"/>
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