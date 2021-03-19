<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		<h2>BINDING PAGE</h2>
		
		<c:if test="${not empty information}">
			<div class="info">${information}</div>
		</c:if>
		
		<c:if test="${not empty exception}">
			<div class="err">${exception}</div>
		</c:if>
		
		<form:form method="POST" action="/spring-mvc-lv/user/save-binding-validation" modelAttribute="userDto">
			<div>Firstname:</div>
			<div><form:input type="text" path="firstname"/></div>
			<div><form:errors path="firstname" /></div>
			
			<div>Lastname:</div>
			<div><form:input type="text" path="lastname"/></div>
			<div><form:errors path="lastname" /></div>
			
			<div>Username:</div>
			<div><form:input type="text" path="username"/></div>
			<div><form:errors path="username" /></div>
			
			<div><form:input type="password" path="password"/></div>
			<div><form:errors path="password" /></div>
			<div><button id="save">Save</button></div>
		</form:form>
	</body>
</html>