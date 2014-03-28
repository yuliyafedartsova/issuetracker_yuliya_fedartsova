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
	<c:choose>
		<c:when test="${property eq 'status'}">
			Update status: 
		</c:when>
		<c:when test="${property eq 'type'}">
			Update type: 
		</c:when>
		<c:when test="${property eq 'priority'}">
			Update priority: 
		</c:when>
		<c:when test="${property eq 'resolution'}">
			Update resolution: 
		</c:when>
	</c:choose>
	<br>
	<form action='${path}/property'>
	<input type='text' name='parameter'>${parameter}<br>
	<input type='hidden' name='property'  value='${property}'>
	<input type='hidden' name='id'  value='${parameter.id}'>
	<input type='hidden' name='action'  value='update'>
	<br>
	<input type="submit" value="Update">
	</form>
	</body>
</html>