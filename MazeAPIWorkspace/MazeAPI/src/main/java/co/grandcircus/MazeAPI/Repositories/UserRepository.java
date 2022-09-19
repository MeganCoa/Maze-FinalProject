package co.grandcircus.MazeAPI.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;

import co.grandcircus.MazeAPI.Models.User;

public interface UserRepository extends MongoRepository<User, String> {
	User findByUsername(String username);
	List<User> findAll();
	User save(User entity);
	@Update("{ '$push' : { 'userMazes' : ?1 } }")
	void findAndPushToUserMazesByUsername(String username, String title);
	@Update("{ '$push' : { 'userFavorites' : ?1 } }")
	void findAndPushToUserFavoritesByUsername(String username, String title);
	@Update("{ '$push' : { 'userTempMazes' : ?1 } }")
	void findAndPushToUserTempMazesByUsername(String username, String title);
}
