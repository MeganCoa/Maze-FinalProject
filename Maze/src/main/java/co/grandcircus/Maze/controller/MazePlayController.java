package co.grandcircus.Maze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.Maze.Service.MazeService;

@Controller
public class MazePlayController {
	@Autowired
	MazeService mazeService;
	
	
	@PostMapping("/playjsmaze")
	public ModelAndView displayMaze(@RequestParam String title, @RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn) {	
		
		ModelAndView modelAndView = new ModelAndView("playjsmaze");
		
		modelAndView.addObject("title", title);
		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);
		
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "/jsonmazegrid/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public int[][] jsonmazegrid(@PathVariable("title") String title) {
		return mazeService.jsonmazegrid(title);
	}
	
	@PostMapping("/povmaze")
	public ModelAndView displayPovMaze(@RequestParam String title, @RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn) {
		ModelAndView modelAndView = new ModelAndView("povmaze");
		
		System.out.println("\n " + title);
		modelAndView.addObject("title", title);
		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);
		
		return modelAndView;
	}
}
