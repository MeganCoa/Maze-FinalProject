<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Random Maze JavaScript Generator</title>

<link href="/style.css" rel="stylesheet">

</head>
<body>
<h1>RandomMaze</h1>
<script src="/mazegame.js"></script>
<script src="/mazing.js"></script>





<div id="maze_container">
<div id="maze" data-steps="???">
<!-- insert the maze HTML code here -->


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
<legend>Maze Generator</legend>
<label for="field_height">No. columns</label><span><input id="field_height" type="number" min="2" max="21" name="cols" value="12"></span>
<label for="field_width">No. rows</label><span><input id="field_width" type="number" min="2" max="21" name="rows" value="8"></span>
<span><input type="button" value="Generate maze »" onclick="onGenerateMaze()" /></span>
</fieldset>


</body>
</html>