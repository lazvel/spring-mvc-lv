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
				font-weight: bold;
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
		
		<form:form method="POST" action="/spring-mvc-lv/manufacturer/confirm" modelAttribute="manufacturerDto">
			
			<div>Manufacturer name:</div>
			<div><form:input type="text" path="name"/></div>
			<div><form:errors path="name" /></div>
			
			<div>City:</div>
			<div>
				<form:select path="cityDto" multiple="false">
					<form:options items="${cities}" itemValue="number" itemLabel="name"/>
				</form:select>
			</div>
			<div><form:errors path="cityDto" /></div>
			
			<div><button id="save">Save</button></div>
		</form:form>
	</body>
</html>