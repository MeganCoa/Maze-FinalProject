package co.grandcircus.Maze.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.grandcircus.Maze.models.User;
import co.grandcircus.Maze.repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	private UserRepository repo;
	
	//reset
	@GetMapping("/reset")
	public String reset() {
		repo.deleteAll();
		User user = new User("Clara", "email@yahoo.com", "sesame");
		repo.insert(user);
		return "Data reset";
	}
	
	//read all
	@GetMapping("/users")
	public List<User> allUsers() {
		return repo.findAll();
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
	@DeleteMapping("/deletemaze/{id}")
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
