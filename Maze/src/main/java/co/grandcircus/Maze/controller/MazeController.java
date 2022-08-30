package co.grandcircus.Maze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import co.grandcircus.Maze.repository.UserRepository;

@RestController
public class MazeController {
	@Autowired
	private UserRepository repo;

}
