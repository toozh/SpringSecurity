<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcome.jsp' starting page</title>
    
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
    <span>登录成功！</span>
	当前用户：<sec:authentication property="name"/>
	
	<sec:authorize ifAllGranted="AUTH_AUTH_MANAGE">
		<a href="<%=path %>/showRolesResources.do">权限管理</a>
	</sec:authorize>
	
	<a href="<%=path %>/j_spring_security_logout">退出</a><br/>
	
	<sec:authorize ifAllGranted="AUTH_USERS_INSERT">
		<a href="<%=path %>/showInsert.do">新增用户</a>
	</sec:authorize>
	
	<sec:authorize ifAllGranted="AUTH_USERS_VIEW">
		<table>
			<tr>
				<td>用户名</td>
				<td>密码</td>
				<td>插入时间</td>
				<td>更新时间</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${usersList }" var="user">
				<tr>
					<td>${user.user_name }</td>
					<td>${user.user_pwd }</td>
					<td>${user.insert_time }</td>
					<td>${user.update_time }</td>
					<td>
						<sec:authorize ifAllGranted="AUTH_USERS_UPDATE">
							<a href="<%=path %>/showUpdate.do">更新用户</a>
						</sec:authorize>
						<sec:authorize ifAllGranted="AUTH_USERS_DELETE">
							<a href="<%=path %>/delete.do" onclick="return confirm('删除是不可恢复的，你确认要删除吗？')">删除用户</a>
						</sec:authorize>
					</td>
				</tr>
			</c:forEach>
		</table>
		
	</sec:authorize>
	
	
	
  </body>
</html>
