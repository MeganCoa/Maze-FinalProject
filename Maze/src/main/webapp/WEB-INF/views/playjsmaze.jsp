<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Play!</title>

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
	
	
<script src="/mazegame.js"></script>
<script src="/mazing.js"></script>




<br><br><br><br>
<h1 class="title">${title}</h1>
<br><br><br><br>
<div id="maze_container">
<div id="maze" data-steps="???">
</div>
<!-- insert the maze HTML code here -->
<br><br><br><br><br>

<script>

  window.addEventListener("DOMContentLoaded", function(e) {
	  let Maze = new MazeBuilder(16, 12);
	  Maze.placeKey();
	  Maze.display("maze_container");
	  
	  var maze = new Mazing("maze");
 maze.setChildMode(); 
  }, false);

</script>
</div>

<div id="begin" class="begin">
<button onclick="fetch('/jsonmazegrid/${title}').then((response) => response.json()).then((data) => onGenerateMaze(data)); change()">Begin Maze</button>
</div>


</body>
</html>