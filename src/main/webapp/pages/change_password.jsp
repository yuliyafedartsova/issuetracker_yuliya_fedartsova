<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp" %> 
	Change password: 
	<br>
<form name="change_password" method="POST" action="LoginController">
	New Password:<br>
	<input type="password" name="NewPassword" value=""><br>
	Password Confirmation:<br>
	<input type="password" name="Confirmation" value=""><br>
	<input type="submit" value="Enter">
	<br>
</form>
</body>
</html>