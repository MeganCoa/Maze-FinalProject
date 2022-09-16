package co.grandcircus.Maze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.Maze.models.MazeResponse;

@Controller
public class GamepageController {
	
//	@Autowired
//	private MazeRepository repo;
	
	@RequestMapping("/mazegamepage")
	public String showIndex() {
		return "mazegamepage";
	}

//	@PostMapping("/generatedmaze")
//	public String generatedMaze(@RequestParam int favorite, Model model) {
//		Maze maze = new Maze();
//		//repo.save(maze);
//		return "generatedmaze";
//	}
}
