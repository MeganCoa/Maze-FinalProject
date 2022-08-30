package co.grandcircus.Maze.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.grandcircus.Maze.models.Users;

public interface UserRepository extends MongoRepository<Users, String> {

}
