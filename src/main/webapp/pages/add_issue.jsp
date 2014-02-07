<%@ page import="org.training.issuetracker.constants.Constants" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Login</title></head>
<script src="pages/scripts.js"></script>
<link rel="stylesheet" type="text/css" href="pages/tracker.css">
<body>
	<%@ include file="header.jsp" %> &nbsp;
	<BR><BR>
	 Add issue: <br>
	<form action='add-issue'>
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
	         <option value='${project.id}' onclick="showVersions('${project.name}');"> ${project.name} </option>
		</c:forEach>
	</select></td></tr>
	
	<tr><td>Build found: </td>
	
	<c:forEach var="project" items="${projects}"> 
	    	
	    	<td><select name='version' size='1' id='${project.id}' class="versions">
	    		<c:forEach var="version" items="${project.buildVersions}">   
	         	<option value='${version.id}'> ${version} </option>
				</c:forEach>
	    	</select></td>
	    	
	    	
	
	
	</c:forEach>
	</tr>
	<tr><td>Assignee:</td>
	<td><select name='assignee' size='1'>
		<option value=''> Not assigned </option>
		<c:forEach var="user" items="${users}"> 
	         <option value='${user.id}'> ${user.firstName} &nbsp; ${user.lastName}</option>
		</c:forEach>
	</select></td></tr>
	</table>
	<input type='hidden' name='author' value='${user.id}'>
	<input type="submit" value="Add">
	</form>
	
	<div id="test"> hi </div>
 </body>
</html>