<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Maze Created!</title>
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
	
	<h1>Confirmation Page</h1>
	<h3>${message}</h3>
	
	<c:if test="${not invalidMaze}">
		<form action="/displaymaze" method="POST">
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="hidden" name="title" value="${title}"/>
			<input type="submit" value="View Maze"/>
		</form>
		<form action="/playjsmaze" method="POST">
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="hidden" name="title" value="${maze.title}"/>
			<input type="submit" value="Play ${maze.title}"/>
		</form>
	</c:if>
	
	<c:if test="${invalidMaze}">
		<c:if test="${loggedIn}">
			<h3>We saved your unfinished maze!</h3>
		</c:if>
		<c:if test="${not loggedIn}">
			<h3>If you don't finish your maze, you'll never see it ever again.</h3>
		</c:if>
		<form action="/mazeeditor" method="POST">
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