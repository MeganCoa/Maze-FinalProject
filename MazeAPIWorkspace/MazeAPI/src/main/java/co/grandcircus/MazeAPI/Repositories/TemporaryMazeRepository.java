package co.grandcircus.MazeAPI.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;

import co.grandcircus.MazeAPI.Models.Coordinate;
import co.grandcircus.MazeAPI.Models.TemporaryMaze;

public interface TemporaryMazeRepository extends MongoRepository<TemporaryMaze, String> {
	TemporaryMaze findByAuthorName(String author);
	TemporaryMaze findByTitle(String title);
	TemporaryMaze save(TemporaryMaze entity);
	void deleteByTitle(String title);
	void deleteAll();
	
	@Update("{'$set' : { 'mazeGrid' : ?1 } }")
	void findAndUpdateMazeGridByTitle(String title, int[][] newMazeGrid);
	@Update("{'$set' : { 'startCoordinate' : ?1 } }")
	void findAndUpdateStartCoordinateByTitle(String title, Coordinate startCoordinate);
	@Update("{'$set' : { 'endCoordinate' : ?1 } }")
	void findAndUpdateEndCoordinateByTitle(String title, Coordinate endCoordinate);
	
	List<TemporaryMaze> findByTitleContaining(String title);
	List<TemporaryMaze> findByAuthorNameContaining(String author);
	List<TemporaryMaze> findAll();
}