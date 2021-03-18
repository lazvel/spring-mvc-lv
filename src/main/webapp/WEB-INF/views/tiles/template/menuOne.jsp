<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
	<style type="text/css">
		#menu {
			display: flex;
			justify-content: space-around;
			padding: 10px;
			border-bottom: 2px solid red;
		}
	</style>
</head>
<div id="menu">
	<div>
		<c:url value="/city/add" var="cityAdd"></c:url>
		<a href="<c:out value="${cityAdd}"/>">Add city</a>
	</div>
	<div>
		<c:url value="/city/list" var="cityList"></c:url>
		<a href="<c:out value="${cityList}"/>">List of cities</a>
	</div>
	<div>
		<c:url value="/manufacturer/add" var="manufacturerAdd"></c:url>
		<a href="<c:out value="${manufacturerAdd}"/>">Manufacturer add</a>
	</div>
	
	<div>
		<c:url value="/manufacturer/list" var="manufacturerList"></c:url>
		<a href="<c:out value="${manufacturerList}"/>">Manufacturer list</a>
	</div>
</div>

