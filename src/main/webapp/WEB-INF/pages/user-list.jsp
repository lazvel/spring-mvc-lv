<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Insert title here</title>
		<style type="text/css">
			.delete {
				text-decoration: none;
				color: red;
				font-size: 16px;
				font-weight: bold;
			}
		</style>
	</head>
	<body>
		<h2>All cities</h2>
		<table>
			<thead>
				<tr>
					<th>Firstname
					<th>Lastname
					<th>Username
					<th>Password
					<th>Details
			</thead>
			<tbody>
			<c:forEach var="user" items="${users}">
				<tr>
					<td>${user.firstname}</td>
					<td>${user.lastname}</td>
					<td>${user.username}</td>
					<td>*******</td>
					<td><a href="/spring-mvc-lv/user/info?firstname=${user.firstname}">Details</a></td>
					<td><a class="delete" href="/spring-mvc-lv/user/delete?username=${user.username}">Delete</a></td>
			</c:forEach>
			</tbody>
			
		</table>
		
	</body>
</html>