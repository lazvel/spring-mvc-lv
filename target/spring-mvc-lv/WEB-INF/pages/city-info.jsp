<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>City info</title>
	</head>
	<body>
		<div>
			<label for="number">Code: </label>
			<input name="number" type="text" value="${city.number}" readonly="readonly"/>
		</div>
		<div>
			<label for="name">Name: </label>
			<input name="name" type="text" value="${city.name}" readonly="readonly" />
		</div>	
			
	</body>
</html>