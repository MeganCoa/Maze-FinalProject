package co.grandcircus.Maze.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;

import co.grandcircus.Maze.models.User;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);
	
	@Update("{ '$push' : { 'userFavorites' : ?1 } }")
	void findAndPushToUserFavoritesByUsername(String username, String title);
}
