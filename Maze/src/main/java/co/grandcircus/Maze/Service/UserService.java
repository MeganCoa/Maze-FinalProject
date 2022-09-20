package co.grandcircus.Maze.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
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
		String url = baseUrl + "/findByUsername";
		UserResponse response = restTemplate.getForObject(url, UserResponse.class, username);
		return response;
	}
//	public Optional<UserResponse> findByUsername(String username) {
//		String url = baseUrl + "/findByUsername/" + username;
//		
//		ParameterizedTypeReference<Optional<UserResponse>> responseType = new ParameterizedTypeReference<Optional<UserResponse>>(){};
//		RequestEntity<Void> request = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
//		Optional<UserResponse> response = restTemplate.exchange(request, responseType).getBody();
//		return response;
//	}
	public ArrayList<UserResponse> findAllUsers() {
		String url = "/findAllUsers";
		UserResponse[] arr = restTemplate.getForObject(baseUrl + url, UserResponse[].class);
		ArrayList<UserResponse> result = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			result.add(arr[i]);
		}
		return result;
	}
	public void saveUser(UserResponse userResponse) {
		String url = baseUrl + "/saveUser";
		restTemplate.put(url, userResponse, UserResponse.class);
		
	}
	public void createUser(UserResponse userResponse) {
		String url = baseUrl + "/createuser";
		restTemplate.put(url, userResponse);
	}
	public void findAndPushToUserMazesByUsername(String username, String title) {	
		String url = baseUrl +  "/findAndPushToUserMazesByUsername/{username}";
		restTemplate.put(url, title, username);
	}
	public void findAndPushToUserFavoritesByUsername(String username, String title) {
		String url = "/findAndPushToUserFavoritesByUsername/{username}";
		restTemplate.put(baseUrl + url, title, username);
	}
	public void findAndRemoveFromUserFavoritesByUsername(String username, String title) {
		String url = "/findAndRemoveFromUserFavoritesByUsername/{username}";
		restTemplate.put(baseUrl + url, title, username);
	}
	public void findAndPushToUserTempMazesByUsername(String username, String title) {
		String url = "/findAndPushToUserTempMazesByUsername/{username}";
		restTemplate.put(baseUrl + url, title, username);
	}
}
