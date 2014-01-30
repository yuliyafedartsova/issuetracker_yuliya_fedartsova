<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp" %> 
	Add user:<br>
	<form action=''>
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
	</form>
</body>
</html>