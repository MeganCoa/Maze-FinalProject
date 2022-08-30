package co.grandcircus.Maze.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.grandcircus.Maze.models.User;

public interface UserRepository extends MongoRepository<User, String> {

}
