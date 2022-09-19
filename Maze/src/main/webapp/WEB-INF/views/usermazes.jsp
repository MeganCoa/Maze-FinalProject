<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${username}'s Mazes</title>
<link href="/style.css" rel="stylesheet">
</head>
<body>
	<div class="buttons">
		<form action="/" method="POST">
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
		<form action="/signout" method="POST">
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="submit" value="Sign Out"/>
		</form>
	</div>
	<h1>${username}'s Mazes</h1>
	<h2>Live Mazes</h2>
	<ul>
		<c:forEach var="maze" items="${userMazes}">
			<c:if test="${maze.validMaze}">
				<li>
					<form action="/displaymaze" method="POST">
						<input type="hidden" name="username" value="${username}"/>
						<input type="hidden" name="loggedIn" value="${loggedIn}"/>
						<input type="hidden" name="title" value="${maze.title}"/>
						<input type="submit" value="${maze.title}"/>
					</form>
					
					<form action="/mazeeditor" method="POST">
						<input type="hidden" name="username" value="${username}"/>
						<input type="hidden" name="loggedIn" value="${loggedIn}"/>
						<input type="hidden" name="title" value="${maze.title}"/>
						<input type="submit" value="Edit ${maze.title}"/>
					</form>
					<form action="/deleteusermaze" method="POST">
						<input type="hidden" name="username" value="${username}"/>
						<input type="hidden" name="loggedIn" value="${loggedIn}"/>
						<input type="hidden" name="title" value="${maze.title}"/>
						<input type="submit" value="Delete ${maze.title}"/>
					</form>
				</li>
			</c:if>
		</c:forEach>
	</ul>
	<h2>${username}'s Mazes in Progress</h2>
		<ul>
		<c:forEach var="maze" items="${userMazes}">
			<c:if test="${not maze.validMaze}">
				<li>
					<form action="/mazeeditor" method="POST">
						<input type="hidden" name="username" value="${username}"/>
						<input type="hidden" name="loggedIn" value="${loggedIn}"/>
						<input type="hidden" name="title" value="${maze.title}"/>
						<input type="submit" value="Edit ${maze.title}"/>
					</form>
					<form action="/deleteusermaze" method="POST">
						<input type="hidden" name="username" value="${username}"/>
						<input type="hidden" name="loggedIn" value="${loggedIn}"/>
						<input type="hidden" name="title" value="${maze.title}"/>
						<input type="submit" value="Delete ${maze.title}"/>
					</form>
				</li>
			</c:if>
		</c:forEach>
	</ul>
	
	<h2>${username}'s Favorite Mazes</h2>
	<ul>
		<c:forEach var="title" items="${userFavorites}">
			<li>
				<form action="/displaymaze" method="POST">
					<input type="hidden" name="username" value="${username}"/>
					<input type="hidden" name="loggedIn" value="${loggedIn}"/>
					<input type="hidden" name="title" value="${title}"/>
					<input type="submit" value="${title}"/>
				</form>
				<form action="/deleteusermaze" method="POST">
					<input type="hidden" name="username" value="${username}"/>
					<input type="hidden" name="loggedIn" value="${loggedIn}"/>
					<input type="hidden" name="title" value="${title}"/>
					<input type="submit" value="Remove From Favorites"/>
				</form>
			</li>
		</c:forEach>
	</ul>
</body>
</html>