package co.grandcircus.Maze.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.Maze.IconSearch.IconSearchService;
import co.grandcircus.Maze.models.Coordinate;
import co.grandcircus.Maze.models.Maze;
import co.grandcircus.Maze.repository.MazeRepository;
import co.grandcircus.Maze.repository.UserRepository;

@Controller
public class RandomMazeController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private MazeRepository mazeRepo;
	@Autowired
	private IconSearchService iconService;

	@GetMapping("/randommaze")
	public ModelAndView tempRandomDisplayMaze(@RequestParam(required = false) String username,
			@RequestParam(required = false) boolean loggedIn) {

		ModelAndView modelAndView = new ModelAndView("randommaze");
		Maze maze = mazeRepo.findByTitle("anon, working");
		
		int mazeRow = maze.getHeight();
		int mazeCol = maze.getWidth();
		int[][] mazegrid = maze.getMazeGrid();
				
		modelAndView.addObject("maze", maze);
		modelAndView.addObject("mazegrid", mazegrid);
		modelAndView.addObject("mazeRow",mazeRow);
		modelAndView.addObject("mazeCol", mazeCol);
		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);

		return modelAndView;
	}

	@PostMapping("/tempdisplaymaze")
	public ModelAndView tempdisplayMaze(@RequestParam String title, @RequestParam(required = false) String username,
			@RequestParam(required = false) boolean loggedIn, @RequestParam(required = false) Integer newMazeRating) {

		Maze maze = mazeRepo.findByTitle(title);

		if (newMazeRating != null) {
			maze.addAndUpdateRatings(newMazeRating);
			mazeRepo.save(maze);
		}

		ModelAndView modelAndView = new ModelAndView("tempdisplaymaze");

		modelAndView.addObject("symbolMaze", maze.mazeVisualizer());
		modelAndView.addObject("maze", maze);
		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);
		modelAndView.addObject("picture",
				iconService.getIconObjects("dog").getData().getObjects().get(5).getAssets().get(2).getUrl());

		return modelAndView;
	}

	@GetMapping("/tempcreatemaze")
	public ModelAndView tempCreateMaze(@RequestParam(required = false) String username,
			@RequestParam(required = false) boolean loggedIn) {

		ModelAndView modelAndView = new ModelAndView("tempcreatemaze");

		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);

		return modelAndView;
	}

	@PostMapping("/tempmazeeditor")
	public ModelAndView tempMazeEditor(@RequestParam String title, @RequestParam(required = false) String username,
			@RequestParam(required = false) boolean loggedIn, @RequestParam(required = false) Integer rows,
			@RequestParam(required = false) Integer columns) {

		ModelAndView modelAndView = new ModelAndView("tempmazeeditor");
		Maze maze;
		ArrayList<Coordinate> mazeGridCoordinates = new ArrayList<>();

		if (title.equals("")) {
			ModelAndView modelAndView2 = new ModelAndView("tempcreatemaze");

			modelAndView2.addObject("username", username);
			modelAndView2.addObject("loggedIn", loggedIn);
			modelAndView2.addObject("message", "Your maze must have a title.");
			return modelAndView2;
		}

		if (mazeRepo.findByTitle(title) != null) {
			// reject maze title
			if (rows != null) {
				ModelAndView modelAndView2 = new ModelAndView("tempcreatemaze");

				modelAndView2.addObject("username", username);
				modelAndView2.addObject("loggedIn", loggedIn);
				modelAndView2.addObject("message", "That maze title already exists.");
				return modelAndView2;
				// edit existing maze
			} else {
				maze = mazeRepo.findByTitle(title);
				rows = maze.getHeight();
				columns = maze.getWidth();
			}
			// create new maze
		} else {
			String authorName;

			if (username != "") {
				authorName = username;
			} else {
				authorName = "Anonymous";
			}

			maze = new Maze(title, authorName, rows, columns);
			mazeRepo.save(maze);
			if (!authorName.equals("Anonymous")) {
				userRepo.findAndPushToUserMazesByUsername(username, title);
			}
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (j == columns - 1) {
					mazeGridCoordinates.add(new Coordinate(i, j, maze.getMazeGrid()[i][j], true));
				} else {
					mazeGridCoordinates.add(new Coordinate(i, j, maze.getMazeGrid()[i][j], false));
				}
			}
		}

		modelAndView.addObject("maze", maze);
		modelAndView.addObject("mazegridcoordinates", mazeGridCoordinates);
		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);
		return modelAndView;
	}

	public boolean userMazeHasValidSolution(Maze maze) {

		// Generates new boolean[][] equal in size to the Maze's mazegrid, in order to
		// set the starting state visitedCoordinates property of the maze (not included
		// in constructor)
		boolean[][] visitedCoordinates = new boolean[maze.getHeight()][maze.getWidth()];

		for (int i = 0; i < visitedCoordinates.length; i++) {
			Arrays.fill(visitedCoordinates[i], false);
		}

		maze.setVisitedCoordinates(visitedCoordinates);

		final int[][] POSSIBLE_DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

		// Initialize a nextToVisit LinkedList and add the starting point
		LinkedList<Coordinate> nextToVisit = new LinkedList<>();
		Coordinate startPoint = maze.getMazeStart();
		nextToVisit.add(startPoint);

		// While the nextToVisitList is populated with viable coordinates, this checks
		// the state of them, removing each coordinate as it's evaluated
		while (!nextToVisit.isEmpty()) {
			Coordinate cur = nextToVisit.remove();

			// if this point isn't valid or has already been explored, continue to the next
			// point
			if (!maze.isThisValidLocation(cur.getX(), cur.getY()) || maze.isThisCellExplored(cur.getX(), cur.getY())) {
				continue;
			}

			// If this point is a wall, set it as visited and continue
			if (maze.isThisWall(cur.getX(), cur.getY())) {
				maze.setVisited(cur.getX(), cur.getY(), true);
				continue;
			}

			// If this point is the end of the maze, return true because the solution must
			// exist
			if (maze.isThisMazeEnd(cur.getX(), cur.getY())) {
				return true;
			}
			// Adds all possible direction coordinates from the current coordinate (cur) via
			// enhanced for loop. Also uses the Coordinate
			// constructor with parentCoordinate to log the current coordinate's parent.
			for (int[] direction : POSSIBLE_DIRECTIONS) {
				Coordinate coordinate = new Coordinate(cur.getX() + direction[0], cur.getY() + direction[1], cur);
				nextToVisit.add(coordinate);
				maze.setVisited(cur.getX(), cur.getY(), true);
			}
		}
		return false;
	}

	@PostMapping("/tempconfirmation")
	public ModelAndView tempConfirmation(@RequestParam String title, @RequestParam(required = false) String username,
			@RequestParam(required = false) boolean loggedIn,
			@RequestParam(required = false, value = "cellData") String[] cellDataList) {

		ModelAndView modelAndView = new ModelAndView("tempconfirmation");

		String message;

		ArrayList<Integer> cellDataArrayList = new ArrayList<>();

		for (String cellData : cellDataList) {
			cellDataArrayList.add(Integer.parseInt(cellData));
		}

		int rows = mazeRepo.findByTitle(title).getMazeGrid().length;
		int columns = mazeRepo.findByTitle(title).getMazeGrid()[0].length;

		int[][] newMazeGrid = new int[rows][columns];

		for (int i = 0; i < rows * columns; i++) {
			newMazeGrid[i / columns][i % columns] = cellDataArrayList.get(i);
		}
		mazeRepo.findAndUpdateMazeGridByTitle(title, newMazeGrid);

		// does maze have exactly one start and one end cell?
		if (startEndCount(cellDataArrayList, 2) != 1 || startEndCount(cellDataArrayList, 3) != 1) {

			// if they need a start, or need an end
			// if they have more than one start, or more than one end
			// if they got one right but not the other

			String startMessage = "";
			String endMessage = "";
			boolean startOkay = false;

			if (startEndCount(cellDataArrayList, 2) == 0) {
				startMessage = "You need to have a starting cell, ";
			} else if (startEndCount(cellDataArrayList, 2) > 1) {
				startMessage = "You can only have one starting cell, ";
			} else {
				startMessage = "Your starting point looks good, ";
				startOkay = true;
			}

			if (startEndCount(cellDataArrayList, 3) == 0) {
				if (startOkay) {
					endMessage = "but you need to have an ending cell!";
				} else {
					endMessage = "and you need to have an ending cell!";
				}
			} else if (startEndCount(cellDataArrayList, 3) > 1) {
				if (startOkay) {
					endMessage = "but you can only have one ending cell!";
				} else {
					endMessage = "and you can only have one ending cell!";
				}
			} else {
				endMessage = "but your end point looks good!";
			}

			modelAndView.addObject("invalidMaze", true);
			modelAndView.addObject("title", title);
			modelAndView.addObject("message", startMessage + endMessage);
			modelAndView.addObject("username", username);
			modelAndView.addObject("loggedIn", loggedIn);

			return modelAndView;
		}

		Coordinate startCoordinate = mazeRepo.findByTitle(title).getUserMazeStartCoordinate();
		Coordinate endCoordinate = mazeRepo.findByTitle(title).getUserMazeEndCoordinate();

		mazeRepo.findAndUpdateStartCoordinateByTitle(title, startCoordinate);
		mazeRepo.findAndUpdateEndCoordinateByTitle(title, endCoordinate);

		// does maze have valid solution?
		if (!userMazeHasValidSolution(mazeRepo.findByTitle(title))) {
			modelAndView.addObject("invalidMaze", true);
			modelAndView.addObject("title", title);
			message = "Your maze doesn't even have a solution...";
			modelAndView.addObject("message", message);
			modelAndView.addObject("username", username);
			modelAndView.addObject("loggedIn", loggedIn);

			return modelAndView;

		}
		message = "Your maze looks great!";

		modelAndView.addObject("message", message);
		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);
		modelAndView.addObject("title", title);
		modelAndView.addObject("invalidMaze", false);
		return modelAndView;
	}

	public int startEndCount(ArrayList<Integer> cellDataArrayList, int startOrEndCode) {
		int counter = 0;

		for (int i = 0; i < cellDataArrayList.size(); i++) {
			if (cellDataArrayList.get(i) == startOrEndCode) {
				counter++;
			}
		}
		return counter;
	}

}
