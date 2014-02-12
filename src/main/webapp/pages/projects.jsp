<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor= "FA F0 E6">
<%@ include file="header.jsp" %> 
	<c:if test="${not empty message}">
		<c:out value="${message}"/>
		<hr>
	</c:if>
	Projects: <br>
	<table>
		<tr><td> Name </td><td> Manager </td><td> Description </td><tr>
		<c:forEach var="project" items="${projects}"> 
			<tr><td><a href='${path}/project-form?action=update&id=${project.id}'> ${project.name}</a></td>
	    	<td> ${project.manager.firstName} &nbsp; ${project.manager.lastName}</td>
	 		<td> ${project.description} </td></tr>
	 	</c:forEach>
	</table>
</body>
</html>