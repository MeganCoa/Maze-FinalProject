package co.grandcircus.Maze.IconSearch;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Objects {
@JsonProperty("name")
private String name;
@JsonProperty("assets")
private List<Assets> assets;


public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public List<Assets> getAssets() {
	return assets;
}

public void setAssets(List<Assets> assets) {
	this.assets = assets;
}


public Objects(String name, List<Assets> assets) {
	super();
	this.name = name;
	this.assets = assets;
}

public Objects() {

}

}
