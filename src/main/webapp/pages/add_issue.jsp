<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="pages/scripts.js"></script>
<link rel="stylesheet" type="text/css" href="pages/tracker.css">
</head>
<body bgcolor= "FA F0 E6">
<%@ include file="header.jsp" %> &nbsp;
	<BR><BR>
	<c:if test="${not empty errorMessage}">
		<c:out value="${errorMessage}"/>
		<hr>
	</c:if>
	<c:if test="${not empty message}">
		<c:out value="${message}"/>
		<hr>
	</c:if>
	Add issue: <br>
	<form action='issue'>
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
	         <option value='${project.id}' onclick="showVersions('${project.id}');"> ${project.name} </option>
		</c:forEach>
	</select></td></tr>
	<tr><td>Build found: </td>
	<td>
	<c:forEach var="project" items="${projects}"> 
	   <select name='version' size='1' id='${project.id}' class="versions">
	    	<c:forEach var="version" items="${project.buildVersions}">   
	         <option value='${version.id}'> ${version} </option>
			</c:forEach>
	    </select>
	</c:forEach>
	</td>
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
	<input type="hidden" name='action' value='add'>
	<input type="submit" value="Add">
	</form>
</body>
</html>