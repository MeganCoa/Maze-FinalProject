package co.grandcircus.Maze.models;

//Coordinate class added to encompass both a row and column gridpoint in the maze
public class Coordinate {
	
	int x;
	int y;
	Coordinate parentCoordinate;	

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
		this.parentCoordinate = null;
	}
	
	public Coordinate(int x, int y, Coordinate parentCoordinate) {
		this.x = x;
		this.y = y;
		this.parentCoordinate = parentCoordinate;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Coordinate getParentCoordinate() {
		return parentCoordinate;
	}

	public void setParentCoordinate(Coordinate parentCoordinate) {
		this.parentCoordinate = parentCoordinate;
	}


}
