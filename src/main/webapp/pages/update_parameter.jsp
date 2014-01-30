<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp" %> 
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
	${parameter}
	<form action=''>
	<input type='text' name='value' value='${parameter}'><br>
	<input type='hidden' name='property'  value='${parameter}'>
	<input type='hidden' name='id'  value='${parameter.id}'>
	</form>
	</body>
</html>