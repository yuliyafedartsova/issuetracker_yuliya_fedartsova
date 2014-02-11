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
<%@ include file="header.jsp" %> 
	<c:if test="${not empty errorMessage}">
		<c:out value="${errorMessage}"/>
		<hr>
	</c:if>
	Update issue :<br><br>
	<table>
	<tr><td> Id:&nbsp; </td> <td>${issue.id}</td></tr>
	<tr><td>Create Date:&nbsp;</td><td> ${issue.createDate} </td></tr>
	<tr><td>Created By</td><td> ${issue.author.firstName} &nbsp; ${issue.author.lastName} </td></tr>
	<c:if test="${issue.modifyDate != null}">
		<tr><td>Modify Date:&nbsp;</td><td> ${issue.modifyDate} </td></tr>
		<tr><td>Modified By:&nbsp;</td><td> ${issue.modifier.firstName} &nbsp; ${issue.modifier.lastName} </td></tr>
	</c:if>
	</table>
	<form action='issue'>
		<c:choose>
		<c:when test="${issue.status.name eq 'Closed'}">
		
		 <table>
		<tr><td>Summary</td><td> ${issue.summary} </td></tr>
    	<tr><td>Description</td><td> ${issue.description} </td></tr>
    	<tr><td> Status </td>  
    	<td><select name='status' size='1'>
    		<c:forEach var="status" items="${statuses}"> 
	        	<option value='${status.id}'> ${status} </option>
			</c:forEach>
	    </select></td></tr>
    	<tr><td>Resolution</td><td> ${issue.resolution} </td></tr>
    	<tr><td>Type</td><td> ${issue.type} </td></tr>
		<tr><td>Priority</td><td> ${issue.priority} </td></tr>  	
		<tr><td>Project</td><td> ${issue.project.name} </td></tr>    	
		<tr><td>Build found</td><td> ${issue.buildFound} </td></tr>    	
		<tr><td>Assignee</td><td> ${issue.assignee.firstName} &nbsp; ${issue.assignee.lastName} </td></tr>  	
		</table>
		<input type="hidden" name='action' value='reopen'>
		 </c:when>
		<c:otherwise>
		<table>
		<tr><td>Summary</td>
    	<td><input type='text' name='summary' value="${issue.summary}"></td></tr>
    	<tr><td>Description</td>
    	<td><input type='text' name='description' value="${issue.description}"></td></tr>
    	<tr><td> Status </td>
    	<td><select name='status' size='1'>
    		<c:forEach var="status" items="${statuses}"> 
	        	<c:choose>
		  			<c:when test="${status.name eq 'Closed'}">
		  				<option value='${status.id}' onclick="showResolutions();"> ${status} </option>
		  			</c:when>
		  			<c:when test="${status.name eq 'Resolved'}">
		  				<option value='${status.id}' onclick="showResolutions();"> ${status} </option>
		  			</c:when>
		  			<c:otherwise>
		  				<option value='${status.id}'> ${status} </option>
		  			</c:otherwise>
		       	</c:choose>
	        </c:forEach>
	    </select></td></tr>
    	<tr><td> Resolution </td>  
    	<td><select id="res" name='resolution' size='1'>
    		<option></option>
    		<c:forEach var="resolution" items="${resolutions}"> 
	        	<option value='${resolution.id}'> ${resolution} </option>
			</c:forEach>
	    </select></td></tr>
    	<tr><td> Type </td>
    	<td><select name='type' size='1'>
    			<option value='${issue.type.id}'> ${issue.type} </option>
    		<c:forEach var="type" items="${types}"> 
	        	<c:if test="${issue.type.name !=  type.name}">
					 <option value='${type.id}'> ${type} </option>
				</c:if>
	        </c:forEach>
	    </select></td></tr>
   		<tr><td> Priority </td>
    	<td><select name='priority' size='1'>
    		<option value='${issue.priority.id}'> ${issue.priority} </option>
    		<c:forEach var="priority" items="${priorities}"> 
	        	<c:if test="${issue.priority.name != priority.name}">
					<option value='${priority.id}'> ${priority} </option>
				</c:if>
	        </c:forEach>
	    </select></td></tr>
	    <tr><td> Project </td>
    	<td><select name='project' size='1'>
    		<option value='${issue.project.id}'> ${issue.project.name} </option>
    		<c:forEach var="project" items="${projects}"> 
	        	<c:if test="${issue.project.id != project.id}">
					<option value='${project.id}'> ${project.name} </option>
				</c:if>
	        </c:forEach>
	    </select></td></tr>
	    <tr><td> Build found </td>
    	<td><select name='version' size='1'>
    		    <option value='${issue.buildFound.id}'> ${issue.buildFound.name} </option>
    		    <c:forEach var="version" items="${issue.project.buildVersions}"> 
	        		<c:if test="${issue.buildFound.name != version.name}">
						<option value='${version.id}'> ${version} </option>
					</c:if>
	        	</c:forEach>
	      </select></td></tr>
	   <tr><td> Assignee </td>
    	<td><select name='assignee' size='1'>
    		<option value='${issue.assignee.id}'> ${issue.assignee.firstName} &nbsp; ${issue.assignee.lastName}</option>
    		<c:forEach var="user" items="${users}"> 
	        	<c:if test="${issue.assignee.id != user.id}">
					<option value='${user.id}'> ${user.firstName} &nbsp; ${user.lastName} </option>
				</c:if>
	        </c:forEach>
	    </select></td></tr>
	    </table>
	   </c:otherwise>
	   </c:choose>
	    <input type="hidden" name='id' value='${issue.id}'>
	    <input type="hidden" name='action' value='update'>
	    <br>
	    <input type="submit" value="Update issue">
	   </form>
	   
	 </body>
</html>