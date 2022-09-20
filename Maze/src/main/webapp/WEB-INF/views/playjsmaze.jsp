<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Play!</title>

<link href="/style.css" rel="stylesheet">

</head>
<body>
	<div class="buttons">
		<form action="/usercreatemaze" method="POST">
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="submit" value="Create a Maze"/>
		</form>
		<form action="/searchforamaze" method="POST">
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="loggedIn" value="${loggedIn}"/>
			<input type="submit" value="Search for a Maze"/>
		</form>
		<c:if test="${loggedIn}">
			<form action="/usermazes" method="POST">
				<input type="hidden" name="username" value="${username}"/>
				<input type="hidden" name="loggedIn" value="${loggedIn}"/>
				<input type="submit" value="My Mazes"/>
			</form>
			<form action="/signout" method="POST">
				<input type="hidden" name="username" value="${username}"/>
				<input type="hidden" name="loggedIn" value="${loggedIn}"/>
				<input type="submit" value="Sign Out"/>
			</form>
		</c:if>
		<c:if test="${not loggedIn}">
			<a href="/login">Log In</a>
			<a href="/signup">Sign Up</a>
		</c:if>
		
	</div>
	
	
<script src="/mazegame.js"></script>
<script src="/mazing.js"></script>




<br><br><br><br><br><br><br><br>
<div id="maze_container">
<div id="maze" data-steps="???">
<!-- insert the maze HTML code here -->
<br><br><br><br><br><br><br><br>

<script>

  window.addEventListener("DOMContentLoaded", function(e) {
	  let Maze = new MazeBuilder(16, 12);
	  Maze.placeKey();
	  Maze.display("maze_container");
	  
	  var maze = new Mazing("maze");
/*  maze.setChildMode(); */
  }, false);

</script>
</div>
</div>


<fieldset>
<span><input type="button" value="Begin Maze" onclick="fetch('/jsonmazegrid/${title}').then((response) => response.json()).then((data) => onGenerateMaze(data))" /></span>
</fieldset>


</body>
</html>