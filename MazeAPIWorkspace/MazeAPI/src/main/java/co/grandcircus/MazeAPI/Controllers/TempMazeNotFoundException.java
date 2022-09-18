package co.grandcircus.MazeAPI.Controllers;

public class TempMazeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public TempMazeNotFoundException(String id) {
		super("TempMaze not found");
	}
}
