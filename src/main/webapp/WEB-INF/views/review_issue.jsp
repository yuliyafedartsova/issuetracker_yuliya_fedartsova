<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Review issue</title>
</head>
<body bgcolor= "FA F0 E6">
<%@ include file="header.jsp" %> 
	Id:&nbsp; ${issue.id} <br>
	Create Date:&nbsp; ${issue.createDate} <br>
	Created By:&nbsp; ${issue.author.firstName} &nbsp; ${issue.author.lastName} <br>
	<c:if test="${issue.modifyDate != null}">
		Modify Date:&nbsp; ${issue.modifyDate} <br>
		Modified By:&nbsp; ${issue.modifier.firstName} &nbsp; ${issue.modifier.lastName} <br>
	</c:if>
	Summary:&nbsp; ${issue.summary} <br>
	Description:&nbsp; ${issue.description} <br>
	Status:&nbsp; ${issue.status} <br>
	<c:if test="${issue.status eq 'Closed'}">
		Resolution:&nbsp; ${issue.resolution} <br>
	</c:if>
	Type:&nbsp; ${issue.type} <br>
    Priority:&nbsp; ${issue.priority} <br>
    Project:&nbsp; ${issue.project.name} <br>
    Build found:&nbsp; ${issue.buildFound} <br>
    <c:if test="${issue.assignee != null}">
		Assignee:&nbsp; ${issue.assignee.firstName} &nbsp; ${issue.assignee.lastName} <br>
	</c:if>
</body>
</html>