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
			<%@ include file="header.jsp" %> &nbsp;
			<c:if test="${user.role != 'Guest'}">
				<a href='submit-issue'> Submit Issue </a> <br>
			</c:if>
	        <c:if test="${user.role eq 'Administrator'}">
				<a href='projects'> Projects </a>
			    <a href='property?property=type'> Types </a>
			    <a href='property?property=status'> Statuses </a>
			    <a href='property?property=resolution'> Resolutions</a>
			    <a href='property?property=priority'> Priorities </a> <br>
			    <a href='project?action=add'> Add project </a>
			    <a href='user?action=add'>Add user</a>
			</c:if>
			<form name="main" method="POST" action='main'>
		    <select name='sorting' size='1'>
		    	<option value=id> Sort by id </option>
		        <option value=type> Sort by type </option>
		        <option value=priority> Sort by priority </option>
		        <option value=assignee> Sort by assignee </option>
		        <option value=status> Sort by status </option>
		   </select>
		   <input type='submit' value='Sort'> 
		   </form> <br>
		   
		  <c:choose>
		  <c:when test="${not empty errorMessage}">
		  		<c:out value="${errorMessage}"/>
				<hr>
		  </c:when>
		  <c:otherwise>
		  <table>  
		   <tr> <td> Id </td> <td> Priority </td> <td> Assignee </td> <td> Type </td>
		   <td> Status </td> <td> Summary </td> </tr>
		   <c:forEach var="issue" items="${issues}"> 
		      <tr>
		      	<td> ${issue.id} </td>
		        <td> ${issue.priority} </td>
		        <c:choose>
					<c:when test="${issue.assignee != null}">
					 	<td> ${issue.assignee.firstName} &nbsp; ${issue.assignee.lastName} </td>
				    </c:when>
				    <c:otherwise>
						<td>  </td>			
					</c:otherwise>
		       </c:choose>
		        <td> ${issue.type} </td>
		        <td> ${issue.status} </td>
		        <td> ${issue.summary} </td>
		      </tr>
		   </c:forEach>
		   </table>
		   </c:otherwise>
		       </c:choose>
	</body>
</html>
