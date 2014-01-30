<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
</head>
<body>
	<a href='main'> Main page </a>
     <c:choose>
		  <c:when test="${user.role != 'Guest'}">
		  		Hello, ${user.firstName} <br><br>
		  		<a href='/issuetracker/pages/change_password.jsp'> Change password </a> <br>
		  		<a href='user?action=update'> Update your data </a> <br>
		  		<a href='logout'> Logout </a> <br><br>
		  </c:when>
          <c:otherwise>
                <%@ include file="login.jsp" %>
          </c:otherwise>
		       </c:choose>
</body>
</html>