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
	Add user:<br>
	<form action='user'>
    <table>
    <tr><td> First name: &nbsp;</td>
	<td><input type='text' name='firstName' value=''></td></tr>
	<tr><td>Last name: &nbsp;</td>
	<td><input type='text' name='lastName' value=''></td></tr>
	<tr><td> Email address: &nbsp;</td>
	<td><input type='text' name='email' value=''></td></tr>
	<tr><td> Role: &nbsp;</td>
	<td><select name='role' size='1'>
		<c:forEach var="role" items="${roles}"> 
	        <c:if test="${role.name != 'Guest'}">
				<option value='${role.id}'> ${role} </option>
			</c:if>
	   </c:forEach>
	</select></td></tr>
	<tr><td> Password: &nbsp;</td>
	<td><input type='text' name='password' value=''></td></tr>
	<tr><td>Confirm password: &nbsp;</td>
	<td><input type='text' name='password2' value=''></td></tr>
	</table>
	<input type="hidden" name='action' value='add'>
	<input type="submit" value="Add user">
	</form>
</body>
</html>
