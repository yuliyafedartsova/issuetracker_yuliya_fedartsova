<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="by.gsu.epamlab.constants.ConstantsJSP"%>
<%@page import="by.gsu.epamlab.model.beans.Role"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>seance</title>
<script src="scripts.js"></script>
</head>
<body bgcolor= "FF FF F0">
	<%@ include file="header.jsp" %> &nbsp;
	<h3> ${play.genre} </h3>
	<h3> ${play.author}&nbsp;${play.name} </h3>
	<form id = "getDate" method="POST">
	<input type=hidden name="dateId" value="no"> 
	<input type=hidden name="actionType" value="getInfoForUser"> 
	<table>
	<tr>
	<c:forEach var="playDate" items="${play.dates}">
		<td>
		<c:choose>
			<c:when test="${date.date eq playDate.date}">
				${playDate.date}
			</c:when>
			<c:otherwise>
				<a onclick="sendDateId(${playDate.id}, '<c:url value='/user-controller'/>')"  href="#this"> ${playDate.date}</a>			
			</c:otherwise>
		</c:choose>
		</td>
	</c:forEach>
	</tr>
	</table>
	</form>
	<BR>
	<label id="errorLabel"> </label>
	<BR>
	<label id="errorLabel2"> </label>
	<BR><BR>
	<c:if test="${not empty errorMessage}">
		<c:out value="${errorMessage}"/>
		<hr>
	</c:if>
	<BR>
	<form name="orderForm" method="POST">
		<input type=hidden name= "actionType" id = "action_id"/>
		<table BORDER CELLSPACING=0 id = "t" align="center" width="70%"  height="50%">
			<tr bgcolor= "F5 F5 DC" name = "infoTR">
				<th> Category </th>
				<th> Ticket price </th>
				<th> Total tickets </th>
				<th> Available </th>
				<c:if test="${user.role eq 'USER'}">
					<th> Paid </th>
					<th> Ordered </th>
					<th> Quantity </th>
				</c:if>
			</tr>
			<c:forEach var="category" items="${categories}">
				<tr name = "infoTR">
					<td> ${category.name} </td>
					<td> ${category.price} </td>
					<td> ${category.placeNumber} </td>
					<td>  ${availableTickets[category.id]} </td>
					<c:if test="${user.role eq 'USER'}">
						<td> ${paidTickets[category.id]} </td> 
						<td> ${orderedTickets[category.id]} </td>
						<td> <input type="text" name="quantity"> </td>
						<c:set var="orderedSum" value="${orderedSum + orderedTickets[category.id]*category.price}"/>
						<c:set var="paidSum" value="${paidSum + paidTickets[category.id]*category.price}"/>
					</c:if>
					<input type=hidden name= "categoryId" value= "${category.id}"/>
					</tr>
			</c:forEach>
				<c:if test="${user.role eq 'USER'}">
				<tr name = "infoTR">
				<td> Sum </td>
				<td>-</td>
				<td>-</td>
				<td>-</td>
				<td><c:out value="${paidSum}"/></td>
				<td><c:out value="${orderedSum}"/></td>
				<td>-</td>
				</tr>
				</c:if>
		</table>
	</form>
	<BR><BR>
	<c:if test="${user.role eq 'USER'}">
    	<button onclick="sendOrder('<c:url value='/user-controller'/>')" > Order </button>&nbsp;  
    	<button onclick="sendCancel('<c:url value='/user-controller'/>')" > Cancel order </button>
	</c:if>
	<BR><BR>
	<%@ include file="footer.jsp" %>
</body>
</html>
