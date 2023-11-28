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
	<sf:form action="User" method="post" modelAttribute="form">

		<table>
			<tr>
				<th>First Name :</th>
				<td><sf:input path="firstName"/></td>
				<td><sf:errors path="firstName"></sf:errors></td>
			</tr>
			<tr>
				<th>Last Name :</th>
				<td><sf:input path="lastName" /></td>
				<td><sf:errors path="lastName"></sf:errors></td>
			</tr>
			<tr>
				<th>Login :</th>
				<td><sf:input path="login" /></td>
				<td><sf:errors path="login"></sf:errors></td>
				
			</tr>
			<tr>
				<th>Password :</th>
				<td><sf:input path="password" /></td>
				<td><sf:errors path="password"></sf:errors></td>
			</tr>
			<tr>
				<th>Date Of Birth :</th>
				<td><sf:input path="dob" /></td>
			</tr>
			<tr>
				<th>Address :</th>
				<td><sf:input path="address" /></td>
				<td><sf:errors path="address"></sf:errors></td>
			</tr>
			<tr>
				<th></th>
				<td><input type="submit"></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>