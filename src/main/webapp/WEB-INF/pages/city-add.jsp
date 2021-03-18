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
			.info {
				color: lightblue;
			}
			.err {
				color: red;
			}
		</style>
	</head>
	<body>
		<h2>Add a new City</h2>
		
		<c:if test="${not empty information}">
			<div class="info">${information}</div>
		</c:if>
		
		<c:if test="${not empty exception}">
			<div class="err">${exception}</div>
		</c:if>
		
		<form method="POST" action="/spring-mvc-lv/city/saveToConfirm">
			<div>City number:</div>
			<div><input type="text" name="number"/></div>
			
			<div>City name:</div>
			<div><input type="text" name="name"/></div>
			
			<div><button id="save">Save</button></div>
		</form>
	</body>
</html>