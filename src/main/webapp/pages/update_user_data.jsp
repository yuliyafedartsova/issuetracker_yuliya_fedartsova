<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor= "FA F0 E6">
<%@ include file="header.jsp" %> 
	<c:if test="${not empty errorMessage}">
		<c:out value="${errorMessage}"/>
		<hr>
	</c:if>
	Update your data:<br>
	<form name='update' method='POST' action='update-user'>
	<table>
	<tr><td>First Name:</td>
	<td><input type='text' name='firstName' value='${user.firstName}'> </td></tr>
	<tr><td>Last Name:</td>
	<td><input type='text' name='lastName' value='${user.lastName}'></td></tr>
	<tr><td>Email Address:</td>
	<td><input type='text' name='email' value='${user.email}'></td></tr>
	<c:if test="${user.role eq 'Administrator'}">
		<tr><td>Role:</td>
		<td><select name='role' size='1'>
			<c:forEach var="role" items="${roles}"> 
	         <option value='${role.id}'> ${role} </option>
			</c:forEach>
		</select></td></tr>
	</c:if>
	</table>
	<input type="hidden" name='id' value='${user.id}'>
	<input type="hidden" name='action' value='update'>
	<input type="submit" value="Update data">
	</form>
	</body>
</html>