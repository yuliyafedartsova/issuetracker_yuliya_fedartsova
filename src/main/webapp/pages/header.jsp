<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<html>
<head>
<title>header</title>
</head>
<body bgcolor= "FA F0 E6">
	<c:choose>
		  <c:when test="${user.role.name != 'Guest'}">
		  		Hello, ${user.firstName} <br><br>
		  		<a href='${path}/main'> Main page </a><br>
		  		<a href='${path}/pages/change_password.jsp'> Change password </a> <br>
		  		<a href='${path}/user-form?action=update'> Update your data </a> <br>
		  		<a href='${path}/logout'> Logout </a> <br><br>
		  </c:when>
          <c:otherwise>
          		<a href='${path}/main'> Main page </a><br>
                <%@ include file="login.jsp" %>
          </c:otherwise>
	</c:choose>
</body>
</html>