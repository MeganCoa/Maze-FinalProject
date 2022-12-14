package co.grandcircus.Maze.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.Maze.IconSearch.IconSearchService;
import co.grandcircus.Maze.Service.MazeService;
import co.grandcircus.Maze.Service.UserService;
import co.grandcircus.Maze.models.MazeResponse;
import co.grandcircus.Maze.models.UserResponse;

@Controller
public class HomeController {
	@Autowired
	private MazeService mazeService;
	@Autowired
	private UserService userService;
	@Autowired 
	private IconSearchService iconService;
	
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
		
		List<MazeResponse> mazes = new ArrayList<>();
		
		try {			
			if(searchCategory.equals("title")) {
				mazes = mazeService.findByTitleContaining(searchTerm);
			}else if(searchCategory.equals("author")) {
				mazes =  mazeService.findByAuthorNameContaining(searchTerm);
			}else {
				throw new Exception("Search Category not real.");
			}
		}catch(Exception e) {
			mazes = mazeService.findAllMazes();
			model.addAttribute("searchError", "There was an error with the search." );
			model.addAttribute("exceptionError", e.getMessage());
		}
		
		ArrayList<MazeResponse> validMazes = new ArrayList<>();
		for (MazeResponse maze : mazes) {
			if (maze.getValidMaze()) {
				validMazes.add(maze);
			}
		}
		
		model.addAttribute("username", username);
		model.addAttribute("loggedIn", loggedIn);
		model.addAttribute("allMazes", validMazes);
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
		ArrayList<UserResponse> allUsers = userService.findAllUsers();
		
		for (UserResponse user : allUsers) {
			
			if (user.getUsername().equals(username)) {
				System.out.println("\n username = username");
				if (user.getPassword().equals(hashPassword(password))) {
					System.out.println("\n password = password");
					boolean loggedIn = true;
					model.addAttribute("loggedIn", loggedIn);
					model.addAttribute("username", username);
					model.addAttribute("message", "Welcome back, " + username + "!");
					return "index";
				} else {
					System.out.println("\n password != password");
					model.addAttribute("message", "That password was incorrect.");
					model.addAttribute("loggedIn", false);
					return "login";
				}
			}
		}
		model.addAttribute("message", "That username/password combo does not exist.");
		model.addAttribute("loggedIn", false);
		return "login";
//		if (userService.findByUsername(username) != null) {
//			if (userService.findByUsername(username).getPassword().equals(hashPassword(password))) {
//				boolean loggedIn = true;
//				model.addAttribute("loggedIn", loggedIn);
//				model.addAttribute("username", username);
//				model.addAttribute("message", "Welcome back, " + username + "!");
//			} else {
//				model.addAttribute("message", "That password was incorrect.");
//				model.addAttribute("loggedIn", false);
//				return "login";
//			}
//		} else {
//			model.addAttribute("message", "That username/password combo does not exist.");
//			model.addAttribute("loggedIn", false);
//			return "login";
//		}
//		return "index";
	}

	@RequestMapping("/signup")
	public String showSignUp(@RequestParam(required=false) String username, @RequestParam(required=false) boolean loggedIn, Model model) {
		model.addAttribute("username", username);
		model.addAttribute("loggedIn", loggedIn);
		return "signup";
	}

	@PostMapping("/signup")
	public String newUserSignUp(@RequestParam String username, @RequestParam String email, @RequestParam String password, Model model) {
		ArrayList<UserResponse> allUsers = userService.findAllUsers();
		for (UserResponse user : allUsers) {
			if (user.getUsername().equals(username)) {
				model.addAttribute("message", "That username exists already. Login instead?");
				return "login";
			}
		}
		if (username.equalsIgnoreCase("Anonymous") || username.equalsIgnoreCase("null") || username.equals("")) {
			model.addAttribute("message", "That username is not permitted.");
			return "signup";
		} else {
			UserResponse user = new UserResponse(username, email, hashPassword(password));
			userService.saveUser(user);
			model.addAttribute("message", "Welcome, " + username + "!");
			model.addAttribute("username", username);
			model.addAttribute("loggedIn", true);
			return "index";
		}
	}

	@RequestMapping("/signout")
	public String userSignout(@RequestParam String username, @RequestParam boolean loggedIn, Model model) {
		model.addAttribute("loggedIn", !loggedIn);
		model.addAttribute("message", "Come back soon, " + username + "!");
		return "index";
	}

	@PostMapping("/usermazes")
	public String userMazes(@RequestParam String username, @RequestParam boolean loggedIn, Model model) {
		ArrayList<UserResponse> allUsers = userService.findAllUsers();
		for (UserResponse user : allUsers) {
			if (user.getUsername().equals(username)) {
				ArrayList<MazeResponse> userMazes = new ArrayList<>();
				for (String title : user.getUserMazes()) {
					userMazes.add(mazeService.findByTitle(title));
				}
				model.addAttribute("userMazes", userMazes);
				model.addAttribute("userFavorites", user.getUserFavorites());
			}
		}
		
		model.addAttribute("username", username);
		model.addAttribute("loggedIn", loggedIn);
		return "usermazes";
	}
	@PostMapping("/addUserFavorite")
	public String addToUserFavorites(@RequestParam String username, @RequestParam String title, @RequestParam boolean loggedIn, Model model) {
		ArrayList<UserResponse> allUsers = userService.findAllUsers();
		for (UserResponse user : allUsers) {
			if (user.getUsername().equals(username)) {
				if (!user.getUserFavorites().contains(title)) {
					userService.findAndPushToUserFavoritesByUsername(username, title);
				}
			}
		}
//		if (!userService.findByUsername(username).getUserFavorites().contains(title)) {
//			userService.findAndPushToUserFavoritesByUsername(username, title);
//		}
		
		MazeResponse maze = mazeService.findByTitle(title);
		model.addAttribute("username", username);
		model.addAttribute("loggedIn", loggedIn);
		model.addAttribute("maze", mazeService.findByTitle(title));
		model.addAttribute("symbolMaze", maze.mazeVisualizer());		
		model.addAttribute("picture", iconService.getIconObjects("dog").getData().getObjects().get(5).getAssets().get(2).getUrl());
		
		return "displaymaze";
	}	
	
	@PostMapping("/removeUserFavorite")
	public String removeFromUserFavorites(@RequestParam String username, @RequestParam String title, @RequestParam boolean loggedIn, Model model) {
	
		ArrayList<UserResponse> allUsers = userService.findAllUsers();
		
		for (UserResponse user : allUsers) {
			if (user.getUsername().equals(username)) {
		
				if (user.getUserFavorites().contains(title)) {
					user.getUserFavorites().remove(title);
					userService.saveUser(user);
				}
			}
		}
		String message = "We found and removed " + title + " from your favorites!";
		
		model.addAttribute("username", username);
		model.addAttribute("loggedIn", loggedIn);
		model.addAttribute("message", message);
		
		return "deleteconfirmation";
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
