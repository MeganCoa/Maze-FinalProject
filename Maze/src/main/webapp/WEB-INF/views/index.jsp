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
	
	<h1>${test.title}</h1>
	<h1 class="indexTitle" >Maze Town</h1>

	<h3 style="text-align: center">${message}</h3>
	
	<div class="indexCenter">
		<div class="indexButton">
			<form action="/usercreatemaze" method="POST">
				<input type="hidden" name="username" value="${username}" /> <input
					type="hidden" name="loggedIn" value="${loggedIn}" />
				<button type="submit">Create A Maze</button>
			</form>
			</div>
			<div class="indexButton">
			<form action="/searchforamaze" method="POST">
				<input type="hidden" name="username" value="${username}" /> <input
					type="hidden" name="loggedIn" value="${loggedIn}" />
				<button type="submit">Search Mazes</button>
			</form>
			</div>
			<c:if test="${loggedIn}">
			<div class="indexButton">
			<form action="/usermazes" method="POST">
					<input type="hidden" name="username" value="${username}" /> <input
						type="hidden" name="loggedIn" value="${loggedIn}" />
					<button type="submit">My Mazes</button>
			</form>
			</div>
			<div class="indexButton">
			<form action="/signout" method="POST">
					<input type="hidden" name="username" value="${username}" /> <input
						type="hidden" name="loggedIn" value="${loggedIn}" />
					<button type="submit">Sign Out</button>
			</form>
			</div>
			</c:if>
			<c:if test="${not loggedIn}">
			<div class="indexButton">
				<form action="/login">
					<button type="submit">Log In</button>
				</form>
				</div>
				<div class="indexButton">
				<form action="/signup">
					<button type="submit">Sign Up</button>
				</form>
				</div>
		</c:if>
		
	</div>

</body>
</html>