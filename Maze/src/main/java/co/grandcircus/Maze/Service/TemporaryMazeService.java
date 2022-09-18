package co.grandcircus.Maze.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.Maze.models.Coordinate;
import co.grandcircus.Maze.models.MazeResponse;
import co.grandcircus.Maze.models.TemporaryMazeResponse;

@Service
public class TemporaryMazeService {
	@Value("${mazeapi.baseUrl}")
	private String baseUrl;
	private RestTemplate restTemplate = new RestTemplate();
	
	public TemporaryMazeResponse findByTemporaryTitle(String title) {
		String url = "/findByTemporaryTitle/{title}";
		TemporaryMazeResponse response = restTemplate.getForObject(baseUrl + url, TemporaryMazeResponse.class, title);
		return response;
	}
	
	public void saveTemporaryMaze(TemporaryMazeResponse temporaryMazeResponse) {
		String url = "/saveTemporaryMaze";
		restTemplate.put(baseUrl + url, temporaryMazeResponse, TemporaryMazeResponse.class);
	}
	
	public void deleteTemporaryByTitle(String title) {
		String url = "/deleteTemporaryByTitle/" + title;
		restTemplate.delete(baseUrl + url);
	}
	
	public void findAndUpdateTemporaryMazeGridByTitle(String title, int[][] newMazeGrid) {		
		String url = "/findAndUpdateTemporaryMazeGridByTitle/{title}";
		restTemplate.put(baseUrl + url, newMazeGrid, title);
	}
	
	public void findAndUpdateTemporaryStartCoordinateByTitle(String title, Coordinate startCoordinate) {
		String url = "/findAndUpdateTemporaryStartCoordinateByTitle/{title}";
		restTemplate.put(baseUrl + url, startCoordinate, title);
	}
	public void findAndUpdateTemporaryEndCoordinateByTitle(String title, Coordinate endCoordinate) {
		String url = "/findAndUpdateTemporaryEndCoordinateByTitle/{title}";
		restTemplate.put(baseUrl + url, endCoordinate, title);
	}
	
	public ArrayList<TemporaryMazeResponse> findAllTemporaryMazes() {
		String url = "/findAllTemporaryMazes";
		TemporaryMazeResponse[] arr = restTemplate.getForObject(baseUrl + url, TemporaryMazeResponse[].class);
		ArrayList<TemporaryMazeResponse> result = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			result.add(arr[i]);
		}
		return result;
	}
}
