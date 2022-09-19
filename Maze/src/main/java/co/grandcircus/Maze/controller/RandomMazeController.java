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
import co.grandcircus.Maze.Service.MazeService;
import co.grandcircus.Maze.Service.UserService;
import co.grandcircus.Maze.models.Coordinate;
import co.grandcircus.Maze.models.MazeResponse;


@Controller
public class RandomMazeController {

	@Autowired
	private MazeService mazeService;
	@Autowired
	private UserService userService;
	@Autowired
	private IconSearchService iconService;

	@GetMapping("/temprandommaze")
	public ModelAndView tempRandomDisplayMaze(@RequestParam(required = false) String username,
			@RequestParam(required = false) boolean loggedIn) {

		ModelAndView modelAndView = new ModelAndView("temprandommaze");
		MazeResponse maze = mazeService.findByTitle("maze1");
		
		int mazeRow = maze.getHeight();
		int mazeCol = maze.getWidth();
		int[][] mazegrid = maze.getMazeGrid();
		String[][] strMazeArray = new String[mazeRow][mazeCol];
		StringBuilder sb = new StringBuilder();
		String str = null;
		
		for (int i = 0; i < mazeRow; i++) {
            for(int j = 0; j < mazeCol;j++) {
            	strMazeArray[i][j] = String.valueOf(mazegrid[i][j]);
            	str = sb.append(strMazeArray[i][j]).toString();
            }			
        }
				
		modelAndView.addObject("maze", maze);
		//modelAndView.addObject("strMazeArray",Arrays.toString(strMazeArray));
		modelAndView.addObject("strMazeArray",str);
		modelAndView.addObject("mazeRow",mazeRow);
		modelAndView.addObject("mazeCol", mazeCol);
		modelAndView.addObject("username", username);
		modelAndView.addObject("loggedIn", loggedIn);

		return modelAndView;
	}

}
