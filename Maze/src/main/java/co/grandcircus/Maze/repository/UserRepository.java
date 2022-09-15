package co.grandcircus.Maze.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;

import co.grandcircus.Maze.models.User;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);
	List<User> findAll();
	@Update("{ '$push' : { 'userMazes' : ?1 } }")
	void findAndPushToUserMazesByUsername(String username, String title);
	@Update("{ '$push' : { 'userFavorites' : ?1 } }")
	void findAndPushToUserFavoritesByUsername(String username, String title);
	@Update("{ '$push' : { 'userTempMazes' : ?1 } }")
	void findAndPushToUserTempMazesByUsername(String username, String title);
}
