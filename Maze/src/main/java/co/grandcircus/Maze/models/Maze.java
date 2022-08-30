package co.grandcircus.Maze.models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("mazes")
public class Maze {
	@Id
	private String id;
	private String title;
	private String authorName;
	private ArrayList<ArrayList<Integer>> mazeGrid; //(0 = wall, 1 = open space, 2,3,4 = special)
	private int startRow;
	private int startCol;
	private int endRow;
	private int endCol;
	private ArrayList<Special> specials; //(index of special corresponds to the order of specials above)
	private int playTotal; //(number of times people have played this maze)
	private ArrayList<Integer> ratings; //or a map? key=userID, value=rating
	private double avgRating;
	//constructors:
	public Maze() {
		super();
	}
	
	public Maze(String title, String authorName, ArrayList<ArrayList<Integer>> mazeGrid, int startRow, int startCol,
			int endRow, int endCol, ArrayList<Special> specials, int playTotal, ArrayList<Integer> ratings,
			double avgRating) {
		super();
		this.title = title;
		this.authorName = authorName;
		this.mazeGrid = mazeGrid;
		this.startRow = startRow;
		this.startCol = startCol;
		this.endRow = endRow;
		this.endCol = endCol;
		this.specials = specials;
		this.playTotal = playTotal;
		this.ratings = ratings;
		this.avgRating = avgRating;
	}

	//getters and setters:
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public ArrayList<ArrayList<Integer>> getMazeGrid() {
		return mazeGrid;
	}
	public void setMazeGrid(ArrayList<ArrayList<Integer>> mazeGrid) {
		this.mazeGrid = mazeGrid;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getStartCol() {
		return startCol;
	}
	public void setStartCol(int startCol) {
		this.startCol = startCol;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getEndCol() {
		return endCol;
	}
	public void setEndCol(int endCol) {
		this.endCol = endCol;
	}
	public ArrayList<Special> getSpecials() {
		return specials;
	}
	public void setSpecials(ArrayList<Special> specials) {
		this.specials = specials;
	}
	public int getPlayTotal() {
		return playTotal;
	}
	public void setPlayTotal(int playTotal) {
		this.playTotal = playTotal;
	}
	public ArrayList<Integer> getRatings() {
		return ratings;
	}
	public void setRatings(ArrayList<Integer> ratings) {
		this.ratings = ratings;
	}
	public double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
}
