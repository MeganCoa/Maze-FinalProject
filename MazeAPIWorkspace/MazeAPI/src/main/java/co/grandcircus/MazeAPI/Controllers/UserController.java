package co.grandcircus.MazeAPI.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.grandcircus.MazeAPI.Models.Maze;
import co.grandcircus.MazeAPI.Models.User;
import co.grandcircus.MazeAPI.Repositories.UserRepository;

@RestController
public class UserController {
	@Autowired
	private UserRepository repo;
	
	//reset
	@GetMapping("/resetusers")
	public String resetusers() {
		repo.deleteAll();
		return "Data reset";
	}
	@GetMapping("/findByUsername/{username}")
	public User findByUsername(@RequestParam String username) {
		return repo.findByUsername(username);
	}
	
	//read all
	@GetMapping("/findAllUsers")
	public List<User> findAllUsers() {
		return repo.findAll();
	}
	@PutMapping("/saveUser")
	public User save(@RequestBody User user) {
		return repo.save(user);
	}	
	@PutMapping("/findAndPushToUserMazesByUsername/{username}")
	public void findAndPushToUserMazesByUsername(@PathVariable("username") String username, @RequestBody String title) {
		repo.findAndPushToUserMazesByUsername(username, title);
	}
	@PutMapping("/findAndPushToUserFavoritesByUsername/{username}")
	public void findAndPushToUserFavoritesByUsername(@PathVariable("username") String username, @RequestBody String title) {
		repo.findAndPushToUserFavoritesByUsername(username, title);
	}
	@PutMapping("/findAndPushToUserTempMazesByUsername/{username}")
	public void findAndPushToUserTempMazesByUsername(@PathVariable("username") String username, @RequestBody String title) {
		repo.findAndPushToUserTempMazesByUsername(username, title);
	}
	//read one
	@GetMapping("/users/{id}")
	public User oneUser(@PathVariable("id") String id) {
		return repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}
	
	//create a user
	@PostMapping("/createuser")
	public User createUser(@RequestBody User user) {
		repo.insert(user);
		return user;
	}
	//update a user
	@PutMapping("/createuser/{id}")
	public User updateUserByID(@RequestBody User user, @PathVariable("id") String id) {
		user.setId(id);
		return repo.save(user);
	}
	
	//delete a user
	@DeleteMapping("/deleteuser/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("id") String id) {
		repo.deleteById(id);
	}
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String userNotFoundHandler(UserNotFoundException ex) {
		return ex.getMessage();
	}
}
