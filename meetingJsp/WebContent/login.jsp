<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>527会议管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="stylesheet" href="styles/common.css"/>
        <style>
	        .wrongName, .wrongPwd, .noName, .noPwd {
				color: red;
				font-size: 12px;
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
              <strong></strong>
                <a href="changepassword.html"></a>
            </div>
        </div>
        <div class="page-body">
           
            <div class="page-content">
                <div class="content-nav">
                  	  登录
                </div>
                <form action="LoginServlet">
                    <fieldset>
                        <legend>登录信息</legend>
                        <table class="formtable" style="width:50%">
                            <tr>
                                <td>账号名:</td>
                                <td>
                                    <input id="accountname" type="text" name="username"
                                    placeholder="请输入您的姓名"/>&nbsp;&nbsp;&nbsp;
                                      
                                    <% 
                                    int res = 0;
                                    if (request.getAttribute("res") != null) {
                                    	res = Integer.parseInt(request.getAttribute("res").toString()); 
                                    }
                                    
                                    %>
                                    
                                    <% if (res == 3) { %>
                                    <span class="wrongName">用户名有误</span>
                                    <% } %>
                                    
                                    <% if (res == 1) { %>
                                    <span class="noName">用户名不能为空</span>
                                    <% } %>
                                </td>
                            </tr>
                            <tr>
                                <td>密码:</td>
                                <td>
                                    <input id="new" type="password" name="pwd"
                                     placeholder="请输入您的密码"/>&nbsp;&nbsp;&nbsp;
                                    <% if (res == 4) { %>
                                     <span class="wrongPwd">密码有误</span>
                                    <% } %>
                                    
                                    <% if (res == 2) { %>
                                     <span class="noPwd">密码不能为空</span>
                                    <% } %>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="command">
                                    <input type="submit" value="登录" class="clickbutton" id="loginSubmit"/>
                                    <input type="button" value="注册" class="clickbutton" onclick="window.location.href='userRegister.jsp';"/>
                                   
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <div class="page-footer">
            <hr/>
        		    更多问题，欢迎联系
        		    <a href="mailto:webmaster@eeg.com">管理员</a>
            <img src="images/footer.png" alt="CoolMeeting"/>
        </div>
        
        <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    </body>
</html>