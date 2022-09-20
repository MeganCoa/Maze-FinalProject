<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Maze Search</title>
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
	
	<h1 class="pageTitle">Maze Search</h1>
	
	<div class="center">
	<form style= "font-size: 24px;" class="form" action="/searchforamaze" method="post">
	<div class="buttonHolder">
		<input id="textboxid" style="width: 500px;" type="text" name="searchTerm" placeholder="Whatcha looking for?" />
		</div> 
		<label for="searchCategory">Search By: </label>
		<input type="radio" name="searchCategory" value="title" placeholder="Search Category" /> Title 
		<input type="radio" name="searchCategory" value="author" placeholder="Search Category" /> Author 
		<input type="hidden" name="username" value="${username}"/>
		<input type="hidden" name="loggedIn" value="${loggedIn}"/>
		<button type="submit">Search</button>
	</form>
	</div>
	
	<br><br>
	
	<div class="center">
	<table id="mazeList">
		<tr>
			<th>Maze Title</th>
			<th>Architect</th>
			<th>Rating</th>
		</tr>
		<c:forEach var="maze" items="${allMazes}">
			<tr>
				<td>${maze.title}</td>
				<td>${maze.authorName}</td>
				<td>${maze.avgRating}</td>
				<td>
					<div class="table-divs">
						<form action="/displaymaze" method="POST">
							<input type="hidden" name="username" value="${username}"/>
							<input type="hidden" name="loggedIn" value="${loggedIn}"/>
							<input type="hidden" name="title" value="${maze.title}"/>
							<button type="submit">View</button>
						</form>
						</div>
						<div class="table-divs">
						<form action="/playjsmaze" method="POST">
							<input type="hidden" name="username" value="${username}"/>
							<input type="hidden" name="loggedIn" value="${loggedIn}"/>
							<input type="hidden" name="title" value="${maze.title}"/>
							<button type="submit">Play ${maze.title}</button>
					</form>
					</div>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	
	
</body>
</html>