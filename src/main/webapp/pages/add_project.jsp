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
	Add project:<br>
	<form action='project'>
    <table>
    <tr><td> Name: &nbsp;</td>
	<td><input type='text' name='name' value=''></td></tr>
	<tr><td>Description: &nbsp;</td>
	<td><textarea name="description" rows="4" cols="50">
    </textarea></td></tr>
	<tr><td>Build: &nbsp;</td>
	<td><input type='text' name='version' value=''></td></tr>
	<tr><td>Manager: &nbsp;</td>
	<td><select name='manager' size='1'>
		<c:forEach var="user" items="${users}"> 
	         <option value='${user.id}'> ${user.firstName} &nbsp; ${user.lastName}</option>
		</c:forEach>
	</select></td></tr>
	</table>
	<input type="hidden" name='action' value='add'>
	<input type="submit" value="Add">
	</form>
</body>
</html>