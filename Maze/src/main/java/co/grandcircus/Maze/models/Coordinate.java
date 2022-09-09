package co.grandcircus.Maze.models;

//Coordinate class added to encompass both a row and column gridpoint in the maze
public class Coordinate {
	
	int x;
	int y;
	Coordinate parentCoordinate;
	int coordinateValue;
	boolean endOfLine;
	
	public Coordinate() {
		super();
	}

	public Coordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.parentCoordinate = null;
	}
	
	public Coordinate(int x, int y, Coordinate parentCoordinate) {
		super();
		this.x = x;
		this.y = y;
		this.parentCoordinate = parentCoordinate;
	}
	
	public Coordinate(int x, int y, int coordinateValue, boolean endOfLine) {
		super();
		this.x = x;
		this.y = y;
		this.coordinateValue = coordinateValue;
		this.endOfLine = endOfLine;
	}
	
	public boolean isEndOfLine() {
		return endOfLine;
	}

	public void setEndOfLine(boolean endOfLine) {
		this.endOfLine = endOfLine;
	}
	
	public int getCoordinateValue() {
		return coordinateValue;
	}

	public void setCoordinateValue(int coordinateValue) {
		this.coordinateValue = coordinateValue;
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
