package co.grandcircus.Maze.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import java.util.List;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.Maze.IconSearch.IconSearchService;
import co.grandcircus.Maze.models.Coordinate;
import co.grandcircus.Maze.models.Maze;
import co.grandcircus.Maze.models.TemporaryMaze;
import co.grandcircus.Maze.models.User;
import co.grandcircus.Maze.repository.MazeRepository;
import co.grandcircus.Maze.repository.TemporaryMazeRepository;
import co.grandcircus.Maze.repository.UserRepository;

@Controller
public class MazeDisplayController {

	@Autowired
	private MazeRepository mazeRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TemporaryMazeRepository tempMazeRepo;
	@Autowired
	private IconSearchService iconService;


	@PostMapping("/displaymaze")
	public ModelAndView displayMaze(@RequestParam String title, @RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn, @RequestParam(required=false) Integer newMazeRating) {
		
		Maze maze = mazeRepo.findByTitle(title);
		
		if (newMazeRating != null) {
			maze.addAndUpdateRatings(newMazeRating);
			mazeRepo.save(maze);
		}
		
		ModelAndView modelAndView = new ModelAndView("displaymaze");
	       
		modelAndView.addObject("symbolMaze", maze.mazeVisualizer());
		modelAndView.addObject("maze", maze);
		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);
		modelAndView.addObject("picture", iconService.getIconObjects("dog").getData().getObjects().get(5).getAssets().get(2).getUrl());
		
