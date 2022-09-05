<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New User</title>
</head>
<body>
	<h1>New User</h1>
	<h3>${message}</h3>
	<form action="/signup" method="POST">
		Username: <input type="text" name="username"/>
		Email: <input type="text" name="email"/>
		Password: <input type="text" name="password"/>
		<input type="submit"/>
	</form>
</body>
</html>