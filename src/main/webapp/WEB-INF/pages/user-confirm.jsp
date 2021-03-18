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
				color: purple;
				font-size: 18px;
			}
			.err {
				color: red;
				font-size: 18px;
			}
		</style>
	</head>
	<body>
		<h2>Confirm added User</h2>
		
		<c:if test="${not empty information}">
			<div class="info">${information}</div>
		</c:if>
		
		<c:if test="${not empty exception}">
			<div class="err">${exception}</div>
		</c:if>
		
		<form method="POST" action="/spring-mvc-lv/user/confirm">
			<div>Firstname:</div>
			<div><input type="text" name="firstname" value="${userDto.firstname}" readonly="readonly" /></div>
			
			<div>Lastname:</div>
			<div><input type="text" name="lastname" value="${userDto.lastname}" readonly="readonly" /></div>
			
			<div>Username:</div>
			<div><input type="text" name="username" value="${userDto.username}" readonly="readonly" /></div>
			
			<div>Password:</div>
			<div><input type="password" name="password" value="${userDto.password}" readonly="readonly" /></div>
			
			<div><button id="save">Confirm</button></div>
		</form>
	</body>
</html>