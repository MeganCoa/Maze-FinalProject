package co.grandcircus.Maze.controller;

public class MazeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public MazeNotFoundException(String id) {
		
		super("Maze not found");
	}
}
