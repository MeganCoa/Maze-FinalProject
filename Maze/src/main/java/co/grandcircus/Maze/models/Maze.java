package co.grandcircus.Maze.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("mazes")
public class Maze {
	
	//Add maze generating final variables to draw maze
	private static final int WALL = 0;
    private static final int OPENSPACE = 1;
    private static final int START = 2;
    private static final int EXIT = 3;
    private static final int PATH = 4;
	
	@Id
	private String id;
	private String title;
	private String authorName;
	private int[][] mazeGrid; //(0 = wall, 1 = open space, 2,3,4 = special)
	//removed start row, start column, end row, end column in favor of a starting Coordinate and ending Coordinate that encompasses both
	private Coordinate startCoordinate;
	private Coordinate endCoordinate;
	//adding a boolean double ArrayList to track Coordinates visited during BFS shortest path traversal
	private boolean[][] visitedCoordinates;
	private ArrayList<Special> specials; //(index of special corresponds to the order of specials above)
	private int playTotal; //(number of times people have played this maze)
	private ArrayList<Integer> ratings; //or a map? key=userID, value=rating
	private double avgRating;
	
	//constructors:
	public Maze() {
		super();
	}
	
	public Maze(String title, String authorName) {
		super();
		this.title = title;
		this.authorName = authorName;
	}
	
	//Updated Maze constructor to include start and end Coordinates in place of rows and columns
	public Maze(String title, String authorName, int[][] mazeGrid, Coordinate startCoordinate, Coordinate endCoordinate) {
		super();
		this.title = title;
		this.authorName = authorName;
		this.mazeGrid = mazeGrid;
		this.startCoordinate = startCoordinate;
		this.endCoordinate = endCoordinate;
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
	public int[][] getMazeGrid() {
		return mazeGrid;
	}
	public void setMazeGrid(int[][] mazeGrid) {
		this.mazeGrid = mazeGrid;
	}
	public Coordinate getStartCoordinate() {
		return startCoordinate;
	}

	public void setStartCoordinate(Coordinate startCoordinate) {
		this.startCoordinate = startCoordinate;
	}

	public Coordinate getEndCoordinate() {
		return endCoordinate;
	}

	public void setEndCoordinate(Coordinate endCoordinate) {
		this.endCoordinate = endCoordinate;
	}

	public boolean[][] getVisitedCoordinates() {
		return visitedCoordinates;
	}

	public void setVisitedCoordinates(boolean[][] visitedCoordinates) {
		this.visitedCoordinates = visitedCoordinates;
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
	
	//Maze generation and traversal logic:
	
	//int[height][width] mazeGrid
	public int getHeight() {
        return mazeGrid.length;
    }
	
	//int[height][width] mazeGrid
    public int getWidth() {
        return mazeGrid[0].length;
    }

    public Coordinate getMazeStart() {
        return startCoordinate;
    }

    public Coordinate getMazeEnd() {
        return endCoordinate;
    }

    //Checks a gridpoints x and y values against the end coordinates x and y values. 
    public boolean isThisMazeEnd(int x, int y) {
        return x == endCoordinate.getX() && y == endCoordinate.getY();
    }

    //Checks a gridpoints x and y values against the start coordinates x and y values.
    public boolean isThisMazeEntrance(int x, int y) {
        return x == startCoordinate.getX() && y == startCoordinate.getY();
    }

    //Checks the visitedCoordinates double boolean array to see if a given gridpoint has been visisted
    public boolean isThisCellExplored(int row, int col) {
        return visitedCoordinates[row][col];
    }

    public boolean isThisWall(int row, int col) {
        return mazeGrid[row][col] == WALL;
    }

    //Sets a given gridpoints Coordinate as visited
    public void setVisited(int row, int col, boolean value) {
        visitedCoordinates[row][col] = value;
    }

    //Checks if a given gridpoint is valid for the maze
    public boolean isThisValidLocation(int row, int col) {
        if (row < 0 || row >= getHeight() || col < 0 || col >= getWidth()) {
            return false;
        }
        return true;
    }

    //Prints the BFS-determined shortest path through the maze (implementation in ShortestPathChecker)
    public void printPath(List<Coordinate> path) {
        int[][] tempMaze = Arrays.stream(mazeGrid)
            .map(int[]::clone)
            .toArray(int[][]::new);
        for (Coordinate coordinate : path) {
            if (isThisMazeEntrance(coordinate.getX(), coordinate.getY()) || isThisMazeEnd(coordinate.getX(), coordinate.getY())) {
                continue;
            }
            tempMaze[coordinate.getX()][coordinate.getY()] = PATH;
        }
        System.out.println(toString(tempMaze));
    }

    //Prints a simple visual representation of a maze
    public String toString(int[][] maze) {
        StringBuilder result = new StringBuilder(getWidth() * (getHeight() + 1));
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if (maze[row][col] == 0) { //Based on final variables, 0 generates a wall 
                    result.append('#');
                } else if (maze[row][col] == 1) { //Based on final variables, 1 generates open space
                    result.append(' ');
                } else if (maze[row][col] == 2) { //Based on final variables, 2 generates maze start point
                    result.append('S');
                } else if (maze[row][col] == 3) { //Based on final variables, 3 generates maze end point
                    result.append('E');
                } else {
                    result.append('.'); //Everything else is the path
                }
            }
            result.append('\n');
        }
        return result.toString();
    }
    
    //Resets the visitedCoordinates double array to read false across the board (not accessed)
    public void reset() {
        for (int i = 0; i < visitedCoordinates.length; i++)
            Arrays.fill(visitedCoordinates[i], false);
    }
		
}
