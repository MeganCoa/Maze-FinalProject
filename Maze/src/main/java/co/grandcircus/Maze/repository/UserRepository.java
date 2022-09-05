package co.grandcircus.Maze.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.grandcircus.Maze.models.User;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);
}
