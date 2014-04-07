<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript" src="pages/scripts.js"></script>
<link rel="stylesheet" type="text/css" href="pages/tracker.css">

<script type="text/javascript">
function addVersion2(id) {
	var f = document.getElementById("issue-form");
	f.versionId.value = id;
}
</script>
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
	<form method='post' id="issue-form" action='/issuetracker/add-issue'>
	<table>
	<tr><td> Summary: </td><td><input type='text' name='summary'></td></tr>
	<tr><td>Description: </td>
	<td><textarea name="description" rows="4" cols="50"></textarea></td></tr>
	<tr><td> Status: </td>
		<td> <select name='statusId' size='1'>
	         <c:forEach var="status" items="${statuses}"> 
	         	<option value='${status.id}'> ${status} </option>
			 </c:forEach>
		</select></td>
	</tr>
	<tr><td>Type: </td>
	<td><select name='typeId' size='1'>
		<c:forEach var="type" items="${types}"> 
	         <option value='${type.id}'> ${type} </option>
		</c:forEach>
	</select></td>
	</tr>
	<tr><td>Priority: </td>
	<td><select name='priorityId' size='1'>
		<c:forEach var="priority" items="${priorities}"> 
	         <option value='${priority.id}'> ${priority} </option>
		</c:forEach>
	</select></td></tr>
	<tr><td> Project </td>
	<td><select name='projectId' size='1'>
		<c:forEach var="project" items="${projects}"> 
	         <option value='${project.id}' onclick="showVersions('${project.id}');"> ${project.name} </option>
		</c:forEach>
	</select></td></tr>
	<tr><td>Build found: </td>
	<td>
	<c:forEach var="project" items="${projects}"> 
	   <select size='1' id='${project.id}' class="versions">
	    	<c:forEach var="version" items="${project.versions}">   
	         	<option onclick="addVersion2('${version.id}');"> ${version.name} </option>
			</c:forEach>
	    </select>
	</c:forEach>
	</td>
	</tr>
	<tr><td>Assignee:</td>
	<td><select name='assigneeId' size='1'>
		<option value=''> Not assigned </option>
		<c:forEach var="user" items="${users}"> 
	         <option value='${user.id}'> ${user.firstName} &nbsp; ${user.lastName}</option>
		</c:forEach>
	</select></td></tr>
	</table>
	<input type="hidden" name='action' value='add'>
	<input type="hidden" name='versionId'>
	<input type="submit" value="Add">
	</form>
</body>
</html>