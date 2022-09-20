<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Maze Creator</title>
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
	
	<h1>Create a Maze</h1>
	
	<h3>${message}</h3>
	
	<form action="/mazeeditor" method="POST">
				<input type="hidden" name="username" value="${username}"/>
				<input type="hidden" name="loggedIn" value="${loggedIn}"/>
 				 Maze Title:
				<textarea name="title" placeholder="Enter maze title here..."></textarea>
				<br>
				<label for="rows">Rows (between 3-20):</label>
				<input type="number" id="rows" name="rows" min="3" max="20" value=5>
				<br>
				<label for="columns">Columns (between 3-20):</label>
				<input type="number" id="columns" name="columns" min="3" max="20" value=5>
				<br>
				<input type="submit" value="Create Maze Design"/>
	</form>
	
	
</body>
</html>