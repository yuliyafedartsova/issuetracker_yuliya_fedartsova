<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp" %> &nbsp;
	Update issue :<br>
	<table>
	<tr><td> Id:&nbsp; </td> ${issue.id} <td></td></tr>
	<tr><td>Create Date:&nbsp;</td><td> ${issue.createDate} </td></tr>
	<tr><td>Created By</td><td> ${issue.author.firstName} &nbsp; ${issue.author.lastName} </td></tr>
	<c:if test="${issue.modifyDate != null}">
		Modify Date:&nbsp; ${issue.modifyDate} <br>
		Modified By:&nbsp; ${issue.modifier.firstName} &nbsp; ${issue.modifier.lastName} <br>
	</c:if>
	<form action=''>
		<tr><td>Summary</td>
    	<td><input type='text' name='summary' value="${issue.summary}"></td></tr>
    	<tr><td>Description</td>
    	<td><input type='text' name='description' value="${issue.description}"></td></tr>
    	<tr><td> Status </td>
    	<td><select name='status' size='1'>
    		<c:forEach var="status" items="${statuses}"> 
	        	<option value='${status.id}'> ${status} </option>
			</c:forEach>
	    </select></td></tr>
    	<tr><td> Type </td>
    	<td><select name='type' size='1'>
    		<c:forEach var="type" items="${types}"> 
	        	<option value='${type.id}'> ${type} </option>
			</c:forEach>
	    </select></td></tr>
   		<tr><td> Priority </td>
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
	    <tr><td> Build found </td>
    	<td><select name='version' size='1'>
    		<c:forEach var="project" items="${projects}"> 
	        	<c:forEach var="version" items="${project.buildVersions}"> 
	        		<option value='${version.id}'> ${version} </option>
	        	</c:forEach>
	        </c:forEach>
	    </select></td></tr>
	   <tr><td> Assignee </td>
    	<td><select name='assignee' size='1'>
    		<c:forEach var="user" items="${users}"> 
	        	<option value='${user.id}'> ${user.firstName} &nbsp; ${user.lastName} </option>
			</c:forEach>
	    </select></td></tr>
	  </form>
	 <table>
   </body>
</html>