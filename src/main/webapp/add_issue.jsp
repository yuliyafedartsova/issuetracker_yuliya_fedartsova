<%@ page import="org.training.issuetracker.constants.Constants" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Login</title></head>
<body bgcolor= "FA F0 E6">
	<%@ include file="header.jsp" %> &nbsp;
	<BR><BR>
	Add issue: <br>
	<form action=''>"
	<table>
	<tr><td> Summary:  </td><td><input type='text' name='summary' value=''></td></tr>
	<tr><td>Description: </td>
	<td><input type='text' name='description' value=''></td></tr>
	<tr><td> Status: </td>
		<td> <select name='status' size='1'>
	         <c:forEach var="status" items="${statuses}"> 
	         	<option value='${status.id}'> ${status} </option>
			 </c:forEach>
		</select></td>
	</tr>
	<tr><td>Type: </td>
	<td><select name='type' size='1'>
		<c:forEach var="type" items="${types}"> 
	         <option value='${type.id}'> ${type} </option>
		</c:forEach>
	</select></td>
	</tr>
	<tr><td>Priority: </td>
	<td><select name='priority' size='1'>
		<c:forEach var="priority" items="${priorities}"> 
	         <option value='${priority.id}'> ${priority} </option>
		</c:forEach>
	</select></td></tr>
	<tr><td> Project </td>
	<td><select name='project' size='1'>
		<c:forEach var="project" items="${projects}"> 
	         <option value='${project.id}'> ${project.name} </option>
		</c:forEach>
	</select></td></tr>
	<tr><td>Build found: </td>
	<td><select name='version' size='1'>
		<c:forEach var="project" items="${projects}"> 
	    	<c:forEach var="version" items="${project.buildVersions}">   
	         	<option value='${version.id}'> ${version} </option>
			</c:forEach>
		</c:forEach>
	</select></td></tr>
	<tr><td>Assignee:</td>
	<td><select name='assignee' size='1'>
		<c:forEach var="user" items="${users}"> 
	         <option value='${user.id}'> ${user.firstName} &nbsp; ${user.lastName}</option>
		</c:forEach>
	</select></td></tr>
	</table>
	</form>
 </body>
</html>