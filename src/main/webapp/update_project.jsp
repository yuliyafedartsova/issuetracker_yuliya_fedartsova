<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp" %> &nbsp;
	Update project: <br>	
	<form action=''>
	<table>
		<tr><td> Name:&nbsp; </td>
		<td><input type='text' name='name' value='${value}'></td></tr>
		<tr><td> Description:&nbsp;</td>
		<td><input type='text' name='description' value=''></td></tr>
		<tr><td>Builds:&nbsp;</td>
		<td><select name='build' size='1'>
			<c:forEach var="version" items="${project.buildVersions}"> 
	         	<option value='${version.id}'> ${version} </option>
		    </c:forEach>
		</select></td></tr>
	
		<tr><td>Manager:&nbsp;</td>
		<td><select name='manager' size='1'>
			<c:forEach var="user" items="${users}"> 
	         	<option value='${user.id}'> ${user.firstName} &nbsp; ${user.lastName} </option>
		    </c:forEach>
		</select></td></tr>
	</table>
    </form>
</body>
</html>