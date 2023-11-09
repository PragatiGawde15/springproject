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
	<sf:form action="/SpringMVC/User" method="post" modelAttribute="form">

		<table>


			<sf:hidden path="id" />

			<tr>
				<th>First Name :</th>
				<td><sf:input path="firstName" /></td>
			</tr>
			<tr>
				<th>Last Name :</th>
				<td><sf:input path="lastName" /></td>
			</tr>
			<tr>
				<th>Login :</th>
				<td><sf:input path="login" /></td>
			</tr>
			<tr>
				<th>Password :</th>
				<td><sf:input path="password" /></td>
			</tr>
			<tr>
				<th>Date Of Birth :</th>
				<td><sf:input path="dob" /></td>
			</tr>
			<tr>
				<th>Address :</th>
				<td><sf:input path="address" /></td>
			</tr>
			<tr>
				<th></th>
				<td><input type="submit"></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>