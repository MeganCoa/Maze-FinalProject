package co.grandcircus.Maze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.Maze.Service.MazeService;
import co.grandcircus.Maze.models.MazeResponse;

@Controller
public class RandomMazeController {
	@Autowired
	MazeService mazeService;
	
	@GetMapping("/randommaze")
	public ModelAndView displayMaze(@RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn) {	
		
		ModelAndView modelAndView = new ModelAndView("temprandommaze");
		
		MazeResponse maze = mazeService.findByTitle("amaze");
		int rows = maze.getHeight();
		int columns = maze.getWidth();
		String strMazeGrid = "";
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				strMazeGrid += maze.getMazeGrid()[i][j];
			}
		}
		
		modelAndView.addObject("strMazeGrid", strMazeGrid);
		modelAndView.addObject("rows", rows);
		modelAndView.addObject("columns", columns);
		
		
		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);
		
		return modelAndView;
	}
	@GetMapping("/povmaze")
	public ModelAndView displayPovMaze(@RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn) {
		ModelAndView modelAndView = new ModelAndView("povmaze");
		
		
		return modelAndView;
	}
}
