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
	<c:choose>
		<c:when test="${property eq 'status'}">
			Statuses: <br>
		</c:when>
		<c:when test="${property eq 'type'}">
			Types: <br>
		</c:when>
		<c:when test="${property eq 'priority'}">
			Priorities: <br>
		</c:when>
		<c:when test="${property eq 'resolution'}">
			Resolutions: <br>
		</c:when>
	</c:choose>
	<c:forEach var="parameter" items="${parametres}"> 
		<a href='ParameterUpdateView?property=${property}&id=${parameter.id}&value=${parameter}'>
	        ${parameter}</a><br>
	 </c:forEach>
   </body>
</html>