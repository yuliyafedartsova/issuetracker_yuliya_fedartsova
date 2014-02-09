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
			Add status: <br>
		</c:when>
		<c:when test="${property eq 'type'}">
			Add type: <br>
		</c:when>
		<c:when test="${property eq 'priority'}">
			Add priority: <br>
		</c:when>
		<c:when test="${property eq 'resolution'}">
			Add resolution: <br>
		</c:when>
	</c:choose>
	<form action='property'>
   	<input type='text' name='parameter' value=''>
    <br><br>
    <input type="hidden" name='action' value='add'>
    <input type="hidden" name='property' value='${property}'>
    <input type="submit" value="Submit">
    </form>
   </body>
</html>