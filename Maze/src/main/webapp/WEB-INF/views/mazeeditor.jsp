<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Make a Maze!</title>
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
	
	<h2>${maze.title}</h2>
	<h2>${username}</h2>
	
	<h3>S: Start of maze</h3>
	<h3>O: Maze Path</h3>
	<h3>E: End of Maze</h3>
	<h3>#: Maze Wall</h3>
	<form action="/confirmation" method="POST">
		<c:forEach var="coordinate" items="${mazegridcoordinates}">
				<select name="cellData" id="cell" class="cell">
				<c:if test="${coordinate.coordinateValue == 0}">
					<option value="0" selected>#</option>
  					<option value="1">O</option>
 			 		<option value="2">S</option>
 			 		<option value="3">E</option>
				</c:if>
				<c:if test="${coordinate.coordinateValue == 1}">
					<option value="0">#</option>
  					<option value="1" selected>O</option>
 			 		<option value="2">S</option>
 			 		<option value="3">E</option>
				</c:if>
				<c:if test="${coordinate.coordinateValue == 2}">
					<option value="0">#</option>
  					<option value="1">O</option>
 			 		<option value="2" selected>S</option>
 			 		<option value="3">E</option>
				</c:if>
				<c:if test="${coordinate.coordinateValue == 3}">
					<option value="0">#</option>
  					<option value="1">O</option>
 			 		<option value="2">S</option>
 			 		<option value="3" selected>E</option>
				</c:if>
				</select>
				<c:if test="${coordinate.endOfLine}"><br></c:if> 
		</c:forEach>
		<input type="hidden" name="title" value="${maze.title}"/>
		<input type="hidden" name="username" value="${username}"/>
		<input type="hidden" name="loggedIn" value="${loggedIn}"/>
		<input type="submit" value="Save Maze"/>
	</form>

	

</body>
</html>