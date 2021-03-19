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
		<h2>All cities</h2>
		<table>
			<thead>
				<tr>
					<th>Code
					<th>Name
					<th>Details
			</thead>
			<tbody>
			<c:forEach var="city" items="${cities}">
				<tr>
					<td>${city.number}</td>
					<td>${city.name}</td>
					<td><a href="/spring-mvc-lv/city/info?number=${city.number}">Details</a></td>
			</c:forEach>
			</tbody>
			
		</table>
		
	</body>
</html>