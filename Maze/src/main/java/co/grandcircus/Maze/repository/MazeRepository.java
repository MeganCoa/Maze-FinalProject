package co.grandcircus.Maze.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;

import co.grandcircus.Maze.models.Maze;

public interface MazeRepository extends MongoRepository<Maze, String> {
	
	Maze findByAuthorName(String author);
	Maze findByTitle(String title);
	Maze save(Maze entity);
	void deleteByTitle(String title);
	@Update("{'$set' : { 'mazeGrid' : ?1 } }")
	void findAndUpdateMazeGridByTitle(String title, int[][] newMazeGrid);
	List<Maze> findAll();
}
