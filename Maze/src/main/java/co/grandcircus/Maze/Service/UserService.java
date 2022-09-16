package co.grandcircus.Maze.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.Maze.models.MazeResponse;
import co.grandcircus.Maze.models.UserResponse;

@Service
public class UserService {
	
	@Value("${mazeapi.baseUrl}")
	private String baseUrl;
	private RestTemplate restTemplate = new RestTemplate();
	
	public UserResponse findByUsername(String username) {
		String url = "/findByUsername/{username}";
		UserResponse response = restTemplate.getForObject(baseUrl + url, UserResponse.class, username);
		return response;
	}
	public ArrayList<UserResponse> findAllUsers() {
		String url = "/findAllUsers";
		UserResponse[] arr = restTemplate.getForObject(baseUrl + url, UserResponse[].class);
		ArrayList<UserResponse> result = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			result.add(arr[i]);
		}
		return result;
	}
	public UserResponse saveUser(UserResponse userResponse) {
		String url = "/saveUser";
		UserResponse response = restTemplate.getForObject(baseUrl + url, UserResponse.class);
		return response;
	}
	public void findAndPushToUserMazesByUsername(String username, String title) {	
		String url = "/findAndPushToUserMazesByUsername/{username}";
		restTemplate.put(baseUrl + url, title, username);
	}
	public void findAndPushToUserFavoritesByUsername(String username, String title) {
		String url = "/findAndPushToUserFavoritesByUsername/{username}";
		restTemplate.put(baseUrl + url, title, username);
	}
	public void findAndPushToUserTempMazesByUsername(String username, String title) {
		String url = "/findAndPushToUserTempMazesByUsername/{username}";
		restTemplate.put(baseUrl + url, title, username);
	}
}