		return modelAndView;
	}
	
	@PostMapping("/solvemaze")
	public ModelAndView solveMaze(@RequestParam String title, @RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn) {
		
		Maze maze = mazeRepo.findByTitle(title);
		ModelAndView modelAndView = new ModelAndView("solvemaze");
		
		//Generates new boolean[][] equal in size to the Maze's mazegrid, in order to set the starting state visitedCoordinates property of the maze (not included in constructor)
		boolean[][] visitedCoordinates = new boolean[maze.getHeight()][maze.getWidth()];
		
		for (int i = 0; i < visitedCoordinates.length; i++) {
            Arrays.fill(visitedCoordinates[i], false);
		}
		
		maze.setVisitedCoordinates(visitedCoordinates);
		
		final int[][] POSSIBLE_DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		
		//Initialize a nextToVisit LinkedList and add the starting point
        LinkedList<Coordinate> nextToVisit = new LinkedList<>();
        Coordinate startPoint = maze.getMazeStart();
        nextToVisit.add(startPoint);

        //While the nextToVisitList is populated with viable coordinates, this checks the state of them, removing each coordinate as it's evaluated 
        while (!nextToVisit.isEmpty()) {
            Coordinate cur = nextToVisit.remove();

            //if this point isn't valid or has already been explored, continue to the next point
            if (!maze.isThisValidLocation(cur.getX(), cur.getY()) || maze.isThisCellExplored(cur.getX(), cur.getY())) {
                continue;
            }
            
            //If this point is a wall, set it as visited and continue
            if (maze.isThisWall(cur.getX(), cur.getY())) {
                maze.setVisited(cur.getX(), cur.getY(), true);
                continue;
            }
            
            //If this point is the end of the maze, we backtrack to add the shortest path to the mazegrid (=4), then pass the solution to a JSP
            if (maze.isThisMazeEnd(cur.getX(), cur.getY())) {
            	int[][] tempMaze = maze.getMazeGrid();
            	Coordinate pathCoordinate = cur;

               while (pathCoordinate != null) {
                   tempMaze[pathCoordinate.getX()][pathCoordinate.getY()] = 4;
                   pathCoordinate = pathCoordinate.getParentCoordinate();
               }
     	       
     	       String result = maze.mazeVisualizer();
                
     	        modelAndView.addObject("symbolMaze", result);
     	        modelAndView.addObject("title", title);
     	        modelAndView.addObject("username", username);
     	       	modelAndView.addObject("loggedIn", loggedIn);
     	        return modelAndView;                    
            }

            //Adds all possible direction coordinates from the current coordinate (cur) via enhanced for loop. Also uses the Coordinate 
            //constructor with parentCoordinate to log the current coordinate's parent. 
            for (int[] direction : POSSIBLE_DIRECTIONS) {
                Coordinate coordinate = new Coordinate(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                nextToVisit.add(coordinate);
                maze.setVisited(cur.getX(), cur.getY(), true);
            }
        }
        return modelAndView;
	}
	
	@PostMapping("/mazeeditor")
	public ModelAndView mazeEditor(@RequestParam String title, @RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn, @RequestParam(required=false) Integer rows, @RequestParam(required=false) Integer columns) {
		
		ModelAndView modelAndView = new ModelAndView("mazeeditor");
		TemporaryMaze tempMaze;
		
		if (title.equals("")) {
			ModelAndView modelAndView2 = new ModelAndView("createmaze");
			
			modelAndView2.addObject("username", username);
		    modelAndView2.addObject("loggedIn", loggedIn);
			modelAndView2.addObject("message", "Your maze must have a title.");
			return modelAndView2;
		}
		
		//if maze title already exists...
		if (mazeRepo.findByTitle(title) != null || tempMazeRepo.findByTitle(title) != null) {
			
			//reject if they're trying to create one with that title
			if (rows != null) {
				ModelAndView modelAndView2 = new ModelAndView("createmaze");
				
				modelAndView2.addObject("username", username);
			    modelAndView2.addObject("loggedIn", loggedIn);
				modelAndView2.addObject("message", "That maze title already exists.");
				return modelAndView2;
			
			//if editing maze from maze repo:
			} else if (mazeRepo.findByTitle(title) != null) {
				
				//create new tempMaze for editing
				Maze maze = mazeRepo.findByTitle(title);
				tempMaze = new TemporaryMaze(maze);
				
				//remove maze from regular db
				mazeRepo.deleteByTitle(title);

				//remove maze from userMazes
				User user = userRepo.findByUsername(username).get();
				user.getUserMazes().remove(title);
				userRepo.save(user);
				
				//add to userTempMazes
				userRepo.findAndPushToUserTempMazesByUsername(username, title);
				
				rows = tempMaze.getHeight();
				columns = tempMaze.getWidth();
				
			//if editing from temp maze repo (previous page):
			} else {
				tempMaze = tempMazeRepo.findByTitle(title);
				rows = tempMaze.getHeight();
				columns = tempMaze.getWidth();
			}
		//create new maze
		} else {
			String authorName;
			
			if(username != "") {
				authorName = username;
			} else {
				authorName = "Anonymous";
			}
			
			tempMaze = new TemporaryMaze(title, authorName, rows, columns);
			
			if (!authorName.equals("Anonymous")) {
				userRepo.findAndPushToUserTempMazesByUsername(username, title);
			}
		}
		
		tempMazeRepo.save(tempMaze);
		
		ArrayList<Coordinate> mazeGridCoordinates = new ArrayList<>();
		
		//sets mazeGridCoordinates for displaying to editor page
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(j == columns - 1) {
					mazeGridCoordinates.add(new Coordinate(i, j, tempMaze.getMazeGrid()[i][j], true)); 
				}else {
					mazeGridCoordinates.add(new Coordinate(i, j, tempMaze.getMazeGrid()[i][j], false));  
				}
			}
		}
		
		modelAndView.addObject("maze", tempMaze);
		modelAndView.addObject("mazegridcoordinates", mazeGridCoordinates);
	    modelAndView.addObject("username", username);
	    modelAndView.addObject("loggedIn", loggedIn);
		return modelAndView;
	}
	
	@PostMapping("/playtest")
	public ModelAndView confirmPlayTest(@RequestParam String title, @RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn, @RequestParam(required=false,value="userPathData") String[] userPathDataList) {
		ModelAndView modelAndView = new ModelAndView("playresults");
		String message = "";
		boolean successful = false;
		
		Maze maze = mazeRepo.findByTitle(title);
		maze.setPlayTotal(maze.getPlayTotal() + 1);
		mazeRepo.save(maze);
		
		ArrayList<Integer> userPathDataArrayList = new ArrayList<>();
		for (String userPathData : userPathDataList) {
			userPathDataArrayList.add(Integer.parseInt(userPathData));
		}
		//check if their path goes from maze start to maze end:
		if (isUserSolutionValid(title, userPathDataArrayList)) {
			message = "Great Job!";
			successful = true;
		} else {
			message = "Dang, that path doesn't work.";
		}
		
		modelAndView.addObject("successful", successful);
		modelAndView.addObject("message", message);
		modelAndView.addObject("title", title);
		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);
		return modelAndView;
	}
	
	@PostMapping("/confirmation")
	public ModelAndView confirmation(@RequestParam String title, @RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn, @RequestParam(required=false,value="cellData") String[] cellDataList) {
		
		ModelAndView modelAndView = new ModelAndView("confirmation");
		
		ArrayList<Integer> cellDataArrayList = new ArrayList<>();
		
		for (String cellData : cellDataList) {
			cellDataArrayList.add(Integer.parseInt(cellData));
		}
		
		int rows;
		int columns;
		
		//pull rows and columns from mazeGrid (from the correct DB collection):
//		if (mazeRepo.findByTitle(title) != null) {
//			rows = mazeRepo.findByTitle(title).getMazeGrid().length;
//			columns = mazeRepo.findByTitle(title).getMazeGrid()[0].length;
//		} else {
			rows = tempMazeRepo.findByTitle(title).getMazeGrid().length;
			columns = tempMazeRepo.findByTitle(title).getMazeGrid()[0].length;
//		}
		
		int[][] newMazeGrid = new int[rows][columns];
		
		for (int i = 0; i < rows * columns; i++) {
			newMazeGrid[i/columns][i%columns] = cellDataArrayList.get(i);
		}
		tempMazeRepo.findAndUpdateMazeGridByTitle(title, newMazeGrid);
		
		//does maze have exactly one start and one end cell?
		if (startEndCount(cellDataArrayList, 2) != 1 || startEndCount(cellDataArrayList, 3) != 1) {
			
			//if they need a start, or need an end
			//if they have more than one start, or more than one end
			//if they got one right but not the other
			
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
		
		Coordinate startCoordinate = tempMazeRepo.findByTitle(title).getUserMazeStartCoordinate();
		Coordinate endCoordinate = tempMazeRepo.findByTitle(title).getUserMazeEndCoordinate();
		
		tempMazeRepo.findAndUpdateStartCoordinateByTitle(title, startCoordinate);
		tempMazeRepo.findAndUpdateEndCoordinateByTitle(title, endCoordinate);
		
		//does maze have valid solution?
		if(!userMazeHasValidSolution(tempMazeRepo.findByTitle(title))) {
			modelAndView.addObject("invalidMaze", true);
			modelAndView.addObject("title", title);
			modelAndView.addObject("message", "Your maze doesn't even have a solution...");
			modelAndView.addObject("username", username);
		    modelAndView.addObject("loggedIn", loggedIn);
		    
			return modelAndView;
		}
		
		Maze maze = new Maze(tempMazeRepo.findByTitle(title));
		mazeRepo.save(maze);
		tempMazeRepo.deleteByTitle(title);
		
		if (userRepo.findByUsername(username).isPresent()) {
			//if user is logged in, delete maze from usertempMazes, add it to userMazes
			User user = userRepo.findByUsername(username).get();
			user.getUserTempMazes().remove(title);
			userRepo.save(user);
			userRepo.findAndPushToUserMazesByUsername(username, title);
		}
		
		modelAndView.addObject("message", "Your maze looks great!");
		modelAndView.addObject("username", username);
	    modelAndView.addObject("loggedIn", loggedIn);
	    modelAndView.addObject("title", title);
	    modelAndView.addObject("invalidMaze", false);
		return modelAndView;
	}
	
	@PostMapping("/deleteusermaze")
	public ModelAndView deleteUserMaze(@RequestParam String title, @RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn, @RequestParam(required=false) boolean invalidMaze) {
		
		ModelAndView modelAndView = new ModelAndView("deleteconfirmation");
		
		//delete maze from databases
		if (mazeRepo.findByTitle(title) != null) {
			mazeRepo.deleteByTitle(title);
		} else {
			tempMazeRepo.deleteByTitle(title);
		}
		
		//delete maze from user's lists (owner of maze)
		String message = ".";
		if (userRepo.findByUsername(username).isPresent()) {
			User user = userRepo.findByUsername(username).get();
			if (userRepo.findByUsername(username).get().getUserMazes().contains(title)) {
				user.getUserMazes().remove(title);
				message = " from your mazes.";
			} else if (userRepo.findByUsername(username).get().getUserTempMazes().contains(title)) {
				user.getUserTempMazes().remove(title);
				message = " from your mazes in progress.";
			}
			userRepo.save(user);
		}
		
		//delete maze from all other users favorites lists
		List<User> allUsers = userRepo.findAll();
		for (User eachUser : allUsers) {
			if (eachUser.getUserFavorites().contains(title)) {
				eachUser.getUserFavorites().remove(title);
				userRepo.save(eachUser);
			}
		}
		
		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);
		modelAndView.addObject("message", "We deleted " + title + message);
		return modelAndView;
	}
	@PostMapping("/usersolvemaze")
	public ModelAndView userSolveMaze(@RequestParam String title, @RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn) {
		ModelAndView modelAndView = new ModelAndView("playmaze");
		
		Maze maze = mazeRepo.findByTitle(title);
		int rows = maze.getHeight();
		int columns = maze.getWidth();
		ArrayList<Coordinate> mazeGridCoordinates = new ArrayList<>();
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(j == columns - 1) {
					mazeGridCoordinates.add(new Coordinate(i, j, maze.getMazeGrid()[i][j], true)); 
				}else {
					mazeGridCoordinates.add(new Coordinate(i, j, maze.getMazeGrid()[i][j], false));  
				}
			}
		}
		modelAndView.addObject("mazegridcoordinates", mazeGridCoordinates);
		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);
		modelAndView.addObject("symbolMaze", maze.mazeVisualizer());
		modelAndView.addObject("maze", maze);
		return modelAndView;
	}
	public boolean userMazeHasValidSolution(Maze maze) {
		
		//Generates new boolean[][] equal in size to the Maze's mazegrid, in order to set the starting state visitedCoordinates property of the maze (not included in constructor)
		boolean[][] visitedCoordinates = new boolean[maze.getHeight()][maze.getWidth()];
			
		for (int i = 0; i < visitedCoordinates.length; i++) {
			Arrays.fill(visitedCoordinates[i], false);
		}
			
		maze.setVisitedCoordinates(visitedCoordinates);
			
		final int[][] POSSIBLE_DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
			
		//Initialize a nextToVisit LinkedList and add the starting point
	    LinkedList<Coordinate> nextToVisit = new LinkedList<>();
	    Coordinate startPoint = maze.getMazeStart();
	    nextToVisit.add(startPoint);

	    //While the nextToVisitList is populated with viable coordinates, this checks the state of them, removing each coordinate as it's evaluated 
	    while (!nextToVisit.isEmpty()) {
	    	Coordinate cur = nextToVisit.remove();

	    	//if this point isn't valid or has already been explored, continue to the next point
	    	if (!maze.isThisValidLocation(cur.getX(), cur.getY()) || maze.isThisCellExplored(cur.getX(), cur.getY())) {
	    		continue;
	    	}
	            
	    	//If this point is a wall, set it as visited and continue
	    	if (maze.isThisWall(cur.getX(), cur.getY())) {
	    		maze.setVisited(cur.getX(), cur.getY(), true);
	    		continue;
	    	}
	            
	    	//If this point is the end of the maze, return true because the solution must exist
	    	if (maze.isThisMazeEnd(cur.getX(), cur.getY())) {		
	    		return true;
	    	}
	    	//Adds all possible direction coordinates from the current coordinate (cur) via enhanced for loop. Also uses the Coordinate 
            //constructor with parentCoordinate to log the current coordinate's parent. 
            for (int[] direction : POSSIBLE_DIRECTIONS) {
                Coordinate coordinate = new Coordinate(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                nextToVisit.add(coordinate);
                maze.setVisited(cur.getX(), cur.getY(), true);
            }
	    }
	    return false;	
	}
	
	public boolean isUserSolutionValid(String title, ArrayList<Integer> userPathDataArrayList) {
		Maze maze = mazeRepo.findByTitle(title);
		
		//Generates new boolean[][] equal in size to the Maze's mazegrid, in order to set the starting state visitedCoordinates property of the maze (not included in constructor)
		boolean[][] visitedCoordinates = new boolean[maze.getHeight()][maze.getWidth()];
				
		maze.setVisitedCoordinates(visitedCoordinates);
		
		//fills visited coordinates as true
		for (int i = 0; i < visitedCoordinates.length; i++) {
            Arrays.fill(visitedCoordinates[i], true);
		}
		//then changes user's path cells to false
		for (int i = 0; i < (maze.getHeight() * maze.getWidth()); i++) {
            if (userPathDataArrayList.get(i) == 4) {
            	visitedCoordinates[i/maze.getWidth()][i%maze.getWidth()] = false;
            }
		}
		
		final int[][] POSSIBLE_DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		
		//Initialize a nextToVisit LinkedList and add the starting point
        LinkedList<Coordinate> nextToVisit = new LinkedList<>();
        Coordinate startPoint = maze.getMazeStart();
        nextToVisit.add(startPoint);

        //While the nextToVisitList is populated with viable coordinates, this checks the state of them, removing each coordinate as it's evaluated 
        while (!nextToVisit.isEmpty()) {
            Coordinate cur = nextToVisit.remove();

            //if this point isn't valid or has already been explored, continue to the next point
            if (!maze.isThisValidLocation(cur.getX(), cur.getY()) || maze.isThisCellExplored(cur.getX(), cur.getY())) {
                continue;
            }
            
            //If this point is a wall, set it as visited and continue
            if (maze.isThisWall(cur.getX(), cur.getY())) {
                maze.setVisited(cur.getX(), cur.getY(), true);
                continue;
            }
            
            //If this point is the end of the maze, we backtrack to add the shortest path to the mazegrid (=4), then pass the solution to a JSP
            if (maze.isThisMazeEnd(cur.getX(), cur.getY())) {
            	return true;
            }

            //Adds all possible direction coordinates from the current coordinate (cur) via enhanced for loop. Also uses the Coordinate 
            //constructor with parentCoordinate to log the current coordinate's parent. 
            for (int[] direction : POSSIBLE_DIRECTIONS) {
                Coordinate coordinate = new Coordinate(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                nextToVisit.add(coordinate);
                maze.setVisited(cur.getX(), cur.getY(), true);
            }
        }
		
		return false;
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
