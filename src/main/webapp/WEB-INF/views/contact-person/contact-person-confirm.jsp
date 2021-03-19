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
		<h2>Confirm page</h2>
		
		<c:if test="${not empty information}">
			<div class="info">${information}</div>
		</c:if>
		
		<c:if test="${not empty exception}">
			<div class="err">${exception}</div>
		</c:if>
		
		<form:form action="/spring-mvc-lv/contactPerson/confirm/saveOrUpdate" method="POST" modelAttribute="contactPersonDto">
			<form:hidden path="id"/>
			<form:hidden path="manufacturerDto.cityDto.number"/>
			
			<div>Firstname:</div>
			<div><form:input type="text" path="firstname" readonly="readonly" size="60"/></div>
			<div><form:errors path="firstname" /></div>
			
			<div>Lastname:</div>
			<div><form:input type="text" path="lastname" readonly="readonly" size="60"/></div>
			<div><form:errors path="lastname" /></div>
			
			<div>Manufacturer id:</div>
			<div>
				<form:input type="text" path="manufacturerDto.id" readonly="readonly" size="60"/>
			</div>
			
			<div>Manufacturer name:</div>
			<div>
				<form:input type="text" path="manufacturerDto.name" readonly="readonly" size="60"/>
			</div>
			<div><form:errors path="manufacturerDto" /></div>
			
			<div>
				<button id="confirm">Confirm</button>
			</div>
			
		</form:form>
	</body>
</html>