package co.grandcircus.Maze.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.grandcircus.Maze.models.Coordinate;
import co.grandcircus.Maze.models.Maze;
import co.grandcircus.Maze.repository.MazeRepository;

@RestController
public class MazeController {
	@Autowired
	private MazeRepository repo;
	
	//reset
	@GetMapping("/reset")
	public String reset() {
		repo.deleteAll();
		
		return "Data reset";
	}
	
	//read all
	@GetMapping("/mazes")
	public List<Maze> allMazes() {
		return repo.findAll();
	}
	
	//read one
	@GetMapping("/mazes/{id}")
	public Maze oneMaze(@PathVariable("id") String id) {
		return repo.findById(id).orElseThrow(() -> new MazeNotFoundException(id));
	}
	
	//create a maze
	@PostMapping("/createmaze")
	public Maze createMaze(@RequestBody Maze maze) {
		repo.insert(maze);
		return maze;
	}
	
	//update a maze
	@PutMapping("/createmaze/{id}")
	public Maze updateMazeByID(@RequestBody Maze maze, @PathVariable("id") String id) {
		maze.setId(id);
		return repo.save(maze);
	}
	
	//delete a maze
	@DeleteMapping("/deletemaze/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMaze(@PathVariable("id") String id) {
		repo.deleteById(id);
	}
	
	@ResponseBody
	@ExceptionHandler(MazeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String mazeNotFoundHandler(MazeNotFoundException ex) {
		return ex.getMessage();
	}
}
