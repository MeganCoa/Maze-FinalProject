package co.grandcircus.Maze.controller;

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

import co.grandcircus.Maze.models.TemporaryMaze;
import co.grandcircus.Maze.repository.TemporaryMazeRepository;

@RestController
public class TemporaryMazeController {
	@Autowired
	private TemporaryMazeRepository repo;
	
	//reset
	@GetMapping("/tempmazereset")
	public String reset() {
		repo.deleteAll();
			
		return "Data reset";
	}
		
	//read all
	@GetMapping("/tempmaze")
	public List<TemporaryMaze> allTempMazes() {
		return repo.findAll();
	}
		
	//read one
	@GetMapping("/tempmaze/{id}")
	public TemporaryMaze oneTempMaze(@PathVariable("id") String id) {
		return repo.findById(id).orElseThrow(() -> new TempMazeNotFoundException(id));
	}
		
	//create a maze
	@PostMapping("/createtempmaze")
	public TemporaryMaze createTempMaze(@RequestBody TemporaryMaze tempMaze) {
		repo.insert(tempMaze);
		return tempMaze;
	}
		
	//update a maze
	@PutMapping("/createtempmaze/{id}")
	public TemporaryMaze updateTempMazeByID(@RequestBody TemporaryMaze tempMaze, @PathVariable("id") String id) {
		tempMaze.setId(id);
		return repo.save(tempMaze);
	}
		
	//delete a maze
	@DeleteMapping("/deletetempmaze/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTempMaze(@PathVariable("id") String id) {
		repo.deleteById(id);
	}
		
	@ResponseBody
	@ExceptionHandler(TempMazeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String tempMazeNotFoundHandler(TempMazeNotFoundException ex) {
		return ex.getMessage();
	}
}
