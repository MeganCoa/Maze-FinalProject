<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Play Maze</title>
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
	<h1>${maze.title}</h1>
  	<h2>By ${maze.authorName}</h2>
  	<div class = "mazeDisplay">
	${symbolMaze}
	</div>
	<h3>From the Start to the End</h3>
	<h3>Change cells to P for the Path you travel</h3>
	
	<form action="/playtest" method="POST">
		<c:forEach var="coordinate" items="${mazegridcoordinates}">
				<select name="userPathData" id="cell" class="cell">
				<c:if test="${coordinate.coordinateValue == 0}">
					<option value="0" selected>#</option>
  					<option value="1" disabled>O</option>
 			 		<option value="2" disabled>S</option>
 			 		<option value="3" disabled>E</option>
				</c:if>
				<c:if test="${coordinate.coordinateValue == 1}">
  					<option value="0" disabled>#</option>
  					<option value="1" selected>O</option>
 			 		<option value="4">P</option>
 			 		<option value="2" disabled>S</option>
 			 		<option value="3" disabled>E</option>
				</c:if>
				<c:if test="${coordinate.coordinateValue == 2}">
 			 		<option value="0" disabled>#</option>
  					<option value="1" disabled>O</option>
 			 		<option value="2" selected>S</option>
 			 		<option value="4">P</option>
 			 		<option value="3" disabled>E</option>
				</c:if>
				<c:if test="${coordinate.coordinateValue == 3}">
 			 		<option value="0" disabled>#</option>
  					<option value="1" disabled>O</option>
 			 		<option value="2" disabled>S</option>
 			 		<option value="3" selected>E</option>
 			 		<option value="4" >P</option>
				</c:if>
				</select>
				<c:if test="${coordinate.endOfLine}"><br></c:if> 
		</c:forEach>
		<input type="hidden" name="title" value="${maze.title}"/>
		<input type="hidden" name="username" value="${username}"/>
		<input type="hidden" name="loggedIn" value="${loggedIn}"/>
		<input type="hidden" name="message" value="${message}"/>
		<input type="submit" value="Check Solution"/>
	</form>
	
	<c:if test="${loggedIn}">
		<form action="/addUserFavorite" method="POST">
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="title" value="${maze.title}"/>
			<input type="submit" value="Add To Favorites"/>
		</form>
	</c:if>
</body>
</html>