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
		
		<form:form modelAttribute="manufacturerDto">
			
			<div>Manufacturer name:</div>
			<div><form:input type="text" path="name" readonly="readonly" size="60"/></div>
			<div><form:errors path="name" /></div>
			
			<div>City number:</div>
			<div>
				<form:input type="text" path="cityDto.number" readonly="readonly" size="60"/>
			</div>
			
			<div>City name:</div>
			<div>
				<form:input type="text" path="cityDto.name" readonly="readonly" size="60"/>
			</div>
			<div><form:errors path="cityDto" /></div>
			
			<div>
				<a href="${pageContext.request.contextPath}/manufacturer/edit/id/${manufacturerDto.id}">Edit</a>
			</div>
			
		</form:form>
	</body>
</html>