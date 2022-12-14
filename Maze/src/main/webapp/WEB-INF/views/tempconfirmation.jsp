<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Maze Created!</title>
<link href="/style.css" rel="stylesheet">
</head>
<body>
	<div class="buttons">
		<form action="/" method="Post">
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="submit" value="Home"/>
		</form>
		<form action="/usercreatemaze" method="POST">
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="submit" value="Create a Maze"/>
		</form>
		<form action="/searchforamaze" method="POST">
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="submit" value="Search for a Maze"/>
		</form>
		<c:if test="${loggedIn}">
			<form action="/usermazes" method="POST">
				<input type="hidden" name="username" value="${username}"/>
				<input type="hidden" name="loggedIn" value="${loggedIn}"/>
				<input type="submit" value="My Mazes"/>
			</form>
			<form action="/signout" method="POST">
				<input type="hidden" name="username" value="${username}"/>
				<input type="hidden" name="loggedIn" value="${loggedIn}"/>
				<input type="submit" value="Sign Out"/>
			</form>
		</c:if>
		<c:if test="${not loggedIn}">
			<a href="/login">Log In</a>
			<a href="/signup">Sign Up</a>
		</c:if>
	</div>
	
	<h1>Confirmation Page</h1>
	<h3>${message}</h3>
	
	<c:if test="${not invalidMaze}">
		<form action="/tempdisplaymaze" method="POST">
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="hidden" name="title" value="${title}"/>
			<input type="submit" value="View Maze"/>
		</form>
	</c:if>
	
	<c:if test="${invalidMaze}">
		<form action="/tempmazeeditor" method="POST">
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="hidden" name="title" value="${title}"/>
			<input type="hidden" name="invalidMaze" value="${invalidMaze}"/>
			<input type="submit" value="Continue Editing"/>
		</form>
		<form action="/deleteusermaze" method="POST">
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="hidden" name="title" value="${title}"/>
			<input type="hidden" name="invalidMaze" value="${invalidMaze}"/>
			<input type="submit" value="Delete Maze"/>
		</form>
	</c:if>
	
	
</body>
</html>