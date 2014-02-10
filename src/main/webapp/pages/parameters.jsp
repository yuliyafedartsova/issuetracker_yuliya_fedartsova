<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor= "FA F0 E6">
<%@ include file="header.jsp" %> 
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
		<a href='property-form?action=update&property=${property}&id=${parameter.id}'>
	        ${parameter}</a><br>
	 </c:forEach>
   </body>
</html>