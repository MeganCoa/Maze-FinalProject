<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>POV Maze</title>
<link href="/style.css" rel="stylesheet">
</head>
<body>


<script src="/povmazegame.js"></script>
<script src="/povmazing.js"></script>

<script>

  window.addEventListener("DOMContentLoaded", function(e) {
	  
	  var maze = new Mazing();
/*  maze.setChildMode(); */
  }, false);

</script>

<div>
<br><br><br><br><br><br><br><br>
	<div id="povContainer">
				
		<img class=goal id="4G" src="4G.png"/>
		<img class=goal id="4LG" src="4LG.png"/>
		<img class=goal id="4RG" src="4RG.png"/>
		<img class=goal id="3RG" src="3RG.png"/>
		<img class=goal id="3G" src="3G.png"/>
		<img class=goal id="3LG" src="3LG.png"/>
		<img class=goal id="2RG" src="2RG.png"/>
		<img class=goal id="2G" src="2G.png"/>
		<img class=goal id="2LG" src="2LG.png"/>
		<img class=goal id="1RG" src="1RG.png"/>
		<img class=goal id="1G" src="1G.png"/>
		<img class=goal id="1LG" src="1LG.png"/>
		<img class=goal id="0RG" src="0RG.png"/>
		<img class=goal id="0LG" src="0LG.png"/>
		<img id="5" src="5.png"/>
		<img id="4R" src="4R.png"/>
		<img id="4L" src="4L.png"/>
		<img id="4" src="4.png"/>
		<img id="3R" src="3R.png"/>
		<img id="3L" src="3L.png"/>
		<img id="3" src="3.png"/>
		<img id="2R" src="2R.png"/>
		<img id="2L" src="2L.png"/>
		<img id="2" src="2.png"/>
		<img id="1R" src="1R.png"/>
		<img id="1L" src="1L.png"/>
		<img id="1" src="1.png"/>
		<img id="0R" src="0R.png"/>
		<img id="0L" src="0L.png"/>
			
	</div>
</div>
<div id="maze_container">
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<fieldset>
<span><input type="button" value="Begin Maze" onclick="fetch('/jsonmazegrid/${title}').then((response) => response.json()).then((data) => onGenerateMaze(data))" /></span>
</fieldset>
	
</body>
</html>