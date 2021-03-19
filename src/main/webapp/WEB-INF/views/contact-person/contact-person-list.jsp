<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h2>All manufacturers</h2>
		<table>
			<thead>
				<tr>
					<th>Firstname
					<th>Lastname
					<th>Manufacturer id
					<th>Manufacturer name
					<th>Details
			</thead>
			<tbody>
			<c:forEach var="p" items="${persons}">
				<tr>
					<td>${p.firstname}</td>
					<td>${p.lastname}</td>
					<td>${p.manufacturerDto.id}</td>
					<td>${p.manufacturerDto.name}</td>
					<td><a href="/spring-mvc-lv/contactPerson/details/id/${p.id}">Details</a></td>
			</c:forEach>
			</tbody>
			
		</table>
		
	</body>
</html>