<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>User info</title>
	</head>
	<body>
		<div>
			<label for="firstname">Firstname: </label>
			<input name="firstname" type="text" value="${user.firstname}" readonly="readonly"/>
		</div>
		<div>
			<label for="lastname">Lastname: </label>
			<input name="lastname" type="text" value="${user.lastname}" readonly="readonly" />
		</div>	
		<div>
			<label for="username">Username: </label>
			<input name="username" type="text" value="${user.username}" readonly="readonly" />
		</div>	
	</body>
</html>