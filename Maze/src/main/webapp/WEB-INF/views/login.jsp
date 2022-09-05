<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login</title>
</head>
<body>
	<h1>User Login</h1>
	<h3>${message}</h3>
	<form action="/login" method="POST">
		Username: <input type="text" name="username"/>
		Password: <input type="text" name="password"/>
		<input type="submit"/>
	</form>
</body>
</html>