package co.grandcircus.Maze.controller;

import java.util.Optional;

import javax.swing.text.html.HTML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.Maze.models.User;
import co.grandcircus.Maze.repository.UserRepository;

@Controller
public class HomeController {
	@Autowired
	private UserRepository repo;
	
	@RequestMapping("/")
	public String showIndex(@RequestParam(required=false) String message, 
			@RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn, Model model) {
		model.addAttribute("message", message);
		model.addAttribute("username", username);
		model.addAttribute("loggedIn", loggedIn);
		return "index";
	}
	@RequestMapping("/usercreatemaze")
	public String showCreateMaze(@RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn, Model model) {
		model.addAttribute("username", username);
		model.addAttribute("loggedIn", loggedIn);
		return "createmaze";
	}
	@RequestMapping("/searchforamaze")
	public String showMazeSearch(@RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn, Model model) {
		model.addAttribute("username", username);
		model.addAttribute("loggedIn", loggedIn);
		return "mazesearch";
	}
	@RequestMapping("/login")
	public String showLogin(@RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn, Model model) {
		model.addAttribute("username", username);
		model.addAttribute("loggedIn", loggedIn);
		String message = "Please enter your username and password.";
		model.addAttribute("message", message);
		return "login";
	}
	@PostMapping("/login")
	public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
		Optional<User> optUser = repo.findByUsername(username);
		if (optUser.isPresent()) {
			if (optUser.get().getPassword().equals(hashPassword(password))) {
				boolean loggedIn = true;
				model.addAttribute("loggedIn", loggedIn);
				model.addAttribute("username", username);
				model.addAttribute("message", "Welcome back, " + username + "!");
			} else {
				model.addAttribute("message", "That password was incorrect");
				return "login";
			}
		}
		return "index";
	}
	@RequestMapping("/signup")
	public String showSignUp(@RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn, Model model) {
		model.addAttribute("username", username);
		model.addAttribute("loggedIn", loggedIn);
		return "signup";
	}
	@PostMapping("/signup")
	public String newUserSignUp(@RequestParam String username, @RequestParam String email, @RequestParam String password, Model model) {
		Optional<User> optUser = repo.findByUsername(username);
		if (optUser.isPresent()) {
			model.addAttribute("message", "That username exists already. Login instead?");
			return "login";
		} else {
			User user = new User(username, email, hashPassword(password));
			repo.insert(user);
			model.addAttribute("message", "Welcome, " + username + "!");
			model.addAttribute("username", username);
			model.addAttribute("loggedIn", true);
			return "index";
		}
	}
	@RequestMapping("/userhome")
	public String showUserHome(@RequestParam String username, Model model) {
		boolean loggedIn = true;
		model.addAttribute("loggedIn", loggedIn);
		model.addAttribute("username", username);
		return "userhome";
	}
	@RequestMapping("/signout")
	public String userSignout(@RequestParam String username, @RequestParam boolean loggedIn, Model model) {
		model.addAttribute("loggedIn", !loggedIn);
		model.addAttribute("message", "Come back soon, " + username + "!");
		return "index";
	}
	@PostMapping("/usermazes")
	public String userMazes(@RequestParam String username, @RequestParam boolean loggedIn, Model model) {
		model.addAttribute("username", username);
		model.addAttribute("loggedIn", loggedIn);
		model.addAttribute("mazes", repo.findByUsername(username).get().getUserMazes());
		return "usermazes";
	}
	public static String hashPassword(String password) {
		String result = "";
		int mult = (password.length() * password.length()) % 126;
		for (int i = password.length() - 1; i >= 0; i--) {
			int num = (int)password.charAt(i);
			num = num + mult;
			
			result += (char)num;
			mult += 4;
		}
		return result;
	}
}
