package co.grandcircus.Maze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.Maze.models.Maze;
import co.grandcircus.Maze.repository.MazeRepository;

@Controller
public class MazeDisplayController {
	
	@Autowired
	private MazeRepository repo;
	
	@RequestMapping("/")
	public ModelAndView displayMaze() {
		
		Maze maze = repo.findByAuthorName("Clara Balmer");
		ModelAndView modelAndView = new ModelAndView("index");
		
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
	       
	        modelAndView.addObject("maze", result.toString());
	        return modelAndView;
	}
	
}
