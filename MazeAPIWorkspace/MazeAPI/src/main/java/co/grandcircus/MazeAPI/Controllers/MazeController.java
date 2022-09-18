package co.grandcircus.MazeAPI.Controllers;

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

import co.grandcircus.MazeAPI.Models.Coordinate;
import co.grandcircus.MazeAPI.Models.Maze;
import co.grandcircus.MazeAPI.Repositories.MazeRepository;

@RestController
public class MazeController {
	@Autowired
	private MazeRepository repo;
	
	//reset
	@GetMapping("/mazereset")
	public String reset() {
		repo.deleteAll();
		return "Data reset";
	}
	//findByAuthorName
//	@GetMapping("/findByAuthorName/{authorName}")
//	public Maze findByAuthorName(@PathVariable("authorName") String authorName) {
//		return repo.findByAuthorName(authorName);
//	}
	//findByTitle
	@GetMapping("/findByTitle/{title}")
	public Maze findByTitle(@PathVariable("title") String title) {
		return repo.findByTitle(title);
	}
	//update a maze
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/saveMaze")
	public Maze save(@RequestBody Maze maze) {
		return repo.save(maze);
	}	
	//delete a maze
	@DeleteMapping("/deleteByTitle/{title}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMaze(@PathVariable("title") String title) {
		repo.deleteByTitle(title);
	}	
	@PutMapping("/findAndUpdateMazeGridByTitle/{title}")
	public void findAndUpdateMazeGridByTitle(@PathVariable("title") String title, @RequestBody int[][] newMazeGrid) {
		repo.findAndUpdateMazeGridByTitle(title, newMazeGrid);
	}
	@PutMapping("/findAndUpdateStartCoordinateByTitle/{title}")
	public void findAndUpdateStartCoordinateByTitle(@PathVariable("title") String title, @RequestBody Coordinate startCoordinate) {
		repo.findAndUpdateStartCoordinateByTitle(title, startCoordinate);
	}
	@PutMapping("/findAndUpdateEndCoordinateByTitle/{title}")
	public void findAndUpdateEndCoordinateByTitle(@PathVariable("title") String title, @RequestBody Coordinate endCoordinate) {
		repo.findAndUpdateEndCoordinateByTitle(title, endCoordinate);
	}
	@GetMapping("/findByTitleContaining/{title}")
	public Maze[] findByTitleContaining(@PathVariable("title") String title) {
		return repo.findByTitleContaining(title);
	}
	@GetMapping("/findByAuthorNameContaining/{title}")
	public Maze[] findByAuthorNameContaining(@PathVariable("author") String author) {
		return repo.findByAuthorNameContaining(author);
	}
	//read all
	@GetMapping("/findallmazes")
	public List<Maze> findAllMazes() {
		return repo.findAll();
	}
	//create a maze
	@PostMapping("/createmaze")
	@ResponseStatus(HttpStatus.CREATED)
	public Maze createMaze(@RequestBody Maze maze) {
		repo.insert(maze);
		return maze;
	}
	@ResponseBody
	@ExceptionHandler(MazeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String mazeNotFoundHandler(MazeNotFoundException ex) {
		return ex.getMessage();
	}
}
