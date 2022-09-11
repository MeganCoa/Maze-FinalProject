<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Play Maze</title>
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
	
	<form action="/confirmation" method="POST">
		<c:forEach var="coordinate" items="${mazegridcoordinates}">
				<select name="userPathData" id="cell" class="cell">
				<c:if test="${coordinate.coordinateValue == 0}">
					<option value="0" selected>#</option>
				</c:if>
				<c:if test="${coordinate.coordinateValue == 1}">
  					<option value="1" selected>O</option>
 			 		<option value="4">P</option>
				</c:if>
				<c:if test="${coordinate.coordinateValue == 2}">
 			 		<option value="2" selected>S</option>
				</c:if>
				<c:if test="${coordinate.coordinateValue == 3}">
 			 		<option value="3" selected>E</option>
				</c:if>
				</select>
				<c:if test="${coordinate.endOfLine}"><br></c:if> 
		</c:forEach>
		<input type="hidden" name="title" value="${maze.title}"/>
		<input type="hidden" name="username" value="${username}"/>
		<input type="hidden" name="loggedIn" value="${loggedIn}"/>
		<input type="hidden" name="message" value="${message}"/>
		<input type="submit" value="Check Solution"/>
	</form>
	
	<c:if test="${loggedIn}">
		<form action="/addUserFavorite" method="POST">
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="title" value="${maze.title}"/>
			<input type="submit" value="Add To Favorites"/>
		</form>
	</c:if>
</body>
</html>