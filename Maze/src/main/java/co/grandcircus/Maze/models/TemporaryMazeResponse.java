package co.grandcircus.Maze.models;

import java.util.ArrayList;

public class TemporaryMazeResponse extends MazeResponse {
	
	public TemporaryMazeResponse() {
		super();
	}
	
	public TemporaryMazeResponse(String title, String authorName, int rows, int columns) {
		super();
		this.title = title;
		this.authorName = authorName;
		this.mazeGrid = new int[rows][columns];
		this.ratings = new ArrayList<Integer>();
	}
	
	public TemporaryMazeResponse(MazeResponse maze) {
		this.title = maze.getTitle();
		this.authorName = maze.getAuthorName();
		this.mazeGrid = maze.getMazeGrid();
		this.startCoordinate = maze.getStartCoordinate();
		this.endCoordinate = maze.getEndCoordinate();
		this.visitedCoordinates = maze.getVisitedCoordinates();
		this.specials = maze.getSpecials();
		this.playTotal = maze.getPlayTotal();
		this.ratings = maze.getRatings();
		this.avgRating = maze.getAvgRating();
	}
	
}
