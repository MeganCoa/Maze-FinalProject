<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Maze</title>
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
	
	
  	<h1>${maze.title}</h1>
  	<h2>By ${maze.authorName}</h2>
	<div class = "mazeDisplay">
	${symbolMaze}
	</div>
	
	<form action="/solvemaze" method="POST">
		<input type="hidden" name="username" value="${username}"/>
		<input type="hidden" name="loggedIn" value="${loggedIn}"/>
		<input type="hidden" name="title" value="${maze.title}"/>
		<input type="submit" value="See Solution"/>
	</form>
	<form action="/usersolvemaze" method="POST">
		<input type="hidden" name="username" value="${username}"/>
		<input type="hidden" name="loggedIn" value="${loggedIn}"/>
		<input type="hidden" name="title" value="${maze.title}"/>
		<input type="submit" value="Solve Maze Myself"/>
	</form>
	
	<c:if test="${loggedIn}">
		<form action="/addUserFavorite" method="POST">
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="title" value="${maze.title}"/>
			<input type="submit" value="Add To Favorites"/>
		</form>
	</c:if>
	
	<br>
	<img src="${picture}" 
       width="100" 
       height="100"
       />
</body>
</html>