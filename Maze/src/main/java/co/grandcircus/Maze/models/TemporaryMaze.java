package co.grandcircus.Maze.models;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("temporarymazes")
public class TemporaryMaze extends Maze {
	
	public TemporaryMaze() {
		super();
	}
	
	public TemporaryMaze(String title, String authorName, int rows, int columns) {
		super();
		this.title = title;
		this.authorName = authorName;
		this.mazeGrid = new int[rows][columns];
		this.ratings = new ArrayList<Integer>();
	}
	
	public TemporaryMaze(Maze maze) {
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
