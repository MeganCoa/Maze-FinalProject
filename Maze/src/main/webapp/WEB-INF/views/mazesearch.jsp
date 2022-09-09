<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Maze Search</title>
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
		<c:if test="${loggedIn}">
			<form action="/usermazes" method="POST">
				<input type="hidden" name="username" value="${username}"/>
				<input type="hidden" name="loggedIn" value="${loggedIn}"/>
				<input type="submit" value="My Mazes"/>
			</form>
			<form action="signout" method="POST">
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
	
	<h1>Maze Search</h1>
	
	<form method="post" action="/searchresults">
		<input type="text" placeholder="Title or Author" name="searchTerm">
		<button type="submit">Search</button>
	</form>
	
	<ul>
		<c:forEach var="maze" items="${allMazes}">
			<li>
				<form action="/displaymaze" method="POST">
					<input type="hidden" name="username" value="${username}"/>
					<input type="hidden" name="loggedIn" value="${loggedIn}"/>
					<input type="hidden" name="title" value="${maze.title}"/>
					<input type="submit" value="${maze.title}"/>
				</form>
			</li>
		</c:forEach>
	</ul>
	
	<h1>"${mazeSearchResult.getTitle()}"</h1>
	<div class = "mazeDisplay">
	${searchSymbolMaze}
	</div>
	
	<table>
         <tr>
            <th>Maze Search Results</th>
         </tr>
         <c:forEach items="${searchedMazes}" var="searchmaze">
            <tr>
                <td>${searchedMazes.getTitle()</td>
                <td>${searchedMazes.getTitle()</td>
            </tr>
         </c:forEach>
      </table>
	
	
</body>
</html>