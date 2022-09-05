<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Maze Creator</title>
</head>
<body>
	<h1>Create a Maze</h1>
	<c:if test="${loggedIn}">
		<h2>Ready to make a maze, ${username}?</h2>
	</c:if>
	
</body>
</html>