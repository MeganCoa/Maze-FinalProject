package co.grandcircus.Maze.IconSearch;


import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import co.grandcircus.Maze.models.Objects;

@Service
public class IconSearchService {
	
	private RestTemplate request = new RestTemplate();
	private HttpHeaders headers = new HttpHeaders();
	
	private static final String REQUEST_SEARCH = "https://open-source-icons-search.p.rapidapi.com/vectors/search?query=%d";
	
	 public List<Objects> getObjects(String searchTerm) {
	        headers.set("X-RapidAPI-Key", "0ece17a191msh867648ce9be47bbp1fe25ejsn922cd8fb3801");
	        headers.set("X-RapidAPI-Host", "open-source-icons-search.p.rapidapi.com");
	        
	        String req = String.format(REQUEST_SEARCH , searchTerm);
	       
	        return request.getForObject(req, IconSearchResponse.class).getObjects() ;
	 }
}