<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MazeGamePage</title>
	<script type="text/javascript" src="/mazegame.js"></script>
	<link rel="stylesheet" type="text/css" href="/styles.css" />
</head>
	
	
<body>

	<div id="maze_container">
		<!-- -->
	</div>
	
	<h1>Hope this works...</h1>
	
	<script>
		function display() {
			let Maze = new MazeBuilder(16, 12);
			Maze.placeKey();
		Maze.display("maze_container");
		}
   
	</script>
	<input type="button" value="Display"id="maze_generator"  onclick="display()" />
	
</body>
</html>