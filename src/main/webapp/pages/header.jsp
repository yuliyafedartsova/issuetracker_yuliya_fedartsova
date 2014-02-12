<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
<link rel="stylesheet" type="text/css" href="pages/tracker.css">
</head>
<body bgcolor= "FA F0 E6">
	<c:choose>
		  <c:when test="${user.role.name != 'Guest'}">
		  		Hello, ${user.firstName} <br><br>
		  		<a href='main'> Main page </a><br>
		  		<a href='/issuetracker/pages/change_password.jsp'> Change password </a> <br>
		  		<a href='user-form?action=update'> Update your data </a> <br>
		  		<a href='logout'> Logout </a> <br><br>
		  </c:when>
          <c:otherwise>
          		<a href='main'> Main page </a><br>
                <%@ include file="login.jsp" %>
          </c:otherwise>
	</c:choose>
</body>
</html>