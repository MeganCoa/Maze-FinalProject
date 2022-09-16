package co.grandcircus.MazeAPI.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;

import co.grandcircus.MazeAPI.Models.Coordinate;
import co.grandcircus.MazeAPI.Models.Maze;


public interface MazeRepository extends MongoRepository<Maze, String> {
	
	Maze findByTitle(String title);
	Maze save(Maze entity);
	void deleteByTitle(String title);
	
	@Update("{'$set' : { 'mazeGrid' : ?1 } }")
	void findAndUpdateMazeGridByTitle(String title, int[][] newMazeGrid);
	@Update("{'$set' : { 'startCoordinate' : ?1 } }")
	void findAndUpdateStartCoordinateByTitle(String title, Coordinate startCoordinate);
	@Update("{'$set' : { 'endCoordinate' : ?1 } }")
	void findAndUpdateEndCoordinateByTitle(String title, Coordinate endCoordinate);
	
	Maze[] findByTitleContaining(String title);
	Maze[] findByAuthorNameContaining(String author);
	List<Maze> findAll();
}
