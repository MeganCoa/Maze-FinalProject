<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Solution Results</title>
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
	
	<h1>Solution Results</h1>
	<h3>${message}</h3>
	<c:if test="${not successful}">
		<form action="/solvemaze" method="POST">
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="hidden" name="title" value="${title}"/>
			<input type="submit" value="See Solution"/>
		</form>
		<form action="/usersolvemaze" method="POST">
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="hidden" name="title" value="${title}"/>
			<input type="submit" value="Try Again"/>
		</form>
	</c:if>
	<c:if test="${successful}">
		<form action="/solvemaze" method="POST">
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="hidden" name="title" value="${title}"/>
			<input type="submit" value="See Solution"/>
		</form>
		<form action="/usersolvemaze" method="POST">
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="hidden" name="title" value="${title}"/>
			<input type="submit" value="Play Again"/>
		</form>
	</c:if>
	<form action="/displaymaze" method="POST">
		<input type="hidden" name="username" value="${username}"/>
		<input type="hidden" name="loggedIn" value="${loggedIn}"/>
		<input type="hidden" name="title" value="${title}"/>
		<select name="newMazeRating">
			<option value="1">1 Star</option>
			<option value="2">2 Stars</option>
 			<option value="3">3 Stars</option>
 			<option value="4">4 Stars</option>
 			<option value="5" selected>5 Stars</option>
		</select>
		<input type="submit" value="Rate this maze"/>
	</form>
	
	
</body>
</html>