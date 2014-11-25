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
    
    <title>My JSP 'rolesResourcesList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  		权限管理   <a href="<%=path %>/login.do">返回</a><br/>
  		
  		<c:forEach items="${rolesList }" var="roles">
  			<form action="updatePermission.do" method="post">
  				<input type="hidden" id="roles_id" name="roles_id" value="${roles.id }">
	  			角色：${roles.name }
	  			<c:forEach items="${resourcesList}" var="resources">
	  				<input type="checkbox" name="resources" value="${resources.id }" <c:forEach items='${roles.resourcesList }' var='roles_resources'><c:if test='${roles_resources.id == resources.id }'>checked=checked</c:if></c:forEach> >${resources.memo }
	  			</c:forEach>
	  			<input type="submit" value="提交">
  			</form>
  		</c:forEach>
  		
  </body>
</html>
