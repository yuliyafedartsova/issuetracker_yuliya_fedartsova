<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.training.issuetracker.constants.Constants" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Welcome</title>
	</head>
	<body  bgcolor= "FA F0 E6">
				<a href='projects-review'> Projects </a><br>
			    <a href='property-review?property=type'> Types </a>&nbsp;
			    <a href='property-review?property=status'> Statuses </a>&nbsp;
			    <a href='property-review?property=resolution'> Resolutions</a>&nbsp;
			    <a href='property-review?property=priority'> Priorities </a> <br>
			    <a href='project-form?action=add'> Add project </a><br>
			    <a href='property-form?property=resolution&action=add'> Add resolution </a>&nbsp;
			    <a href='property-form?property=priority&action=add'> Add priority </a>&nbsp;
			    <a href='property-form?property=type&action=add'> Add type </a>&nbsp;<br>
			    <a href='user-form?action=add'>Add user</a><br>
			</body>
</html>