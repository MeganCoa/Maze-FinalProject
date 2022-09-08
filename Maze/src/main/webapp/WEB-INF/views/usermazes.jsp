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
	<ul>
		<c:forEach var="title" items="${userMazes}">
			<li>
				<form action="/displaymaze" method="POST">
					<input type="hidden" name="username" value="${username}"/>
					<input type="hidden" name="loggedIn" value="${loggedIn}"/>
					<input type="hidden" name="title" value="${title}"/>
					<input type="submit" value="${title}"/>
				</form>
			</li>
		</c:forEach>
	</ul>
	<h1>${username}'s Favorite Mazes</h1>
	<ul>
		<c:forEach var="title" items="${userFavorites}">
			<li>
				<form action="/displaymaze" method="POST">
					<input type="hidden" name="username" value="${username}"/>
					<input type="hidden" name="loggedIn" value="${loggedIn}"/>
					<input type="hidden" name="title" value="${title}"/>
					<input type="submit" value="${title}"/>
				</form>
			</li>
		</c:forEach>
	</ul>
</body>
</html>