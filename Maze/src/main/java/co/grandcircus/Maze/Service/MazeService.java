package co.grandcircus.Maze.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.Maze.models.Coordinate;
import co.grandcircus.Maze.models.MazeResponse;

@Service
public class MazeService {
	
	@Value("${mazeapi.baseUrl}")
	private String baseUrl;
	private RestTemplate restTemplate = new RestTemplate();
	
	public MazeResponse findByTitle(String title) {
		String url = "/findByTitle/" + title;
		MazeResponse response = restTemplate.getForObject(baseUrl + url, MazeResponse.class, title);
		return response;
	}
	
	public void saveMaze(MazeResponse mazeResponse) {
		String url = baseUrl +  "/saveMaze";
		restTemplate.put(url, mazeResponse, MazeResponse.class);
	}
	
	public MazeResponse createMaze(MazeResponse mazeResponse) {
		String url = baseUrl + "/createmaze";
		return restTemplate.postForObject(url, mazeResponse, MazeResponse.class);
	}
	
	public void deleteByTitle(String title) {
		String url = "/deleteByTitle/{title}";
		restTemplate.delete(baseUrl + url, MazeResponse.class, title);
	}
	
	public void findAndUpdateMazeGridByTitle(String title, int[][] newMazeGrid) {		
		String url = "/findAndUpdateMazeGridByTitle/{title}";
		restTemplate.put(baseUrl + url, newMazeGrid, title);
	}
	
	public void findAndUpdateStartCoordinateByTitle(String title, Coordinate startCoordinate) {
		String url = "/findAndUpdateStartCoordinateByTitle/{title}";
		restTemplate.put(baseUrl + url, startCoordinate, title);
	}
	public void findAndUpdateEndCoordinateByTitle(String title, Coordinate endCoordinate) {
		String url = "/findAndUpdateEndCoordinateByTitle/{title}";
		restTemplate.put(baseUrl + url, endCoordinate, title);
	}
	public ArrayList<MazeResponse> findByTitleContaining(String title) {
		String url = baseUrl + "/findByTitleContaining/{title}";
		MazeResponse[] arr = restTemplate.getForObject(url, MazeResponse[].class, title);
		ArrayList<MazeResponse> result = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			result.add(arr[i]);
		}
		return result;
	}
	public ArrayList<MazeResponse> findByAuthorNameContaining(String author) {
		String url = baseUrl + "/findByAuthorNameContaining/{author}";
		MazeResponse[] arr = restTemplate.getForObject(url, MazeResponse[].class, author);
		ArrayList<MazeResponse> result = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			result.add(arr[i]);
		}
		return result;
	}
	public ArrayList<MazeResponse> findAllMazes() {
		String url = baseUrl + "/findallmazes";
		MazeResponse[] arr = restTemplate.getForObject(url, MazeResponse[].class);
		ArrayList<MazeResponse> result = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			result.add(arr[i]);
		}
		return result;
	}
	
}