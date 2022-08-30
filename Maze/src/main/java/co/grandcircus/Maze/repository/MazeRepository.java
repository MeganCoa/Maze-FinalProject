package co.grandcircus.Maze.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.grandcircus.Maze.models.Maze;

public interface MazeRepository extends MongoRepository<Maze, String> {

}
