<%@page import="com.rays.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



	<%
		UserDTO user = (UserDTO) session.getAttribute("user");
	%>

	<%
		if (user != null) {
	%>
	<h3>
		Hi,
		<%=user.getFirstName()%></h3>
	<h3>
		<a href="/SpringMVC/ctl/User">Add User</a> <a
			href="/SpringMVC/ctl/User/list">User List</a> <a
			href="/SpringMVC/Auth/logout">Logout</a>
	</h3>
	<%
		} else {
	%>
	<h3>Hi, Guest</h3>
	<%
		}
	%>
	<hr>
</body>
</html>