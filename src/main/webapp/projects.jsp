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
	Projects: <br>
	<table>
		<tr><td> Name </td><td> Manager </td><td> Description </td><tr>
		<c:forEach var="project" items="${projects}"> 
			<tr><td><a href='project?action=update&id=${project.id}'> ${project.name}</a></td>
	    	<td> ${project.manager.firstName} &nbsp; ${project.manager.lastName}</td>
	 		<td> ${project.description} </td></tr>
	 	</c:forEach>
	</table>
</body>
</html>