<%@ page import="org.training.issuetracker.constants.Constants" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Login</title></head>
<body bgcolor= "FA F0 E6">
	<BR><BR>
	<c:if test="${not empty errorMessage}">
		<c:out value="${errorMessage}"/>
		<hr>
	</c:if>
	<form name="loginForm" method="POST" action="${path}/login">
		
	
		Email111:<br>
		<input type="text" name=<%= Constants.EMAIL %> value=""><br>
		Password:<br>
		<input type="password" name=<%= Constants.PASSWORD %> value=""><br>
		<input type= "submit" value="Login">
	</form>
	<hr>
</body></html>
