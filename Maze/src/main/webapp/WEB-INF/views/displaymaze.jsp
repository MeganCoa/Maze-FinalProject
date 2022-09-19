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
<link href="/resources/static/style.css" rel="stylesheet" type="text/css">

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
			<div class="form-container">
				<form action="/usermazes" method="POST">
					<input type="hidden" name="username" value="${username}" /> <input
						type="hidden" name="loggedIn" value="${loggedIn}" />
					<button type="submit">My Mazes</button>
				</form>
			</div>
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
				<form action="/signup">
					<button type="submit">Sign Up</button>
				</form>
			</div>
		</c:if>
	</div>
	
	
  	<h1>${maze.title}</h1>
  	<h2>By ${maze.authorName}</h2>
  	<p>Total Plays: ${maze.playTotal}</p>
  	<p>Average Rating: ${maze.avgRating} Stars</p>
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
		<input type="hidden" name="loSggedIn" value="${loggedIn}"/>
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
	<div class="icon">
	<img src="${picture}"
       width="100" 
       height="100"
       />
       </div>
</body>
</html>