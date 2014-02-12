<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="pages/tracker.css">
</head>
<body bgcolor= "FA F0 E6">
<%@ include file="header.jsp" %> 
	<c:if test="${not empty errorMessage}">
		<c:out value="${errorMessage}"/>
		<hr>
	</c:if>
	Change password: 
	<br><br>
<form name="change_password" method="POST" action="${path}/change-password">
	New Password:<br>
	<input type="password" name="password" value=""><br>
	Password Confirmation:<br>
	<input type="password" name="password2" value=""><br>
	<input type="hidden" name='id' value='${user.id}'>
	<input type="hidden" name='action' value='change'>
	<input type="submit" value="Enter">
	<br>
</form>
</body>
</html>