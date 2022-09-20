<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta charset="ISO-8859-1">

<title>A-MAZE-ING!</title>
<link href="/resources/static/style.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	
	<div class="topnav">
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
	<h1>${test.title}</h1>
	<h1>Maze Town</h1>

	<h3>${message}</h3>

</body>
</html>