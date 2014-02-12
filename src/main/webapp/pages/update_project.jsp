<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor= "FA F0 E6">
<%@ include file="header.jsp" %>
	<c:if test="${not empty errorMessage}">
		<c:out value="${errorMessage}"/>
		<hr>
	</c:if>
	Update project: <br>	
	<form action='project'>
	<table>
		<tr><td> Name:&nbsp; </td>
		<td><input type='text' name='name' value='${project.name}'></td></tr>
		<tr><td> Description:&nbsp;</td>
		<td> <textarea name="description" rows="4" cols="50">${project.description}
    	</textarea></td></tr>
    	
	 <tr><td>Builds:&nbsp;</td>
		<td><select size='1'>
			<c:forEach var="version" items="${project.buildVersions}"> 
	         	<option value='${version.id}'> ${version} </option>
		    </c:forEach>
		</select> <input type='text' name='version' value=''> </option>    
		Add new version
		</td>
		<tr><td>Manager:&nbsp;</td>
		<td><select name='manager' size='1'>
			<option value='${project.manager.id}'> ${project.manager.firstName} &nbsp; ${project.manager.lastName} </option>
			<c:forEach var="user" items="${users}"> 
	         	<c:if test="${project.manager.id != user.id}">
					<option value='${user.id}'> ${user.firstName} &nbsp; ${user.lastName} </option>
				</c:if>
	        </c:forEach>
		</select></td></tr>
	</table>
	<input type="hidden" name='id' value='${project.id}'>
	<input type="hidden" name='action' value='update'>
	<input type="submit" value="Update project">
    </form>
</body>
</html>

