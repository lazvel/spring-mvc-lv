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
					<th>Name
					<th>City code
					<th>City name
					<th>Details
			</thead>
			<tbody>
			<c:forEach var="m" items="${manufacturers}">
				<tr>
					<td>${m.name}</td>
					<td>${m.cityDto.number}</td>
					<td>${m.cityDto.name}</td>
					<td><a href="/spring-mvc-lv/manufacturer/details/id/${m.id}">Details</a></td>
			</c:forEach>
			</tbody>
			
		</table>
		
	</body>
</html>