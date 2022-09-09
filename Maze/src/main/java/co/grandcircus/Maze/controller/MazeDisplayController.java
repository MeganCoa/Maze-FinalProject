package co.grandcircus.Maze.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.Maze.models.Coordinate;
import co.grandcircus.Maze.models.Maze;
import co.grandcircus.Maze.models.ShortestPathChecker;
import co.grandcircus.Maze.models.User;
import co.grandcircus.Maze.repository.MazeRepository;
import co.grandcircus.Maze.repository.UserRepository;

@Controller
public class MazeDisplayController {
	
	@Autowired
	private MazeRepository mazeRepo;
	@Autowired
	private UserRepository userRepo;

	@PostMapping("/displaymaze")
	public ModelAndView displayMaze(@RequestParam String title, @RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn) {
		
		Maze maze = mazeRepo.findByTitle(title);
		ModelAndView modelAndView = new ModelAndView("displaymaze");
		
		 StringBuilder result = new StringBuilder(maze.getWidth() * (maze.getHeight() + 1));
	        for (int row = 0; row < maze.getHeight(); row++) {
	            for (int col = 0; col < maze.getWidth(); col++) {
	                if (maze.getMazeGrid()[row][col] == 0) { //Based on final variables, 0 generates a wall 
	                    result.append("#  ");
	                } else if (maze.getMazeGrid()[row][col] == 1) { //Based on final variables, 1 generates open space
	                    result.append("0  ");
	                } else if (maze.getMazeGrid()[row][col] == 2) { //Based on final variables, 2 generates maze start point
	                    result.append("S  ");
	                } else if (maze.getMazeGrid()[row][col] == 3) { //Based on final variables, 3 generates maze end point
	                    result.append("E  ");
	                } else {
	                    result.append('.'); //Everything else is the path
	                }
	            }
	            result.append("<br>");
	        }
	       
	        modelAndView.addObject("symbolMaze", result.toString());
	        modelAndView.addObject("maze", maze);
	        modelAndView.addObject("username", username);
	        modelAndView.addObject("loggedIn", loggedIn);
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
                        
                StringBuilder result = new StringBuilder(maze.getWidth() * (maze.getHeight() + 1));
     	        for (int row = 0; row < maze.getHeight(); row++) {
     	            for (int col = 0; col < maze.getWidth(); col++) {
     	                if (tempMaze[row][col] == 0) { //Based on final variables, 0 generates a wall 
     	                    result.append("#  ");
     	                } else if (tempMaze[row][col] == 1) { //Based on final variables, 1 generates open space
     	                    result.append("0  ");
     	                } else if (tempMaze[row][col] == 2) { //Based on final variables, 2 generates maze start point
     	                    result.append("S  ");
     	                } else if (tempMaze[row][col] == 3) { //Based on final variables, 3 generates maze end point
     	                    result.append("E  ");
     	                } else {
     	                    result.append(".  "); //Everything else is the path
     	                }
     	            }
     	            result.append("<br>");
     	        }
                
     	        modelAndView.addObject("symbolMaze", result.toString());
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
		Maze maze;
		ArrayList<Coordinate> mazeGridCoordinates = new ArrayList<>();
		
		if (mazeRepo.findByTitle(title) != null) {
			//reject maze title
			if (rows != null) {
				ModelAndView modelAndView2 = new ModelAndView("createmaze");
				
				modelAndView2.addObject("username", username);
			    modelAndView2.addObject("loggedIn", loggedIn);
				modelAndView2.addObject("message", "That maze title already exists.");
				return modelAndView2;
			//edit existing maze
			} else {
				maze = mazeRepo.findByTitle(title);
				rows = maze.getHeight();
				columns = maze.getWidth();
			}
		//create new maze
		} else {
			String authorName;
			
			if(username != null) {
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
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(j == columns - 1) {
					mazeGridCoordinates.add(new Coordinate(i, j, maze.getMazeGrid()[i][j], true)); 
				}else {
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
	
	@PostMapping("/creationconfirmation")
	public ModelAndView creationConfirmation(@RequestParam String title, @RequestParam(required=false,value="cellData") String[] cellDataList, @RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn, @RequestParam(required=false) String message) {
		
		ModelAndView modelAndView = new ModelAndView("creationconfirmation");
		
		ArrayList<Integer> cellDataArrayList = new ArrayList<>();
		for (String cellData : cellDataList) {
			cellDataArrayList.add(Integer.parseInt(cellData));
		}
		
		int rows = mazeRepo.findByTitle(title).getMazeGrid().length;
		int columns = mazeRepo.findByTitle(title).getMazeGrid()[0].length;
		
		int[][] newMazeGrid = new int[rows][columns];
		
		for (int i = 0; i < rows * columns; i++) {
			newMazeGrid[i/columns][i%columns] = cellDataArrayList.get(i);  
		}
		
		mazeRepo.findAndUpdateMazeGridByTitle(title, newMazeGrid);
		
		modelAndView.addObject("username", username);
	    modelAndView.addObject("loggedIn", loggedIn);
	    modelAndView.addObject("title", title);
	    modelAndView.addObject("message", message);
		return modelAndView;
	}
	
	@PostMapping("/deleteusermaze")
	public ModelAndView deleteUserMaze(@RequestParam String title, @RequestParam String username, @RequestParam boolean loggedIn, Model model) {
		
		ModelAndView modelAndView = new ModelAndView("deleteconfirmation");
		
		mazeRepo.deleteByTitle(title);
		User user = userRepo.findByUsername(username).get();
		user.getUserMazes().remove(title);
		userRepo.save(user);
		
		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);
		modelAndView.addObject("message", "We deleted " + title + " for you.");
		return modelAndView;
	}
	@PostMapping("/deleteuserfavorite")
	public ModelAndView deleteUserFavorite(@RequestParam String title, @RequestParam String username, @RequestParam boolean loggedIn) {
		ModelAndView modelAndView = new ModelAndView("deleteconfirmation");
		
		User user = userRepo.findByUsername(username).get();
		user.getUserFavorites().remove(title);
		userRepo.save(user);
		
		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);
		modelAndView.addObject("message", "We deleted " + title + " from your favorites.");
		
		return modelAndView;
	}
	
	public String mazeDisplayWriter(String title) {
		
		Maze maze = mazeRepo.findByTitle(title);
		
		StringBuilder result = new StringBuilder(maze.getWidth() * (maze.getHeight() + 1));
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                if (maze.getMazeGrid()[row][col] == 0) { //Based on final variables, 0 generates a wall 
                    result.append("#  ");
                } else if (maze.getMazeGrid()[row][col] == 1) { //Based on final variables, 1 generates open space
                    result.append("0  ");
                } else if (maze.getMazeGrid()[row][col] == 2) { //Based on final variables, 2 generates maze start point
                    result.append("S  ");
                } else if (maze.getMazeGrid()[row][col] == 3) { //Based on final variables, 3 generates maze end point
                    result.append("E  ");
                } else {
                    result.append('.'); //Everything else is the path
                }
            }
            result.append("<br>");
        }
        return result.toString();
	}
	
	}
