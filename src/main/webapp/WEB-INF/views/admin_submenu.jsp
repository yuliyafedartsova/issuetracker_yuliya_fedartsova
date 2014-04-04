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
				<a href='/issuetracker/review-projects'> Projects </a><br>
			    <a href='/issuetracker/property-review?property=type'> Types </a>&nbsp;
			    <a href='/issuetracker/property-review?property=status'> Statuses </a>&nbsp;
			    <a href='/issuetracker/property-review?property=resolution'> Resolutions</a>&nbsp;
			    <a href='/issuetracker/property-review?property=priority'> Priorities </a> <br>
			    <a href='/issuetracker/form-project-add'> Add project </a><br>
			    <a href='/issuetracker/property-form-add?property=resolution'> Add resolution </a>&nbsp;
			    <a href='/issuetracker/property-form-add?property=priority'> Add priority </a>&nbsp;
			    <a href='/issuetracker/property-form-add?property=type'> Add type </a>&nbsp;<br>
			    <a href='/issuetracker/user-form-add'>Add user</a><br>
			</body>
</html>