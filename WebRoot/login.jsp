<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  <body>
    <form name="f" action="/SpringSecurity/j_spring_security_check" method="post">
    	username:<input type="text" name="j_username">
    	pwd:<input type="password" name="j_password">
    	<input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/>记住我
    	<input type="submit" value="登录">
    	${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
    </form>
    
  </body>
</html>
