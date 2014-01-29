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
			Update status: <br>
		</c:when>
		<c:when test="${property eq 'type'}">
			Update type: <br>
		</c:when>
		<c:when test="${property eq 'priority'}">
			Update priority: <br>
		</c:when>
		<c:when test="${property eq 'resolution'}">
			Update resolution: <br>
		</c:when>
	</c:choose>
	${value}
	<form action=''>
	<input type='text' name='value' value='${value}'><br>
	<input type='hidden' name='property'  value='${property}'>
	<input type='hidden' name='id'  value='${id}'>
	</form>
	</body>
</html>