package co.grandcircus.Maze.IconSearch;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
	@JsonProperty("objects")
	private List<Objects> objects;
	@JsonProperty("success")
	private String success;

	public List<Objects> getObjects() {
		return objects;
	}

	public void setObjects(List<Objects> objects) {
		this.objects = objects;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Data(List<Objects> objects, String success) {
		super();
		this.objects = objects;
		this.success = success;
	}
	public Data() {
		
	}
	
	
}
