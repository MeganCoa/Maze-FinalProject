package co.grandcircus.Maze.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.Maze.models.Maze;

@Controller
public class RandomMazeController {
	@GetMapping("/temprandommaze")
	public ModelAndView displayMaze(@RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn) {	
		
		ModelAndView modelAndView = new ModelAndView("temprandommaze");
	       
		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);
		
		return modelAndView;
	}
	@GetMapping("/tempcreatemaze")
	public ModelAndView tempCreateMaze(@RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn) {	
		
		ModelAndView modelAndView = new ModelAndView("tempcreatemaze");
	       
		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);
		
		return modelAndView;
	}
	
	
}
