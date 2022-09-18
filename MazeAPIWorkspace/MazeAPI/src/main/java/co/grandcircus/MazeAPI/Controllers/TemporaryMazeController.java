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
import co.grandcircus.MazeAPI.Models.TemporaryMaze;
import co.grandcircus.MazeAPI.Repositories.MazeRepository;
import co.grandcircus.MazeAPI.Repositories.TemporaryMazeRepository;

@RestController
public class TemporaryMazeController {
	@Autowired
	private TemporaryMazeRepository repo;
	
	//reset
	@GetMapping("/temporarymazereset")
	public String temporaryMazeReset() {
		repo.deleteAll();
		return "Data reset";
	}
	//findByTitle
	@GetMapping("/findByTemporaryTitle/{title}")
	public TemporaryMaze findByTemporaryTitle(@PathVariable("title") String title) {
		return repo.findByTitle(title);
	}
	//update a maze
	@PutMapping("/saveTemporaryMaze")
	public TemporaryMaze saveTemporary(@RequestBody TemporaryMaze temporaryMaze) {
		return repo.save(temporaryMaze);
	}
	//delete a maze
	@DeleteMapping("/deleteTemporaryByTitle/{title}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTemporaryMaze(@PathVariable("title") String title) {
		repo.deleteByTitle(title);
	}	
	@PutMapping("/findAndUpdateTemporaryMazeGridByTitle/{title}")
	public void findAndUpdateTemporaryMazeGridByTitle(@PathVariable("title") String title, @RequestBody int[][] newMazeGrid) {
		repo.findAndUpdateMazeGridByTitle(title, newMazeGrid);
	}
	@PutMapping("/findAndUpdateTemporaryStartCoordinateByTitle/{title}")
	public void findAndUpdateTemporaryStartCoordinateByTitle(@PathVariable("title") String title, @RequestBody Coordinate startCoordinate) {
		repo.findAndUpdateStartCoordinateByTitle(title, startCoordinate);
	}
	@PutMapping("/findAndUpdateTemporaryEndCoordinateByTitle/{title}")
	public void findAndUpdateTemporaryEndCoordinateByTitle(@PathVariable("title") String title, @RequestBody Coordinate endCoordinate) {
		repo.findAndUpdateEndCoordinateByTitle(title, endCoordinate);
	}
	//read all
	@GetMapping("/findAllTemporaryMazes")
	public List<TemporaryMaze> findAllTemporaryMazes() {
		return repo.findAll();
	}
	//create a maze
	@PostMapping("/createtemporarymaze")
	public TemporaryMaze createTemporaryMaze(@RequestBody TemporaryMaze temporaryMaze) {
		repo.insert(temporaryMaze);
		return temporaryMaze;
	}
	@ResponseBody
	@ExceptionHandler(TempMazeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String temporaryMazeNotFoundHandler(TempMazeNotFoundException ex) {
		return ex.getMessage();
	}
}
