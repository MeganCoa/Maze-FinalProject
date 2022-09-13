package co.grandcircus.Maze.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.HTML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.Maze.models.Maze;
import co.grandcircus.Maze.models.User;
import co.grandcircus.Maze.repository.MazeRepository;
import co.grandcircus.Maze.repository.UserRepository;

@Controller
public class HomeController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private MazeRepository mazeRepo;
	@RequestMapping("/")
	public String showIndex(@RequestParam(required = false) String message,
			@RequestParam(required = false) String username, @RequestParam(required = false) boolean loggedIn,
			Model model) {
		model.addAttribute("message", message);
		model.addAttribute("username", username);
		model.addAttribute("loggedIn", loggedIn);
		return "index";
	}

	@RequestMapping("/usercreatemaze")
	public String showCreateMaze(@RequestParam(required = false) String username,
			@RequestParam(required = false) boolean loggedIn, Model model) {
		model.addAttribute("username", username);
		model.addAttribute("loggedIn", loggedIn);
		return "createmaze";
	}

	@RequestMapping("/searchforamaze")
	public String showMazeSearch(@RequestParam(required = false) String searchTerm, @RequestParam(required = false) String searchCategory, @RequestParam(required = false) String username,
			@RequestParam(required = false) boolean loggedIn, Model model) {
		List<Maze> mazes = new ArrayList<>();
		try {			
			 if(searchCategory.equals("title")) {
				mazes = mazeRepo.findByTitleContaining(searchTerm);
			}else if(searchCategory.equals("author")) {
				mazes =  mazeRepo.findByAuthorNameContaining(searchTerm);
			}else {
				throw new Exception("Search Category not real.");
			}
			}catch(Exception e) {
				mazes = mazeRepo.findAll();
				model.addAttribute("searchError", "There was an error with the search." );
				model.addAttribute("exceptionError", e.getMessage());
			}
		
		model.addAttribute("username", username);
		model.addAttribute("loggedIn", loggedIn);
		model.addAttribute("allMazes", mazes);
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
		Optional<User> optUser = userRepo.findByUsername(username);
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
		Optional<User> optUser = userRepo.findByUsername(username);
		if (optUser.isPresent()) {
			model.addAttribute("message", "That username exists already. Login instead?");
			return "login";
		} else if (username.equalsIgnoreCase("Anonymous") || username.equalsIgnoreCase("null") || username.equals("")) {
			model.addAttribute("message", "That username is not permitted.");
			return "login";
		} else {
			User user = new User(username, email, hashPassword(password));
			userRepo.insert(user);
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
		model.addAttribute("userMazes", userRepo.findByUsername(username).get().getUserMazes());
		model.addAttribute("userFavorites", userRepo.findByUsername(username).get().getUserFavorites());
		return "usermazes";
	}
	@PostMapping("/addUserFavorite")
	public String addToUserFavorites(@RequestParam String username, @RequestParam String title, @RequestParam boolean loggedIn, Model model) {
		Optional<User> optUser = userRepo.findByUsername(username);
		if (!optUser.get().getUserFavorites().contains(title)) {
			userRepo.findAndPushToUserFavoritesByUsername(username, title);
		}
		model.addAttribute("username", username);
		model.addAttribute("loggedIn", loggedIn);
		model.addAttribute("maze", mazeRepo.findByTitle(title));
		model.addAttribute("symbolMaze", mazeDisplayWriter(title));		
		
		return "displaymaze";

	}	
	public String mazeDisplayWriter(String title) {
		
		Maze maze = mazeRepo.findByTitle(title);
		
		StringBuilder result = new StringBuilder(maze.getWidth() * (maze.getHeight() + 1));
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                if (maze.getMazeGrid()[row][col] == 0) { //Based on final variables, 0 generates a wall 
                    result.append("#  ");
                } else if (maze.getMazeGrid()[row][col] == 1) { //Based on final variables, 1 generates open space
                    result.append("0  ");
                } else if (maze.getMazeGrid()[row][col] == 2) { //Based on final variables, 2 generates maze start point
                    result.append("S  ");
                } else if (maze.getMazeGrid()[row][col] == 3) { //Based on final variables, 3 generates maze end point
                    result.append("E  ");
                } else {
                    result.append('.'); //Everything else is the path
                }
            }
            result.append("<br>");
        }
        return result.toString();
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
