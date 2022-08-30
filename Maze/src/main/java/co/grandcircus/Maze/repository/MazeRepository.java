package co.grandcircus.Maze.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.grandcircus.Maze.models.Mazes;

public interface MazeRepository extends MongoRepository<Mazes, String> {

}
