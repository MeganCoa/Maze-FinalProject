<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Make Temp JS a Maze!</title>
<link href="/style.css" rel="stylesheet">
</head>
<body>

	<div class="buttons">
		<form action="/tempMazeEditor" method="Post">
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
	
	<h3>S: Start of maze</h3>
	<h3>O: Maze Path</h3>
	<h3>E: End of Maze</h3>
	<h3>#: Maze Wall</h3>
	<form action="/tempconfirmation" method="POST">
		<c:forEach var="coordinate" items="${mazegridcoordinates}">
				<select name="cellData" id="cell" class="cell">
				<c:if test="${coordinate.coordinateValue == 0}">
					<option value="0" selected>#</option>
  					<option value="1">O</option>
 			 		<option value="2">S</option>
 			 		<option value="3">E</option>
				</c:if>
				<c:if test="${coordinate.coordinateValue == 1}">
					<option value="0">#</option>
  					<option value="1" selected>O</option>
 			 		<option value="2">S</option>
 			 		<option value="3">E</option>
				</c:if>
				<c:if test="${coordinate.coordinateValue == 2}">
					<option value="0">#</option>
  					<option value="1">O</option>
 			 		<option value="2" selected>S</option>
 			 		<option value="3">E</option>
				</c:if>
				<c:if test="${coordinate.coordinateValue == 3}">
					<option value="0">#</option>
  					<option value="1">O</option>
 			 		<option value="2">S</option>
 			 		<option value="3" selected>E</option>
				</c:if>
				</select>
				<c:if test="${coordinate.endOfLine}"><br></c:if> 
		</c:forEach>
		<input type="hidden" name="title" value="${maze.title}"/>
		<input type="hidden" name="username" value="${username}"/>
		<input type="hidden" name="loggedIn" value="${loggedIn}"/>
		<input type="submit" value="Save Maze"/>
	</form>

	

</body>
</html>