<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${username}'sMazes</title>
<link href="/resources/static/style.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div class="topnav">
		<div class="form-container">
			<form action="/" method="POST">
				<input type="hidden" name="username" value="${username}" /> <input
					type="hidden" name="loggedIn" value="${loggedIn}" />
				<button type="submit">Home</button>
			</form>
		</div>
		<div class="form-container">
			<form action="/usercreatemaze" method="POST">
				<input type="hidden" name="username" value="${username}" /> <input
					type="hidden" name="loggedIn" value="${loggedIn}" />
				<button type="submit">Create A Maze</button>
			</form>
		</div>
		<div class="form-container">
			<form action="/searchforamaze" method="POST">
				<input type="hidden" name="username" value="${username}" /> <input
					type="hidden" name="loggedIn" value="${loggedIn}" />
				<button type="submit">Search Mazes</button>
			</form>
		</div>
		<c:if test="${loggedIn}">
			<div class="form-container-right">
				<form action="/signout" method="POST">
					<input type="hidden" name="username" value="${username}" /> <input
						type="hidden" name="loggedIn" value="${loggedIn}" />
					<button type="submit">Sign Out</button>
				</form>
			</div>
		</c:if>
		<c:if test="${not loggedIn}">
			<div class="form-container-right">
				<form action="/login">
					<button type="submit">Log In</button>
				</form>
			</div>
			<div class="form-container-right">
				<form action="/signup">
					<button type="submit">Sign Up</button>
				</form>
			</div>
		</c:if>
	</div>
	<h1>${username}'sMazes</h1>
	<h2>Live Mazes</h2>
	<ul>
		<c:forEach var="maze" items="${userMazes}">
			<c:if test="${maze.validMaze}">
				<li>
					<form action="/displaymaze" method="POST">
						<input type="hidden" name="username" value="${username}"/>
						<input type="hidden" name="loggedIn" value="${loggedIn}"/>
						<input type="hidden" name="title" value="${maze.title}"/>
						<input type="submit" value="View ${maze.title}"/>
					</form>
					<form action="/playjsmaze" method="POST">
						<input type="hidden" name="username" value="${username}"/>
						<input type="hidden" name="loggedIn" value="${loggedIn}"/>
						<input type="hidden" name="title" value="${maze.title}"/>
						<input type="submit" value="Play ${maze.title}"/>
					</form>
					<form action="/mazeeditor" method="POST">
						<input type="hidden" name="username" value="${username}" />
						<input type="hidden" name="loggedIn" value="${loggedIn}" />
						<input type="hidden" name="title" value="${maze.title}" />
						<input type="submit" value="Edit ${maze.title}" />
					</form>
					<form action="/deleteusermaze" method="POST">
						<input type="hidden" name="username" value="${username}" />
						<input type="hidden" name="loggedIn" value="${loggedIn}" />
						<input type="hidden" name="title" value="${maze.title}" />
						<input type="submit" value="Delete ${maze.title}" />
					</form>
				</li>
			</c:if>
		</c:forEach>
	</ul>
	<h2>${username}'sMazes in Progress</h2>
	<ul>
		<c:forEach var="maze" items="${userMazes}">
			<c:if test="${not maze.validMaze}">
				<li>
					<form action="/mazeeditor" method="POST">
						<input type="hidden" name="username" value="${username}" /> <input
							type="hidden" name="loggedIn" value="${loggedIn}" /> <input
							type="hidden" name="title" value="${maze.title}" /> <input
							type="submit" value="Edit ${maze.title}" />
					</form>
					<form action="/deleteusermaze" method="POST">
						<input type="hidden" name="username" value="${username}" /> <input
							type="hidden" name="loggedIn" value="${loggedIn}" /> <input
							type="hidden" name="title" value="${maze.title}" /> <input
							type="submit" value="Delete ${maze.title}" />
					</form>
				</li>
			</c:if>
		</c:forEach>
	</ul>

	<h2>${username}'sFavorite Mazes</h2>
	<ul>
		<c:forEach var="title" items="${userFavorites}">
			<li>
				<form action="/displaymaze" method="POST">
					<input type="hidden" name="username" value="${username}" /> <input
						type="hidden" name="loggedIn" value="${loggedIn}" /> <input
						type="hidden" name="title" value="${title}" /> <input
						type="submit" value="${title}" />
				</form>
				<form action="/playjsmaze" method="POST">
					<input type="hidden" name="username" value="${username}"/>
					<input type="hidden" name="loggedIn" value="${loggedIn}"/>
					<input type="hidden" name="title" value="${maze.title}"/>
					<input type="submit" value="Play ${maze.title}"/>
				</form>
				<form action="/removeUserFavorite" method="POST">
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