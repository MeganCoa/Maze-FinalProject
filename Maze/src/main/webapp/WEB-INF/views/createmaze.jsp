<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Maze Creator</title>
<link href="/style.css" rel="stylesheet">
</head>
<body>
	<div class="buttons">
		<form action="/" method="Post">
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="submit" value="Home"/>
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
	
	<h1>Create a Maze</h1>
	
	<h3>${message}</h3>
	
	<form action="/mazeeditor" method="POST">
				<input type="hidden" name="username" value="${username}"/>
				<input type="hidden" name="loggedIn" value="${loggedIn}"/>
 				 Maze Title:
				<textarea name="title" placeholder="Enter maze title here..."></textarea>
				<br>
				<label for="rows">Rows (between 3-20):</label>
				<input type="number" id="rows" name="rows" min="3" max="20" value=5>
				<br>
				<label for="columns">Columns (between 3-20):</label>
				<input type="number" id="columns" name="columns" min="3" max="20" value=5>
				<br>
				<input type="submit" value="Create Maze Design"/>
	</form>
	
	
</body>
</html>