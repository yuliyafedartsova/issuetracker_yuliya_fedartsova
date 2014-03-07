<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>${title}</title>
	</head>
	<body>
		<h1>${message}</h1>	
		<a href='/issuetracker/hello/test'> test with id </a>
		<br>
		<a href='/issuetracker/hello?id=2&name=version1'> create version </a>
	</body>
</html>