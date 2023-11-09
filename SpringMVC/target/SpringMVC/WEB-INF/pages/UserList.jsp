<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>

	<sf:form action="/SpringMVC/User/list" method="post"
		modelAttribute="form">

		<table>
			<tr>
				<th>First Name :</th>
				<td><sf:input path="firstName"/></td>
				<th>DOB :</th>
				<td><sf:input path="dob"/></td>
				<td><input type="submit" value="search"></td>
			</tr>
		</table>
	</sf:form>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Login</th>
			<th>Password</th>
			<th>DOB</th>
			<th>Address</th>
			<th>Edit | Delete</th>
		</tr>
		<c:forEach items="${list}" var="user">
			<tr>
				<td><c:out value="${user.id } "></c:out></td>
				<td><c:out value="${user.firstName }"></c:out></td>
				<td><c:out value="${user.lastName }"></c:out></td>
				<td><c:out value="${user.login }"></c:out></td>
				<td><c:out value="${user.password }"></c:out></td>
				<td><c:out value="${user.dob }"></c:out></td>
				<td><c:out value="${user.address }"></c:out></td>
				<td><a href="/SpringMVC/ctl/User/get/${user.id }">edit</a> | <a
					href="/SpringMVC/ctl/User/delete/${user.id }">delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
