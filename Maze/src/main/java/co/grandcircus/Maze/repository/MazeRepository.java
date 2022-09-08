package co.grandcircus.Maze.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.grandcircus.Maze.models.Maze;

public interface MazeRepository extends MongoRepository<Maze, String> {
	
	Maze findByAuthorName(String author);
	Maze findByTitle(String title);
	
	List<Maze> findAll();
}
